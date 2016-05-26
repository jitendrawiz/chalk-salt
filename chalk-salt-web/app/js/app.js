'use strict';

define([ 'angular', 'uiRouter', 'uiBootstrap', 'angularResource', 'css!bootstrap', 'css!candDCss', 'jquery', 'jqueryUI', 'angularTranslate',
        'angularTranslateStaticFile', 'angularSanitize', 'css!fontAwesome','ngAnimate'], function(angular) {

    var chalkAndDust = angular.module('chalkAndDust', [ 'ui.router', 'ngResource','Home.controller', 'Login.controller', 'Registration.controller','Student.controller','httpInterceptor', 'System.configuration', 'pascalprecht.translate', 'ngSanitize', 'ui.bootstrap','ngAnimate']);

    chalkAndDust.config(function($stateProvider, $urlRouterProvider, $translateProvider,$sceProvider) {
        /*
         * https://scotch.io/tutorials/internationalization-of-angularjs-applications
         */$translateProvider.useStaticFilesLoader({
            prefix : './resources/i18n/locale-',
            suffix : '.json'
        });
        $translateProvider.preferredLanguage('en_US');
        $translateProvider.useSanitizeValueStrategy('sanitize');
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
