'use strict';

define([ 'angular', 'uiRouter', 'uiBootstrap', 'angularResource', 'css!bootstrap', 'css!propCoCss', 'jquery', 'jqueryUI', 'angularTranslate',
        'angularTranslateStaticFile', 'angularSanitize', 'angularDragDrop','angularPrettyCheckable','css!fontAwesome'], function(angular) {

    var propcoEnterprise = angular.module('PropCoEnterprise', [ 'ui.router', 'ngResource', 'Login.controller', 'Agent.controller','Administration.controller',
            'Registration.controller', 'httpInterceptor', 'System.configuration', 'pascalprecht.translate', 'ngSanitize', 'ui.bootstrap']);

    propcoEnterprise.config(function($stateProvider, $urlRouterProvider, $translateProvider) {
        /*
         * https://scotch.io/tutorials/internationalization-of-angularjs-applications
         */$translateProvider.useStaticFilesLoader({
            prefix : './resources/i18n/locale-',
            suffix : '.json'
        });
        $translateProvider.preferredLanguage('en_US');
        $translateProvider.useSanitizeValueStrategy('sanitize');

        $stateProvider.state('propco', {
            url : '/propco',
            abstract : true,
            template : '<ui-view/>'
        });
        $urlRouterProvider.otherwise('/propco/login');
    });

    return propcoEnterprise;
});
