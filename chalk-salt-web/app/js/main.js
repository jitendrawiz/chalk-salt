require.config({
  baseUrl: '.',
  paths: {
    angular: 'lib/angular/angular.min',
    domReady: 'lib/requirejs-domready/domReady',
    angularSanitize: 'lib/angular-sanitize/angular-sanitize.min',
    uiRouter: 'lib/angular-ui-router/release/angular-ui-router.min',
    angularResource: 'lib/angular-resource/angular-resource.min',
    text: 'lib/requirejs-text/text',
    css: 'lib/require-css/css',
    bootstrap: 'lib/bootstrap/dist/css/bootstrap',
    jquery: 'lib/jquery/dist/jquery.min',
    jqueryUI: 'lib/jquery-ui/jquery-ui.min',
    candDCss: 'resources/css/chalk-Dust',
    uiBootstrap: 'lib/angular-bootstrap/ui-bootstrap-tpls',
    fontAwesome: 'lib/fontawesome/css/font-awesome',
    angularTranslate: 'lib/angular-translate/angular-translate.min',
    angularTranslateStaticFile: 'lib/angular-translate-loader-static-files/angular-translate-loader-static-files.min',
    angularDragDrop:'lib/angular-dragdrop/src/angular-dragdrop.min',
    ngAnimate:'lib/angular-animate/angular-animate.min',
    angularPdf:'lib/angular-pdf/dist/angular-pdf.min',
    pdfjs:'lib/pdfjs-dist/build/pdf',
    ionicons:'lib/ionicons/css/ionicons.min'
    	//angularPrettyCheckable: 'lib/angular-pretty-checkable/dist/angular-pretty-checkable.min'
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
    angularTranslate: ['angular'],
    angularTranslateStaticFile: ['angular','angularTranslate'],
    angularPdf:['angular','pdfjs'],
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
