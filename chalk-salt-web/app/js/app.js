'use strict';

define([ 'angular', 'uiRouter', 'uiBootstrap', 'angularResource', 'css!bootstrap', 'css!candDCss',
         'jquery', 'jqueryUI', 'angularTranslate',
        'angularTranslateStaticFile', 'angularSanitize', 'css!fontAwesome','ngAnimate','angularPdf','pdfjs','css!ionicons'], function(angular) {

    var chalkAndDust = angular.module('chalkAndDust', [ 'ui.router', 'ngResource',
     'Home.controller', 'Login.controller', 'Registration.controller','Student.controller',
     'httpInterceptor', 'System.configuration', 'pascalprecht.translate', 'ngSanitize', 'ui.bootstrap','ngAnimate','pdf']);

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
