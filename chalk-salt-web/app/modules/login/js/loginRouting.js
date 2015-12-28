'use strict';

define([ 'angular', 'text!../templates/login.tpl.html', 'text!../../home/templates/home.tpl.html'], function(angular, templateLogin, templateHome) {
    
    var loginRouter = angular.module('Login.router', []);

    loginRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.login', {
            url : '/login',
            template : templateLogin,
            controller : 'LoginController as loginCtrl'
        }).state('chalkanddust.logout', {
            url : '/logout',
            template : templateHome,
            controller : 'LogoutController as logoutCtrl'
        });
    } ]);
});