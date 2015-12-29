'use strict';

define([ 'angular', './loginRouting', './loginService' ], function(angular) {

    var loginModule = angular.module('Login.controller', [ 'Login.router', 'System.configuration', 'Login.service']);
    
    loginModule.controller('LoginController', [ '$scope', '$state', '$resource','$rootScope', 'CHALKNDUST', 'LoginService',
            function($scope, $state, $resource,$rootScope, CHALKNDUST, LoginService) {
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
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                $rootScope.userInfo = {};
                this.authenticateUser = function() {
                    LoginService.save({}, $scope.authRequest, function(response) {
                        if(response){
                            $rootScope.fullName = response.fullName;
                            //console.log($rootScope.fullName);
                            $rootScope.securUuid=response.securUuid;
                            $state.go('chalkanddust.student');
                            console.log(response);
                           
                        }
                    }, function(error) {
                    	showAlert('danger',error.data.message);
                    });
                };
               
			}
    	]);


/**
 * Log out Controller
 */
loginModule.controller('LogoutController', [ '$scope', '$state', 'LogoutService', '$window',
        function($scope, $state, LogoutService, $window) {

            /**
             * Code to display/close alert on UI
             */
            $scope.alert = {};
            $scope.alert.show = false;

            var showAlert = function(type, message) {
                $scope.alert = {};
                $scope.alert.type = type;
                $scope.alert.message = message;
                $scope.alert.show = true;

                $window.scrollTo(0, 0);
            };

            $scope.closeAlert = function() {
                $scope.alert = {};
                $scope.alert.show = false;
            };
            /**
             * Service call to logging out of the application
             */
            LogoutService.logout({}, function() {
            }, function() {
                showAlert('danger', 'There was some problem while logging out.');
            });
            $state.go('chalkanddust.home');
        }]);
});
