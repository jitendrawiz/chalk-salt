'use strict';

define([ 'angular', 'text!../templates/studentProfile.tpl.html','text!../templates/studentHome.tpl.html',
         'text!../templates/adminProfile.tpl.html','text!../templates/adminHome.tpl.html',
         'text!../templates/admin/StudentDetails.tpl.html'],
		function(angular, templateHome,templateProfile,adminprofiletemplate,adminhometemplate,studentdetailstemplate) {
    
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
        }).state('chalkanddust.adminprofile',{
        	url : '/adminprofile',
            template : adminprofiletemplate,
            controller : 'AdminController as adminCtrl'        		
        }).state('chalkanddust.adminhome',{
        	url : '/adminhome',
            template : adminhometemplate,
            controller : 'AdminController as adminCtrl'        		
        }).state('chalkanddust.studentdetails',{
        	url : '/admin/:id/students',
            template : studentdetailstemplate,
            controller : 'AdminController as adminCtrl'        		
        });
    } ]);
});