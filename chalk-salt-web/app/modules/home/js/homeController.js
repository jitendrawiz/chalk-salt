'use strict';

define([ 'angular', './homeRouting', './homeService' ], function(angular) {

    var homeModule = angular.module('Home.controller', [ 'Home.router', 'System.configuration', 'Home.service']);
    
    homeModule.controller('HomeController', [ '$window', '$scope', '$state', '$resource','$rootScope', 'CHALKNDUST', 'HomeService',
            function($window,$scope, $state, $resource,$rootScope, CHALKNDUST, HomeService) {

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
               this.backToTop=function(){
               	$window.scrollTo(0, 0);  
               };
               $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
               $scope.userName=$window.localStorage.getItem(CHALKNDUST.USERNAME);
               console.log($scope.fullName);
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
               this.login = function() {
                            $state.go('chalkanddust.home');
                            console.log("i am in");
                };
                
            }
    ]);
});