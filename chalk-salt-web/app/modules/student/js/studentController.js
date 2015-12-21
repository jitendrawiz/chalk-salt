'use strict';

define([ 'angular', './studentRouting', './studentService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service']);
    
    homeModule.controller('StudentController', [ '$scope', '$state', '$resource','$rootScope', 'CHALKNDUST', 'GetUserDetailsService',
            function($scope, $state, $resource,$rootScope, CHALKNDUST, GetUserDetailsService) {

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
                $scope.securUuid=$rootScope.securUuid;
                console.log($rootScope.securUuid);
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                $rootScope.studentName = {};
                //debugger;
                GetUserDetailsService.get({securUuid:$scope.securUuid},  function(response) {
                    if(response){
                    //	debugger;
                    	 $scope.userInfo = response;
                        // SystemUtil.setItem("userInfo",response);
                         console.log(JSON.stringify($scope.userInfo));
                        // $scope.userDetails= {};
                        
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
               
                
            }
    ]);
});