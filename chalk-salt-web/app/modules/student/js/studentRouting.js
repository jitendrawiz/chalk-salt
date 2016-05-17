'use strict';

define([ 'angular', 'text!../templates/studentProfile.tpl.html', 'text!../templates/adminProfile.tpl.html',
         'text!../templates/adminHome.tpl.html','text!../templates/admin/StudentDetails.tpl.html',
         'text!../templates/admin/ExamQuestionMaster.tpl.html','text!../templates/admin/ExamQuestionList.tpl.html',,'text!../templates/studentDashboard/testInstruction.tpl.html'],
		function(angular, templateHome,templateProfile,adminprofiletemplate,adminhometemplate,studentdetailstemplate, 
				examQuestionMasterTemplate, examQuestionListTemplate, testInstructionTemplate) {
    
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
        }).state('chalkanddust.testInstruction',{
          url : '/student/testInstruction',
          template : testInstructionTemplate,
          controller : 'StudentController as stdCtrl'           
      }).state('chalkanddust.questioncreate',{
        	url : '/admin/exams/questions/create',
            template : examQuestionMasterTemplate,
            controller : 'AdminController as adminCtrl'        		
        }).state('chalkanddust.questionlist',{
        	url : '/admin/exams/questions/list',
            template : examQuestionListTemplate,
            controller : 'AdminController as adminCtrl'        		
        });
    } ]);
});