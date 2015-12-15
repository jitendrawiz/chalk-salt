'use strict';

define([ 'angular', 'text!../templates/login.tpl.html'], function(angular, templateLogin) {
    
    var loginRouter = angular.module('Login.router', []);

    loginRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.login', {
            url : '/login',
            template : templateLogin,
            controller : 'LoginController as loginCtrl'
        });
    } ]);
});