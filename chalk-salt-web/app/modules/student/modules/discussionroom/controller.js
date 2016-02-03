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
                $window.localStorage.setItem(CHALKNDUST.SUBJECTID,$scope.subjectId);
                
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
            '$stateParams',
            '$window','GetCommmentsOfTopicService',
            function($scope, CHALKNDUST,$state,$stateParams,$window,GetCommmentsOfTopicService) {
                $scope.alert = {};
                $scope.alert.show = false;
                $scope.currentDate = new Date();
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
                $scope.subjectId = $window.localStorage.getItem(CHALKNDUST.SUBJECTID);
                $scope.topicId = $stateParams.topicId;
                
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
                
                GetCommmentsOfTopicService.query({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId}, function(response) { 
                    if(response){
                    	$scope.commentsList = response;
                    	console.log(response);
                    	$scope.totalItems = $scope.commentsList.length;
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
                            $scope.commentListDetails = $scope.commentsList.slice(begin, end);
                        });
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                //showAlert('Success',$scope.topicId);
               // alert($scope.topicId);

            } ]);

});