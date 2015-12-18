'use strict';

define([ 'angular', './studentRouting', './studentService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service']);
    
    homeModule.controller('StudentController', [ '$scope', '$state', '$resource','$rootScope', 'CHALKNDUST', 'StudentService',
            function($scope, $state, $resource,$rootScope, CHALKNDUST, StudentService) {

    		   var showAlert = function(type, message){
                   $scope.alert = {};
                   $scope.alert.type = type;
                   $scope.alert.message = message;
                   $scope.alert.show = true;
               };
               
               $scope.closeAlert = function(){
                   $scope.alert = {};
                   $scope.alert.show = false;
                   return true;
               };
    	      //showAlert("hello");
                $scope.studentRequest = {};
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                $rootScope.studentName = {};
               this.studentSuccess = function() {
                          //  $state.go('chalkanddust.home');
                            console.log("i am in");
                };
                
            }
    ]);
});