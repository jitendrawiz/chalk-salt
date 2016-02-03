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
                
                GetTopicStatistics.query({studentClassId:$scope.classId}, function(response) { 
                    if(response){
                    	 $scope.topicStatistics = response;
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

    module.controller('DiscussionRoomTopicController', [ '$scope', 'CHALKNDUST', '$state',  '$window','$stateParams','GetTopicsService',
            
            function($scope, CHALKNDUST, $state, $window,$stateParams,GetTopicsService) {

    	        $scope.alert = {};
                $scope.alert.show = false;
                $scope.currentDate = new Date();
                
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
                               
                $scope.subjectId = $stateParams.subjectId;
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
                
                GetTopicsService.query({classId:$scope.classId, subjectId:$scope.subjectId}, function(response) { 
                    if(response){
                    	$scope.topicList = response;
                    	console.log(response);
                    	$scope.totalItems = $scope.topicList.length;
                        $scope.currentPage = 1;
                        $scope.itemsPerPage = 10;

                        $scope.setPage = function(pageNo) {
                            $scope.currentPage = pageNo;
                        };

                        $scope.pageCount = function() {
                            return Math.ceil($scope.domains.length / $scope.itemsPerPage);
                        };

                        $scope.$watch('currentPage + itemsPerPage', function() {
                            var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin + $scope.itemsPerPage;
                            $scope.topicDetails = $scope.topicList.slice(begin, end);
                        });
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                
            } ]);

    module.controller('DiscussionRoomCommentsController', [
            '$scope',
            'CHALKNDUST',
            '$state',
            '$window',
            function($scope, CHALKNDUST,$state,$window) {
                $scope.alert = {};
                $scope.alert.show = false;
                $scope.currentDate = new Date();
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
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