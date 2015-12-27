'use strict';

define([ 'angular', 'text!../templates/studentProfile.tpl.html'], function(angular, templateHome) {
    
    var homeRouter = angular.module('Student.router', []);

    homeRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.student', {
            url : '/student',
            template : templateHome,
            controller : 'StudentController as stdCtrl'
        });
    } ]);
});