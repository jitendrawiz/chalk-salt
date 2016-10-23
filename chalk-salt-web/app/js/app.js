'use strict';

define([ 'angular', 'jquery', 'jqueryUI', 'uiRouter', 'uiBootstrap', 'angularResource', 'css!bootstrapCSS', 'css!candDCss', 'angularSanitize', 'css!fontAwesome', 'ngAnimate',
    'angularPdf', 'pdfjs', 'css!ionicons', 'bootstrap', 'ngMaterial', 'css!ngMaterialCSS', 'singlePageNav', 'owlCarousl', 'wow', 'easing', 'maincs' ], function(angular) {

  var chalkAndDust = angular.module('chalkAndDust', [ 'ui.router', 'ngResource', 'Home.controller', 'Login.controller', 'Registration.controller', 'Student.controller',
      'httpInterceptor', 'System.configuration', 'ngSanitize', 'ui.bootstrap', 'ngAnimate', 'pdf', 'ngMaterial' ]);

  chalkAndDust.config(function($stateProvider, $urlRouterProvider, $sceProvider) {

    $sceProvider.enabled(false);

    $stateProvider.state('chalkanddust', {
      url : '/chalkanddust',
      abstract : true,
      template : '<ui-view/>'
    });
    $urlRouterProvider.otherwise('/chalkanddust/home');
  });

  return chalkAndDust;
});
