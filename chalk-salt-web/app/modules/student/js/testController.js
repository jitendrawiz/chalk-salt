'use strict';

define([ 'angular', '../../CandDModal/js/CandDModalService' ], function(angular) {

  var testModule = angular.module('Student.testcontroller', [ 'System.configuration', 'CandDModal' ]);

  testModule.controller('TestController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', '$stateParams',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams) {

        var showAlert = function(type, message) {
          $scope.alert = {};
          $scope.alert.type = type  ;
          $scope.alert.message = message;
          $scope.alert.show = true;
        };

        $scope.closeAlert = function() {
          $scope.alert = {};
          $scope.alert.show = false;
          return true;
        };
        $scope.securUuid = $window.localStorage.getItem(CHALKNDUST.SECURUUID);
        $scope.fullName = $window.localStorage.getItem(CHALKNDUST.USERFULLNAME);

        console.log($stateParams.type);
        $window.localStorage.setItem(CHALKNDUST.TESTTYPE, $stateParams.type)
        
        $scope.goBackToDashBoard = function() {
          $state.go('chalkanddust.profile');
        };
        
      } ]);
});