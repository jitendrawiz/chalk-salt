'use strict';

define([ 'angular', '../../CandDModal/js/CandDModalService' ], function(angular) {

  var testModule = angular.module('Student.testcontroller', [ 'System.configuration', 'CandDModal' ]);

  testModule.controller('TestController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', '$stateParams', 'GetUserPhotoService',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService) {

        var showAlert = function(type, message) {
          $scope.alert = {};
          $scope.alert.type = type;
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

        $window.localStorage.setItem(CHALKNDUST.TESTTYPE, $stateParams.type);

        $scope.goBackToDashBoard = function() {
          $state.go('chalkanddust.profile');
        };

        $scope.startTest = function() {
          $state.go('chalkanddust.testscreen');
        };

        GetUserPhotoService.get({
          securUuid : $scope.securUuid
        }, function(result) {
          $scope.userProfilePhoto = result.photolink;
        }, function(error) {
          showAlert('danger', error.data.message);
        });
      } ]);

  testModule.controller('TestDetailController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', '$stateParams',
      'GetUserPhotoService', 'GetTestQuestionsListService',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService, GetTestQuestionsListService) {

        var showAlert = function(type, message) {
          $scope.alert = {};
          $scope.alert.type = type;
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
        $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
        $scope.subjectId = $window.localStorage.getItem(CHALKNDUST.SUBJECTID);
        $scope.type = $window.localStorage.getItem(CHALKNDUST.TESTTYPE);

        GetUserPhotoService.get({
          securUuid : $scope.securUuid
        }, function(result) {
          $scope.userProfilePhoto = result.photolink;
        }, function(error) {
          showAlert('danger', error.data.message);
        });

        GetTestQuestionsListService.query({
          classId : $scope.classId,
          subjectId : $scope.subjectId,
          type : $scope.type
        }, function(result) {
          $scope.questionListDetails = result;
         
          $scope.totalItems = $scope.questionListDetails.length;
             $scope.currentPage = 1;
             $scope.itemsPerPage = 1;
             $scope.maxSize = $scope.totalItems;

             $scope.setPage = function(pageNo) {
                 $scope.currentPage = pageNo;
             };

             $scope.pageCount = function() {
                 return Math.ceil($scope.questionListDetails.length / $scope.itemsPerPage);
             };

             $scope.$watch('currentPage + itemsPerPage', function() {
                 var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin + $scope.itemsPerPage;
                 $scope.questionList = $scope.questionListDetails.slice(begin, end);
             });
          
        }, function(error) {
          showAlert('danger', error.data.message);
        });
        
        $scope.submitAnswer = function(index,selectedOption) {
          console.log(index)
          console.log(selectedOption)
        };

      } ]);
});