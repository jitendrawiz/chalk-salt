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
    candDCss: 'resources/css/propco-enterprise',
    uiBootstrap: 'lib/angular-bootstrap/ui-bootstrap-tpls',
    fontAwesome: '/lib/fontawesome/css/font-awesome',
    angularTranslate: 'lib/angular-translate/angular-translate.min',
    angularTranslateStaticFile: 'lib/angular-translate-loader-static-files/angular-translate-loader-static-files.min',
    angularDragDrop:'lib/angular-dragdrop/src/angular-dragdrop.min',
    fontAwesomeCss:'resources/css/font-awesome.min',
    bootStrapCss:'resources/css/bootstrap.min',
    iconCss:'resources/css/ionicons.min',
    carouselCss:'resources/css/owl.carousel',
    animateCss:'resources/css/animate',
    mainCss:'resources/css/main',
    cssSliderCss:'resources/cssslider_files/csss_engine1/style',
    commonCss:'resources/css/Common',
    angularDragDrop:'lib/angular-dragdrop/src/angular-dragdrop.min'

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
    uiRouter: ['angular'],
    uiBootstrap: ['angular'],
    angularTranslate: ['angular'],
    angularTranslateStaticFile: ['angular','angularTranslate'],
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
