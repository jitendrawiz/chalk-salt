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

    module.controller('DiscussionRoomTopicController',
    		[ '$scope', 'CHALKNDUST', '$state',  '$window','$stateParams','GetTopicsService','getSubjectNameService',
            
            function($scope, CHALKNDUST, $state, $window,$stateParams,GetTopicsService,getSubjectNameService) {

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
                
                getSubjectNameService.get({classId:$scope.classId, subjectId:$scope.subjectId}, function(response) { 
                    if(response){
                    	$scope.subjectName=response.subjectName;
                 }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });                
                
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
                            return Math.ceil($scope.topicList.length / $scope.itemsPerPage);
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
            '$window','GetCommmentsOfTopicService','CommentService','topicDetailsService','getDetailsOfCommentService','updateCommentDetailsService',
            function($scope, CHALKNDUST,$state,$stateParams,$window,GetCommmentsOfTopicService,CommentService,topicDetailsService,getDetailsOfCommentService,updateCommentDetailsService) {
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
                
                topicDetailsService.get({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId}, function(response) { 
                    if(response){
                        console.log("resoibse aere"+response);
                    	$scope.topicTitle=response.topicTitle;
                    	$scope.topicDescription=response.topicDescription;
                	                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                this.saveComment = function() {
                   if($scope.commentsinfo!=null && $scope.commentsinfo!=""){
                	   $scope.commentsinfo.classId=$scope.classId;
                	   $scope.commentsinfo.subjectId=$scope.subjectId;
                	   $scope.commentsinfo.discussionTopicId=$scope.topicId;
                	   $scope.commentsinfo.userSecurUuid=$scope.securUuid;                	   
                	   console.log($scope.commentsinfo);
                     CommentService.save({}, $scope.commentsinfo, function(
                             response) {
                         if (response) {
                             console.log(response);
                             alert("Your comment added Successfully");
                             $state.reload();
                         }
                         
                     }, function(error) {
                         showAlert('danger', error.data.message);
                     });                   
                	   }else{
                	   alert("Please fill your comments");
                   }
                };
                
                GetCommmentsOfTopicService.query({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId}, function(response) { 
                    if(response){
                    	$scope.commentsList = response;
                    	console.log(response);
                    	$scope.totalItems = $scope.commentsList.length;
                        $scope.currentPage = 1;
                        $scope.itemsPerPage = 40;

                        $scope.setPage = function(pageNo) {
                            $scope.currentPage = pageNo;
                        };

                        $scope.pageCount = function() {
                            return Math.ceil($scope.commentsList.length / $scope.itemsPerPage);
                        };

                        $scope.$watch('currentPage + itemsPerPage', function() {
                            var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin + $scope.itemsPerPage;
                            $scope.commentListDetails = $scope.commentsList.slice(begin, end);
                        });
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                
                this.editComment=function(commentUuid){
                    getDetailsOfCommentService.get({commentUuid:commentUuid}, function(response) { 
                        if(response){
                        	$scope.commentsinfo=response;
                        	console.log(response);
                        	$window.scrollTo(0, angular.element(document.getElementById('commentTextArea')).offsetTop);  
                        	$window.document.getElementById('commentTextArea').focus();
                        }
                    }, function(error) {
                    	showAlert('danger',error.data.message);
                    });
                	
                	
                };
                
                $scope.isEmpty = function(obj) {
               	 for(var prop in obj) {
               	      if(obj.hasOwnProperty(prop))
               	          return false;
               	  }
               	  return true;
                };
                

                this.updateComment = function() {
                    updateCommentDetailsService.save({}, $scope.commentsinfo, function(
                            response) {
                        if (response) {
                            console.log(response);
                            alert("Your Comment is updated successfully");
                            $state.reload();
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };

            } ]);

});