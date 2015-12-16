'use strict';

define([ 'angular', 'text!../templates/home.tpl.html'], function(angular, templateHome) {
    
    var homeRouter = angular.module('Home.router', []);

    homeRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.home', {
            url : '/home',
            template : templateHome,
            controller : 'HomeController as homeCtrl'
        });
    } ]);
});