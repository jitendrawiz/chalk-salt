'use strict';

define([ 'angular', './loginRouting', './loginService' ], function(angular) {

    var loginModule = angular.module('Login.controller', [ 'Login.router', 'System.configuration', 'Login.service']);
    
    loginModule.controller('LoginController', [ '$scope', '$state', '$resource', 'PROPCO', 'LoginService',
            function($scope, $state, $resource, PROPCO, LoginService) {

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
    	       
                $scope.authRequest = {};
                $scope.version = PROPCO.VERSION;
                $scope.build = PROPCO.BUILD;
                $scope.email = PROPCO.EMAIL;
                $scope.releaseDate = PROPCO.RELEASE_DATE;
                
                this.authenticateUser = function() {
                    LoginService.save({}, $scope.authRequest, function(response) {
                        if(response){
                            $state.go('propco.agent.home');
                        }
                    }, function(error) {
                    	showAlert('danger',error.data.message);
                    });
                };
                
            }
    ]);
});