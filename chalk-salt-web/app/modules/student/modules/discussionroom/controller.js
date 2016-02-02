'use strict';
define([ 'angular', './routing', './service' ], function(angular) {
    var module = angular.module('Student.discussionroom.controller', ['Student.discussionroom.routing', 'Student.discussionroom.service' ]);

    module.controller('DiscussionRoomSubjectsController', [ '$scope', '$state', 'CHALKNDUST',  '$window', 'GetUserDetailsService', 'GetTopicStatistics',   
            function($scope, $state, CHALKNDUST,$window,GetUserDetailsService,GetTopicStatistics) {
    			
                $scope.alert = {};
                $scope.alert.show = false;
                $scope.currentDate = new Date();
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
                
              //  $scope.topicDetails=[];
              //  $scope.topicStatistics="";
                GetTopicStatistics.query({studentClassId:$scope.classId}, function(response) { 
                    if(response){
                    	 //$scope.topicDetails = response;
                    	 $scope.topicStatistics = response;
                    	// console.log($scope.topicStatistics);
                    	console.log(response);
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });                	
               
                
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

                    return true;
                };
                
                
            } ]);

    module.controller('DiscussionRoomTopicController', [ '$scope', 'CHALKNDUST', '$state',  '$window','$stateParams',
            
            function($scope, CHALKNDUST, $state, $window,$stateParams) {

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

                    return true;
                };
                var subjectId = $stateParams.subjectId;
console.log(subjectId);
                
            } ]);

    module.controller('DiscussionRoomCommentsController', [
            '$scope',
            'CHALKNDUST',
            '$state',
            '$window',
            function($scope, CHALKNDUST,$state,$window) {
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

                    return true;
                };

            } ]);

});