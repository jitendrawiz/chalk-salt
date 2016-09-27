require.config({
  baseUrl: '.',
  waitSeconds: 0,
  paths: {
    angular: 'lib/angular/angular.min',
    domReady: 'lib/requirejs-domready/domReady',
    angularSanitize: 'lib/angular-sanitize/angular-sanitize.min',
    uiRouter: 'lib/angular-ui-router/release/angular-ui-router.min',
    angularResource: 'lib/angular-resource/angular-resource.min',
    text: 'lib/requirejs-text/text',
    css: 'lib/require-css/css',
    jquery: 'lib/jquery/dist/jquery.min',
    jqueryUI: 'lib/jquery-ui/jquery-ui.min',
    bootstrapCSS: 'lib/bootstrap/dist/css/bootstrap',
    bootstrap:'lib/bootstrap/dist/js/bootstrap.min',
    candDCss: 'resources/css/chalk-Dust',
    uiBootstrap: 'lib/angular-bootstrap/ui-bootstrap-tpls',
    fontAwesome: 'lib/fontawesome/css/font-awesome',
    ngAnimate:'lib/angular-animate/angular-animate.min',
    angularPdf:'lib/angular-pdf/dist/angular-pdf.min',
    pdfjs:'lib/pdfjs-dist/build/pdf',
    ionicons:'lib/ionicons/css/ionicons.min'
    },
  /**
   * for lib that either do not support AMD out of the box, or
   * require some fine tuning to dependency mgt'
   */
  shim: {
    angular: {'exports': 'angular'},
    angularResource: ['angular'],
    angularSanitize: ['angular'],
    ngAnimate: ['angular'],
    uiRouter: ['angular'],
    uiBootstrap: ['angular'],
    angularPdf:['angular','pdfjs'],
    bootstrap: ['jquery']
  },
  map: {
    '*': {
      text: 'lib/requirejs-text/text',
      css: 'lib/require-css/css'
    }
  },
  deps: [
    './js/bootstrap'
  ]

});
