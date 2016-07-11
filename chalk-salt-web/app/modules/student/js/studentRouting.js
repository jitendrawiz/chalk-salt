'use strict';

define([ 'angular', 'text!../templates/studentProfile.tpl.html', 'text!../templates/studentHome.tpl.html', 'text!../templates/adminProfile.tpl.html',
    'text!../templates/adminHome.tpl.html', 'text!../templates/admin/StudentDetails.tpl.html', 'text!../templates/admin/ExamQuestionMaster.tpl.html',
    'text!../templates/admin/ExamQuestionList.tpl.html', 'text!../templates/studentDashboard/testInstruction.tpl.html', 'text!../templates/studentDashboard/notes.tpl.html','text!../templates/exam/testScreen.tpl.html' ],
    function(angular, templateHome, templateProfile, adminprofiletemplate, adminhometemplate, studentdetailstemplate, examQuestionMasterTemplate, examQuestionListTemplate,
        testInstructionTemplate, notesTemplate,testScreenTemplate) {

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
        }).state('chalkanddust.adminprofile', {
          url : '/adminprofile',
          template : adminprofiletemplate,
          controller : 'AdminController as adminCtrl'
        }).state('chalkanddust.adminhome', {
          url : '/adminhome',
          template : adminhometemplate,
          controller : 'AdminController as adminCtrl'
        }).state('chalkanddust.studentdetails', {
          url : '/admin/:id/students',
          template : studentdetailstemplate,
          controller : 'AdminController as adminCtrl'
        }).state('chalkanddust.testInstruction', {
          url : '/student/exam/testInstruction/:type',
          template : testInstructionTemplate,
          controller : 'TestController as testCtrl'
        }).state('chalkanddust.questioncreate', {
          url : '/admin/exams/questions/create',
          template : examQuestionMasterTemplate,
          controller : 'AdminController as adminCtrl'
        }).state('chalkanddust.questionlist', {
          url : '/admin/exams/questions/list',
          template : examQuestionListTemplate,
          controller : 'AdminController as adminCtrl'
        }).state('chalkanddust.shownotes', {
          url : '/student/notes/:pdfName/:pdfUrl',
          template : notesTemplate,
          controller : 'StudentController as stdCtrl'
        }).state('chalkanddust.testscreen',{
          url: '/student/exam/testscreen',
          template: testScreenTemplate,
          controller: 'TestDetailController as testCtrl'
        });
      } ]);
    });