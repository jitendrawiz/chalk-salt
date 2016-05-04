'use strict';
define([ 'angular', './routing', './service','../../../CandDModal/js/CandDModalService' ], function(angular) {
    var module = angular.module('Student.discussionroom.controller', ['Student.discussionroom.routing', 'Student.discussionroom.service','CandDModal' ]);

    module.controller('DiscussionRoomFirstController', ['$element','$timeout', '$scope', '$state', 'CHALKNDUST',  '$window',
                                                        'GetTopicStatistics','GetTopicsService','$stateParams','$rootScope',   
            function($element,$timeout,$scope, $state, CHALKNDUST,$window,GetTopicStatistics,GetTopicsService,$stateParams,$rootScope) {
    			
                $scope.alert = {};
                $scope.alert.show = false;
                $scope.currentDate = new Date();
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                
                var classId=null;
                if($stateParams.classId!=""){
                  classId= $stateParams.classId;
                  $window.localStorage.setItem(CHALKNDUST.GUESTCLASSID,classId);
                }else{
                  classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
                  $window.localStorage.removeItem(CHALKNDUST.GUESTCLASSID);
                }
                if($window.localStorage.getItem(CHALKNDUST.GUESTCLASSID)!=null){
                  $scope.isGuest=true;
                 }else{
                   $scope.isGuest=false;
                 }
                $scope.classId=classId;                
                $scope.defaultSubjectId=null;
                $scope.defaultSubject=null;
                
                GetTopicStatistics.query({studentClassId:$scope.classId}, function(response) { 
                    if(response){
                    	 $scope.topicStatistics = response;
                    	 $scope.defaultSubjectId=$scope.topicStatistics[0].subjectId;
                    	 getDefaultSubjectTopic($scope.defaultSubjectId);
                    	 $scope.defaultSubject=$scope.topicStatistics[0].subjectName;
                    	console.log(response);
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                function getDefaultSubjectTopic(subjectId){
                if(subjectId!=null){
                	$scope.getTopicsUsingSubject(subjectId);
                }
                }
               
                
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
                
                $scope.getTopicsUsingSubject=function(subjectId){
                $window.localStorage.setItem(CHALKNDUST.SUBJECTID,subjectId);
               	 GetTopicsService.query({classId:$scope.classId, subjectId:subjectId}, function(response) { 
                        if(response){
                        	$scope.topicList = response;
                        	if($scope.topicList.length!=0){
                        	$scope.defaultSubject=$scope.topicList[0].subjectName;
                        }
                       }
                    }, function(error) {
                    	showAlert('danger',error.data.message);
                    });
               }
                this.goBackToProfileScreen = function() {
                	console.log("Going back to profile screen");
                	if($window.localStorage.getItem(CHALKNDUST.GUESTCLASSID)!=null){
                	  $window.localStorage.removeItem(CHALKNDUST.GUESTCLASSID);
                	  $window.localStorage.removeItem(CHALKNDUST.SUBJECTID);
                	  $state.go('chalkanddust.home');
                	 }else{
                	   $state.go('chalkanddust.profile');
                	 }
                };
                
                $scope.contactUs = function() {
                    $state.go('chalkanddust.contactus');
                };
            } ]);

    module.controller('DiscussionRoomSecondController',
    		['$element','$timeout', '$scope', 'CHALKNDUST', '$state',  '$window','$stateParams',
    		 'getSubjectNameService','filterFilter', 'GetCommmentsOfTopicService','CommentService',
             'topicDetailsService','getDetailsOfCommentService','updateCommentDetailsService','CandDModalService','$log','$rootScope',
            
            function($element,$timeout,$scope, CHALKNDUST, $state, $window, $stateParams, 
            		getSubjectNameService, filterFilter,GetCommmentsOfTopicService,CommentService,
            		topicDetailsService,getDetailsOfCommentService,updateCommentDetailsService,CandDModalService,$log,$rootScope) {

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
                if($window.localStorage.getItem(CHALKNDUST.GUESTCLASSID)!=null){
                  $scope.isGuest=true;
                 }else{
                   $scope.isGuest=false;
                 }            
                $scope.topicId= $stateParams.topicId;
                $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
                $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
                var classId=null;
                if($window.localStorage.getItem(CHALKNDUST.GUESTCLASSID)!=null){
                  classId= $window.localStorage.getItem(CHALKNDUST.GUESTCLASSID);
                }else{
                  classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
                }
                $scope.classId=classId;
                $scope.subjectId=$window.localStorage.getItem(CHALKNDUST.SUBJECTID);
                
                getSubjectNameService.get({classId:$scope.classId, subjectId:$scope.subjectId}, function(response) { 
                    if(response){
                    	$scope.subjectName=response.subjectName;
                 }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });                
                
                this.goBackToFirstPage = function() {
                    if($window.localStorage.getItem(CHALKNDUST.GUESTCLASSID)!=null){
                      $state.go('chalkanddust.discussionroomfirstpage', {'classId': classId});
                    }else{
                      $state.go('chalkanddust.discussionroomfirstpage');
                    }
                    
                };
                
                
                /*Get Topic Details only Topic Details*/
                topicDetailsService.get({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId}, function(response) { 
                    if(response){
                    	$scope.topicDetails=response.discussionTopics;
                    	$scope.imageLink=response.photolink;
                    	if($scope.imageLink=="noImage"){
                    		$scope.imageLink=undefined;
                    	}
                	                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                /*Get Comments of the particular Topic 
                 * using ClassId,SubjectId,topicId */
                	
                GetCommmentsOfTopicService.query({classId:$scope.classId, subjectId:$scope.subjectId,topicId:$scope.topicId,isGuest:$scope.isGuest}, function(response) { 
                    if(response){
                    	$scope.commentsList = response;
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                $scope.isUndefined = function (thing) {
                    return (typeof thing === "undefined");
                };
                
                /*Comments Code will start from here*/
                this.process=function(e) {
                    var code = (e.keyCode ? e.keyCode : e.which);
                    if (code == 13 && !e.shiftKey) { //Enter keycode
                        saveComment(document.getElementById('comment').value);
                    }
                };

                var saveComment =  function(commentValue) {
                    if($scope.commentsinfo!=null && $scope.commentsinfo!=""){
                      $scope.commentsinfo.classId=$scope.classId;
                      $scope.commentsinfo.subjectId=$scope.subjectId;
                      $scope.commentsinfo.discussionTopicId=$scope.topicId;
                      $scope.commentsinfo.userSecurUuid=$scope.securUuid; 
                      $scope.commentsinfo.isGuest=$scope.isGuest; 
                      console.log(JSON.stringify($scope.commentsinfo));
                      
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
                
                /*Comments code will ends here*/
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
           	$state.go('chalkanddust.discussionroomfirstpage');
                      
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
