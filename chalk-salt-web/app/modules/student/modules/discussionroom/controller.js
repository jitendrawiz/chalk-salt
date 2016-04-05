'use strict';
define([ 'angular', './routing', './service','../../../CandDModal/js/CandDModalService' ], function(angular) {
    var module = angular.module('Student.discussionroom.controller', ['Student.discussionroom.routing', 'Student.discussionroom.service','CandDModal' ]);

    module.controller('DiscussionRoomSubjectsController', ['$element','$timeout', '$scope', '$state', 'CHALKNDUST',  '$window', 'GetUserDetailsService', 'GetTopicStatistics',   
            function($element,$timeout,$scope, $state, CHALKNDUST,$window,GetUserDetailsService,GetTopicStatistics) {
    			
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
                
                this.goBackToProfileScreen = function() {
                	console.log("Going back to profile screen");
                	 $state.go('chalkanddust.profile');
                };
                
                this.openTopicRequest = function() {
                	console.log("open topic request page");
                	$state.go('chalkanddust.topicrequest');
                };
                
                var myElements = $element.find('.requestTopicButton');
                function showElement() {
                	myElements.css("background", "Aqua");               
                    $timeout(hideElement, 1000);
                }

                function hideElement() {
                	myElements.css("background", "Wheat");                
                    $timeout(showElement, 1000);
                }
                showElement();
            } ]);

    module.controller('DiscussionRoomTopicController',
    		['$element','$timeout', '$scope', 'CHALKNDUST', '$state',  '$window','$stateParams',
    		 'GetTopicsService','getSubjectNameService','filterFilter', 
            
            function($element,$timeout,$scope, CHALKNDUST, $state, $window, $stateParams, GetTopicsService, 
            		getSubjectNameService, filterFilter) {

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
                    	// pagination controls
                    	$scope.currentPage = 1;
                    	$scope.totalItems = $scope.topicList.length;
                    	$scope.itemsPerPage = 10; // items per page
                    	$scope.maxSize = Math.ceil($scope.totalItems / $scope.itemsPerPage);

                    	// $watch search to update pagination
                    	$scope.$watch('search', function (newVal, oldVal) {
                    		$scope.topicDetails = filterFilter($scope.topicList, newVal);
                    		$scope.totalItems = $scope.topicDetails.length;
                    		$scope.maxSize = Math.ceil($scope.totalItems / $scope.itemsPerPage);
                    		$scope.currentPage = 1;
                    	}, true);
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                this.goBackToSubjectsScreen = function() {
                	console.log("Going back to subjects screen");
                    $state.go("chalkanddust.discussionroomsubjects");
                };
                
                this.openTopicRequest = function() {
                   	console.log("open topic request page");
                   	$state.go('chalkanddust.topicrequest');
                };
                   
                var myElements = $element.find('.requestTopicButton');
                function showElement() {
                	myElements.css("background", "Aqua");               
                    $timeout(hideElement, 1000);
                }

                function hideElement() {
                	myElements.css("background", "Wheat");                
                    $timeout(showElement, 1000);
                }
                
                showElement();
            } ]);

    module.controller('DiscussionRoomCommentsController', ['$element','$timeout',
            '$scope',
            'CHALKNDUST',
            '$state',
            '$stateParams',
            '$window','GetCommmentsOfTopicService','CommentService',
            'topicDetailsService','getDetailsOfCommentService','updateCommentDetailsService','CandDModalService','$log','filterFilter',
            function($element,$timeout,$scope, CHALKNDUST,$state,$stateParams,$window,GetCommmentsOfTopicService,CommentService,
            		topicDetailsService,getDetailsOfCommentService,updateCommentDetailsService,CandDModalService,$log,filterFilter) {
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
                             var modalOptions = {
                                     header : 'Note',
                                     body : 'Your comment added Successfully',
                                     btn : 'OK'
                                 };

                             CandDModalService.showModal({}, modalOptions).then(function(result) {
                                     $log.info(result);
                                 });
                             $state.reload();
                         }
                         
                     }, function(error) {
                         showAlert('danger', error.data.message);
                     });                   
                	   }else{
                		   var modalOptions = {
                                   header : 'Note',
                                   body : 'Please fill your comments!!',
                                   btn : 'OK'
                               };

                           CandDModalService.showModal({}, modalOptions).then(function(result) {
                                   $log.info(result);
                               });

                   }
                };
                
                GetCommmentsOfTopicService.query({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId}, function(response) { 
                    if(response){
                    	$scope.commentsList = response;
                    	// pagination controls
                    	$scope.currentPage = 1;
                    	$scope.totalItems = $scope.commentsList.length;
                    	$scope.itemsPerPage = 10; // items per page
                    	$scope.maxSize = Math.ceil($scope.totalItems / $scope.itemsPerPage);

                    	// $watch search to update pagination
                    	$scope.$watch('search', function (newVal, oldVal) {
                    		$scope.commentListDetails = filterFilter($scope.commentsList, newVal);
                    		$scope.totalItems = $scope.commentListDetails.length;
                    		$scope.maxSize = Math.ceil($scope.totalItems / $scope.itemsPerPage);
                    		$scope.currentPage = 1;
                    	}, true);

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
                            var modalOptions = {
                                    header : 'Note',
                                    body : 'Your Comment is updated successfully',
                                    btn : 'OK'
                                };

                            CandDModalService.showModal({}, modalOptions).then(function(result) {
                                    $log.info(result);
                                });
                            $state.reload();
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
                this.goBackToTopicsScreen = function() {
                	console.log("Going back to Topics screen");
                	$state.go('chalkanddust.discussionroomtopics', {'subjectId': $scope.subjectId});
                           
                };
                
                this.openTopicRequest = function() {
                	console.log("open topic request page");
                	$state.go('chalkanddust.topicrequest');
                };
                
                var myElements = $element.find('.requestTopicButton');
                function showElement() {
                	myElements.css("background", "Aqua");               
                    $timeout(hideElement, 1000);
                }

                function hideElement() {
                	myElements.css("background", "Wheat");                
                    $timeout(showElement, 1000);
                }
                showElement();
            } ]);
    
    module.controller('DiscussionRoomTopicRequestController', ['$log','$element','$timeout', '$scope', '$state', '$filter', 'CHALKNDUST', 'CandDModalService',  
                                                               '$window', 'GetUserDetailsService', 'GetTopicStatistics','SaveTopicRequest', 'UpdateTopicImageService',   
       function($log, $element, $timeout, $scope, $state, $filter, CHALKNDUST, CandDModalService, $window, GetUserDetailsService, GetTopicStatistics, SaveTopicRequest, UpdateTopicImageService) {
		
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
           
           this.goBackToTopicsScreen = function() {
           	console.log("Going back to Topics screen");
           	$state.go('chalkanddust.discussionroomsubjects');
                      
           };
           
           this.openTopicRequest = function() {
           	console.log("open topic request page");
           	$state.go('chalkanddust.topicrequest');
           };
           
           this.saveTopicRequest = function(fileData) {
               if($scope.topicRequestInfo!=null && $scope.topicRequestInfo!=""){
            	   $scope.topicRequestInfo.securUuid=$scope.securUuid; 
            	   $scope.topicRequestInfo.classId=$scope.classId;
            	   $scope.topicRequestInfo.requestDate=$filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss');
            	   
            	   console.log($scope.topicRequestInfo);
            	   SaveTopicRequest.save({}, $scope.topicRequestInfo, function(
                         response) {
                     if (response) {
                         console.log(response);
                         
                         if(!angular.isUndefined(fileData)){
                          	updateTopicPhoto(fileData,response.securUuid);
                          }
                         var modalOptions = {
                                 header : 'Note',
                                 body : 'Your topic request added Successfully',
                                 btn : 'OK'
                             };
                         	
                         CandDModalService.showModal({}, modalOptions).then(function(result) {
                                 $log.info(result);
                             });
                         $state.reload();
                     }
                     
                 }, function(error) {
                     showAlert('danger', error.data.message);
                 });                   
            	   }else{
            		   var modalOptions = {
                               header : 'Note',
                               body : 'Please fill your topic details!!',
                               btn : 'OK'
                           };

                       CandDModalService.showModal({}, modalOptions).then(function(result) {
                               $log.info(result);
                           });

               }
            };
           
           var myElements = $element.find('.requestTopicButton');
           function showElement() {
           	myElements.css("background", "Aqua");               
               $timeout(hideElement, 1000);
           }

           function hideElement() {
           	myElements.css("background", "Wheat");                
               $timeout(showElement, 1000);
           }
           
           /**
            * Function to upload Topic photo
            */
           var updateTopicPhoto = function (fileData,securUuid) {
               var file = fileData;
               var formData = new FormData();
               formData.append('file', file);
               formData.append('name', file.name);
               formData.append('documentType', file.type);
               UpdateTopicImageService.upload(formData, securUuid, function(response) {
                   showAlert("success", "Topic Image updated successfully.");                        
               }, onRequestFailure);
           };
           
           function onRequestFailure(error) {
               showAlert('danger', error.data.message);
          };
           showElement();
       } ]);

});