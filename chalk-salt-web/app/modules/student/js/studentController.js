'use strict';

define([ 'angular', './studentRouting', './studentService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service']);
    
    homeModule.controller('StudentController', ['$window', '$scope', '$state', '$resource', '$location', '$rootScope', 'CHALKNDUST', 'GetUserDetailsService',
            function($window,$scope, $state, $resource, $location, $rootScope, CHALKNDUST, GetUserDetailsService) {

    		   $scope.editFlag = false;
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
               $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
               $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
               
               $scope.backToDashBoard=function(){
            	   $state.go('chalkanddust.profile');  
               };
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                GetUserDetailsService.get({securUuid:$scope.securUuid},  function(response) {
                    if(response){
                    	 $scope.userInfo = response;
                    	 $scope.subjects =$scope.userInfo.subjects;
                    	 
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
            }
    ]);
});