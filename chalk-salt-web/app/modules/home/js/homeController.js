'use strict';

define([ 'angular', './homeRouting', './homeService' ], function(angular) {

    var homeModule = angular.module('Home.controller', [ 'Home.router', 'System.configuration', 'Home.service']);
    
    homeModule.controller('HomeController', [ '$scope', '$state', '$resource','$rootScope', 'PROPCO', 'HomeService',
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
    	      showAlert("hello");
                $scope.authRequest = {};
                $scope.version = PROPCO.VERSION;
                $scope.build = PROPCO.BUILD;
                $scope.email = PROPCO.EMAIL;
                $scope.releaseDate = PROPCO.RELEASE_DATE;
                $rootScope.userName = {};
               this.login = function() {
                            $state.go('chalkanddust.home');
                            console.log("i am in");
                };
                
            }
    ]);
});