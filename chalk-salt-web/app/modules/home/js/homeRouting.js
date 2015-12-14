'use strict';

define([ 'angular', 'text!../templates/home.tpl.html'], function(angular, templateLogin) {
    
    var homeRouter = angular.module('Home.router', []);

    homeRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.home', {
            url : '/home',
            template : templateLogin,
            controller : 'HomeController as homeCtrl'
        });
    } ]);
});