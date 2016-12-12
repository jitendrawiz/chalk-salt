'use strict';

define([ 'angular', './loginRouting', './loginService' ], function(angular) {

    var loginModule = angular.module('Login.controller', [ 'Login.router', 'System.configuration', 'Login.service']);
    
    loginModule.controller('LoginController', ['$window', '$scope', '$state', '$resource','$rootScope', 'CHALKNDUST', 'LoginService',
            function($window,$scope, $state, $resource,$rootScope, CHALKNDUST, LoginService) {
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
                $scope.authRequest.rememberMe=false;
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                $rootScope.contact_number1 = CHALKNDUST.CONTACT_NUMBER1;
                $rootScope.contact_number2 = CHALKNDUST.CONTACT_NUMBER2;
                $rootScope.contact_email = CHALKNDUST.CONTACT_EMAIL;
                $rootScope.copy_right = CHALKNDUST.COPY_RIGHT;
                this.authenticateUser = function() {
                	
                    LoginService.save({}, $scope.authRequest, function(response) {
                        if(response){
                            $rootScope.username = $scope.authRequest.username;
                            $window.localStorage.setItem(CHALKNDUST.SECURUUID,response.securUuid);
                            $window.localStorage.setItem(CHALKNDUST.USERFULLNAME,response.fullName);
                            $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);
                            $window.localStorage.setItem(CHALKNDUST.USERNAME,response.userName);
                            if($window.localStorage.getItem(CHALKNDUST.USERNAME)=="admin"){
                            $state.go('chalkanddust.adminhome');
                            }
                            else{
                            $state.go('chalkanddust.profile');
                            }	
                            console.log(response);
                           
                        }
                    }, function(error) {
                      if(error.data==null){
                        showAlert('danger',"Oops it seems like network problem has occurred.Please revisit once network is back");
                      }else{
                    	showAlert('danger',error.data.message);
                    	}
                    });
                };
               
			}
    	]);


/**
 * Log out Controller
 */
loginModule.controller('LogoutController', [ '$scope', '$state', 'LogoutService', '$window', '$rootScope','CHALKNDUST',
        function($scope, $state, LogoutService, $window, $rootScope,CHALKNDUST) {

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
            function resetLocalStorage(){
            	$window.localStorage.removeItem(CHALKNDUST.SECURUUID);
                $window.localStorage.removeItem(CHALKNDUST.USERFULLNAME);
                $window.localStorage.removeItem(CHALKNDUST.EDITFLAG);
                $window.localStorage.removeItem(CHALKNDUST.USERNAME);
                $window.localStorage.removeItem(CHALKNDUST.CLASSID);
                $window.localStorage.removeItem(CHALKNDUST.SUBJECTID);
                $window.localStorage.removeItem(CHALKNDUST.TABNUMBER);
                $window.localStorage.removeItem(CHALKNDUST.SUBJECTNAME);
                $window.localStorage.removeItem(CHALKNDUST.QUESTIONDATA);
                $window.localStorage.removeItem(CHALKNDUST.TESTTYPE);
                clearTimeout($rootScope.timeoutvariable);
                $window.localStorage.removeItem(CHALKNDUST.TIMERTIME);
                $window.localStorage.removeItem(CHALKNDUST.SCHEDULETESTUUID);
                $window.localStorage.removeItem(CHALKNDUST.TESTGROUPID);

                
            };
            /**
             * Service call to logging out of the application
             */
            LogoutService.logout({}, function(res) {
            	$rootScope.fullName="";
            	$rootScope.username="";
            	$rootScope.securUuid="";
            	resetLocalStorage();            	
                $state.go('chalkanddust.home');
            }, function(error) {
                showAlert('danger', 'There was some problem while logging out.');
            });
            
        }]);
});
