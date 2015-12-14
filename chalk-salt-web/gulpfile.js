'use strict';

var gulp = require('gulp'),
  prefix = require('gulp-autoprefixer'),
  minifyCss = require('gulp-minify-css'),
  usemin = require('gulp-usemin'),
  uglify = require('gulp-uglify'),
  minifyHtml = require('gulp-minify-html'),
  livereload = require('gulp-livereload'),
  imagemin = require('gulp-imagemin'),
  ngAnnotate = require('gulp-ng-annotate'),
  jshint = require('gulp-jshint'),
  rev = require('gulp-rev'),
  connect = require('gulp-connect'),
  proxy = require('proxy-middleware'),
  es = require('event-stream'),
  flatten = require('gulp-flatten'),
  del = require('del'),
  vinylPaths = require('vinyl-paths'),
  replace = require('gulp-replace'),
  browserify = require('gulp-browserify'),
  bower = require('gulp-bower'),
  sass = require('gulp-sass'),
  watch = require('gulp-watch'),
  ngConstant = require('gulp-ng-constant'),
  rename = require("gulp-rename"),
  gutil = require('gulp-load-utils')(['colors', 'env', 'log', 'pipeline']);
                                                     
var karma = require('gulp-karma')({configFile: 'src/test/javascript/karma.conf.js'});

var yeoman = {
  app: 'app/',
  dist: 'dist/',
  test: 'src/test/javascript/spec/',
  tmp: '.tmp/',
  scss: 'scss/'
}

gulp.task('clean', function(){
  return gulp.src(yeoman.dist, {read: false}).
    pipe(vinylPaths(del));
});

gulp.task(':tmp', function(){
  return gulp.src(yeoman.tmp, {read: false}).
    pipe(vinylPaths(del));
});

gulp.task('bower', function() {
	  return bower({ cmd: 'update'})
});
                  
gulp.task('test', function(){
  karma.once();
});

gulp.task('i18n', function(){
  return gulp.src(yeoman.app + 'resources/i18n/**')
      .pipe(livereload());
});

gulp.task('images', function(){
    return gulp.src(yeoman.app + 'resources/img/**').
      pipe(livereload());
});


gulp.task('templates', function(){
    return gulp.src(yeoman.app + 'modules/**/*.html').
      pipe(livereload());
});

gulp.task('scripts', function(){
    return gulp.src(yeoman.app + 'modules/**/*.js').
      pipe(livereload());
});

gulp.task('customScripts', function(){
    return gulp.src(yeoman.app + 'js/**/*.js').
      pipe(livereload());
});

gulp.task('sass', function () {
    gulp.src(yeoman.scss+'**/*.scss')
        .pipe(sass())
        .pipe(gulp.dest(yeoman.app+'resources/css'))
        .pipe(livereload()).on('error', function(err){
            displayError(err);
        });
});

gulp.task('fonts:dist', ['clean', 'config'], function(){
    return gulp.src(yeoman.app + '**/*.{woff,svg,ttf,eot}').
           pipe(flatten()).
           pipe(gulp.dest(yeoman.dist + 'fonts/'));
});

gulp.task('i18n:dist', function(){
    return gulp.src(yeoman.app + 'resources/i18n/**')
            .pipe(gulp.dest(yeoman.dist + 'i18n/'))
});

gulp.task('images:dist', function(){
    return gulp.src(yeoman.app + 'resources/img/**').
      pipe(imagemin({optimizationLevel: 5})).
      pipe(gulp.dest(yeoman.dist + 'img'))
      pipe(livereload());
});

gulp.task('styles:dist', function () {
    return gulp.src(yeoman.app+'resources/css/**')
        .pipe(minifyCss({compatibility: 'ie8'}))
        .pipe(gulp.dest(yeoman.dist+'css'))
});

gulp.task('jshint', function () {
    gulp.src([yeoman.app+'/modules/**/*.js'])
        .pipe(jshint('.jshintrc'))
        .pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('watch', function() {
    livereload.listen();
    gulp.watch(yeoman.app + 'modules/**/*.js', ['scripts']);
    gulp.watch(yeoman.app + 'js/**/*.js', ['customScripts']);
    gulp.watch(yeoman.scss+'*.scss', ['sass']); 
    gulp.watch(yeoman.app +'resources/img/**', ['images']);
    gulp.watch(yeoman.app+'modules/**/*.html', ['templates']);
    gulp.watch(yeoman.app+'resources/i18n/**', ['i18n']);
});

gulp.task('usemin', ['images', 'sass'], function(){
  return gulp.src(yeoman.app + '{,views/}*.html').
    pipe(usemin({
      css: [
        prefix.apply(),
        replace(/[0-9a-zA-Z\-_\s\.\/]*\/([a-zA-Z\-_\.0-9]*\.(woff|eot|ttf|svg))/g, '/fonts/$1'),
        'concat',
        rev()
      ],
      html: [
        minifyHtml({empty: true, conditionals:true})
      ],
      js: [
        ngAnnotate(),
        uglify(),
        'concat',
        rev()
      ]
    })).
    pipe(gulp.dest(yeoman.dist));
});

gulp.task('config', function () {
    var environment = gutil.env.environment || "development";
    gutil.log( 'Configuring', gutil.colors.bold.green(environment) , 'environment...');
    var envConfig = require('./config/' + environment + '.json');
    return gulp.src('./config/configuration.json')
        .pipe(ngConstant({
          name: 'System.configuration',
          dest:'configuration.js',
          constants: envConfig,
          wrap:'"use strict";\n\n define(["angular"], function(angular) { \n  <%= __ngModule %> \n\n});',
        }))
        .pipe(gulp.dest('src/main/webapp/app/js/'));
        
});



var displayError = function(error) {

    var errorString = '[' + error.plugin + ']';
    errorString += ' ' + error.message.replace("\n",''); 
    if(error.fileName)
        errorString += ' in ' + error.fileName;
    if(error.lineNumber)
        errorString += ' on line ' + error.lineNumber;
    console.error(errorString);
}


gulp.task('serve', ['build','watch'], function() {
    connect.server(
      {
        root: [yeoman.app, yeoman.tmp],
        port: 9000,
        livereload: true,
        middleware: function(connect, o) {
          return [
            (function() {
              var url = require('url');
              var proxy = require('proxy-middleware');
              var options = url.parse('http://localhost:8080/app');
              options.route = '/app';
              return proxy(options);
            })(),
            (function() {
              var url = require('url');
              var proxy = require('proxy-middleware');
              var options = url.parse('http://localhost:8080/api-docs');
              options.route = '/api-docs';
              return proxy(options);
            })(),
            (function() {
              var url = require('url');
              var proxy = require('proxy-middleware');
              var options = url.parse('http://localhost:8080/metrics');
              options.route = '/metrics';
              return proxy(options);
            })(),
            (function() {
              var url = require('url');
              var proxy = require('proxy-middleware');
              var options = url.parse('http://localhost:8080/dump');
              options.route = '/dump';
              return proxy(options);
            })()
          ];
        }
      }
    );
});

gulp.task('build', ['config', 'sass']);

gulp.task('default', ['build']);

gulp.task('release',['clean', 'bower', 'build', 'fonts:dist', 'images:dist', 'styles:dist']);

