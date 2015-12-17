'use strict';

define([ 'angular', 'uiRouter', 'uiBootstrap', 'angularResource', 'css!bootstrap', 'css!candDCss', 'jquery', 'jqueryUI', 'angularTranslate',
        'angularTranslateStaticFile', 'angularSanitize', 'css!fontAwesome'], function(angular) {

    var chalkAndDust = angular.module('chalkAndDust', [ 'ui.router', 'ngResource','Home.controller', 'Login.controller', 'Registration.controller','httpInterceptor', 'System.configuration', 'pascalprecht.translate', 'ngSanitize', 'ui.bootstrap']);

    chalkAndDust.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
        /*
         * https://scotch.io/tutorials/internationalization-of-angularjs-applications
         */$translateProvider.useStaticFilesLoader({
            prefix : './resources/i18n/locale-',
            suffix : '.json'
        });
        $translateProvider.preferredLanguage('en_US');
        $translateProvider.useSanitizeValueStrategy('sanitize');

        $stateProvider.state('chalkanddust', {
            url : '/chalkanddust',
            abstract : true,
            template : '<ui-view/>'
        });
        $urlRouterProvider.otherwise('/chalkanddust/home');
    });

    return chalkAndDust;
});
