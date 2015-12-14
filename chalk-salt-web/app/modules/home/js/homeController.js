'use strict';

define([ 'angular', './homeRouting', './homeService','css!mainCss',
         'css!cssSliderCss','css!animateCss','css!carouselCss','css!iconCss',
         'css!bootStrapCss','css!fontAwesomeCss','css!commonCss' ], function(angular) {

    var loginModule = angular.module('Home.controller', [ 'Home.router', 'System.configuration', 'Home.service','fontAwesomeCss',
                                                          'bootStrapCss','iconCss','carouselCss','animateCss','mainCss','cssSliderCss','commonCss']);
    
    loginModule.controller('HomeController', [ '$scope', '$state', '$resource','$rootScope', 'PROPCO', 'HomeService',
            function($scope, $state, $resource,$rootScope, PROPCO, HomeService) {

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
    	       showAlert("i am in");
                $scope.authRequest = {};
                $scope.version = PROPCO.VERSION;
                $scope.build = PROPCO.BUILD;
                $scope.email = PROPCO.EMAIL;
                $scope.releaseDate = PROPCO.RELEASE_DATE;
                $rootScope.userName = {};
           /*     this.authenticateUser = function() {
                    LoginService.save({}, $scope.authRequest, function(response) {
                        if(response){
                            $rootScope.userName = response.fullName;
                            console.log($rootScope.userName);
                            $state.go('propco.agent.home');
                            console.log(response);
                        }
                    }, function(error) {
                    	showAlert('danger',error.data.message);
                    });
                };*/
                
            }
    ]);
});