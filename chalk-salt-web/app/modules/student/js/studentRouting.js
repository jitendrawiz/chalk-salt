'use strict';

define([ 'angular', 'text!../templates/studentProfile.tpl.html','text!../templates/Profile.tpl.html'], function(angular, templateHome,templateProfile) {
    
    var homeRouter = angular.module('Student.router', []);

    homeRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.student', {
            url : '/student',
            template : templateHome,
            controller : 'StudentController as stdCtrl'
        }).state('chalkanddust.profile', {
            url : '/profile',
            template : templateProfile,
            controller : 'StudentController as stdCtrl'
        });
    } ]);
});