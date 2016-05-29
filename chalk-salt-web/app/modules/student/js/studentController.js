'use strict';

define([ 'angular', './studentRouting', './studentService','../../CandDModal/js/CandDModalService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service','Student.discussionroom.controller','CandDModal']);
    
    homeModule.controller('StudentController', ['$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 
                                                'CHALKNDUST', 'GetUserDetailsService','StudentProfileUpdateService',
                                                'ChangePasswordService','UpdateProfilePhotoService','GetUserPhotoService','DeletePhotoService','CandDModalService',
                                                'GetDashboardDataBySubject',
            function($window,$scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, GetUserDetailsService,
            		StudentProfileUpdateService,ChangePasswordService,UpdateProfilePhotoService,
            		GetUserPhotoService,DeletePhotoService,CandDModalService,GetDashboardDataBySubject) {
    	       $scope.uploadedlogo = {};
    	       $scope.showVideoDiv=false;
    		  
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
               $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
               $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
               if($window.localStorage.getItem(CHALKNDUST.EDITFLAG)=="false"){
               $scope.editFlag=false;
               }
               else  if($window.localStorage.getItem(CHALKNDUST.EDITFLAG)=="true"){
               $scope.editFlag=true;
               }
               $scope.backToDashBoard=function(){
            	   $state.go('chalkanddust.profile');  
               };
                $scope.version = CHALKNDUST.VERSION;
                $scope.build = CHALKNDUST.BUILD;
                $scope.email = CHALKNDUST.EMAIL;
                $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
                GetUserDetailsService.get({securUuid:$scope.securUuid},  function(response) {
                    if(response){
                    	 $scope.userInfo = response;
                    	 $scope.subjects =$scope.userInfo.subjects;
                    	 
                    	 $scope.academicInfo =$scope.userInfo.academicInfo;
                    	 $window.localStorage.setItem(CHALKNDUST.CLASSID,$scope.academicInfo.studentClassId);
                    	 $scope.parentsInfo =$scope.userInfo.parentsInfo;
                    	 $scope.studentSubjects=""; 
                    	 if($scope.subjects!=null){
                    	 for(var i=0;i<$scope.subjects.length;i++){
                    		 if($scope.studentSubjects==""){
                    			 $scope.studentSubjects=$scope.subjects[i].subjectName;
                    		 }else{
                    			 $scope.studentSubjects=$scope.studentSubjects+", "+$scope.subjects[i].subjectName;
                    		 }
                    		 
                    	 }}
                    	 
                         GetUserPhotoService.get({securUuid:$scope.securUuid},  function(result) {
                             	$scope.userProfilePhoto=result.photolink;
                         }, function(error) {
                         	showAlert('danger',error.data.message);
                         });
                    }
                }, function(error) {
                	showAlert('danger',error.data.message);
                });
                
                this.updateProfile = function() {
                    $scope.userInfo.academicInfo = $scope.academicInfo;
                    $scope.userInfo.parentsInfo = $scope.parentsInfo;
                    StudentProfileUpdateService.save({}, $scope.userInfo, function(
                            response) {
                        if (response) {
                            console.log(response);
                            $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);                            
                            $state.reload();
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
                $scope.editProfile = function(){
                	$window.localStorage.setItem(CHALKNDUST.EDITFLAG,true);                	
                	 $state.reload();
                };
                
                this.changePassword = function() {
                	ChangePasswordService.save({}, $scope.userInfo, function(
                            response) {
                        if (response) {
                            console.log(response);
                            $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);                            
                            $state.reload();
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
                /**
                 * Function to upload profile photo
                 */
                $scope.updateProfilePhoto = function(fileData) {
                    var file = fileData;
                    var formData = new FormData();
                    formData.append('file', file);
                    formData.append('name', file.name);
                    formData.append('documentType', file.type);
                    UpdateProfilePhotoService.upload(formData, $scope.securUuid, function(response) {
                        showAlert("success", "Profile Photo updated successfully.");                        
                    }, onRequestFailure);
                };

                
                function onRequestFailure(error) {
                    showAlert('danger', error.data.message);
               };
                /**
                 * Function to enable/disable upload profile photo button
                 */
                $scope.disableUploadPhotoButton = function() {
                    var disable = true;
                    var inputVal = document.getElementById("inputUploadProfilePhoto").value;

                    if (inputVal) {
                        disable = false;
                    }

                    return disable;
                };
                
                $scope.isUndefined = function (thing) {
                    return (typeof thing === "undefined");
                };
                
                /**
                 * Function to delete uploaded profile photo 
                 */ 
                this.deleteProfilePhoto=function(){   
                	var modalOptionsConfirm = {
                            header : 'Note',
                            body : 'Do you want to delete profile photo?',
                            btn : 'OK'
                        };
                    CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
                    	DeletePhotoService.remove({securUuid:$scope.securUuid},  function(response) {
                    	        if(response){
                    	        	 console.log(response);
                    	         	var modalOptions = {
                    	                    header : 'Note',
                    	                    body : response.message,
                    	                    btn : 'OK'
                    	                };
                    	            CandDModalService.showModal({}, modalOptions).then(function(result) {
                    	            	showAlert('success',response.message);
                    	                });
                    	        	 $state.reload();
                    	        	 }
                    	    }, function(error) {
                    	    	showAlert('danger',error.data.message);
                    	    })
                        });
                };
                
                
                if($window.localStorage.getItem(CHALKNDUST.TABNUMBER)!=null){
                  $scope.tab = $window.localStorage.getItem(CHALKNDUST.TABNUMBER);
                  var item={};
                  item.subjectId=$window.localStorage.getItem(CHALKNDUST.SUBJECTID);
                  item.subjectName=$window.localStorage.getItem(CHALKNDUST.SUBJECTNAME);
                  updatePageDetailsOnClick(item);
                  }else{
                    $scope.tab=1;
                  }
                
                $scope.setTab = function(newTab){
                    $scope.tab = newTab; 
                    $window.localStorage.setItem(CHALKNDUST.TABNUMBER,newTab); 
                    $scope.subjectName=null;
                  };
                  
                  $scope.isSet = function(tabNum){
                      return $scope.tab === tabNum;
                    };
                    
                    $scope.setTabSubject=function(newtab,item){
                        $scope.tab=newtab;
                        $window.localStorage.setItem(CHALKNDUST.TABNUMBER,newtab);                        
                        updatePageDetailsOnClick(item);
                    }
                    
                    
                    function updatePageDetailsOnClick(item){
                        $window.localStorage.setItem(CHALKNDUST.SUBJECTID,item.subjectId);
                        $window.localStorage.setItem(CHALKNDUST.SUBJECTNAME,item.subjectName);
                        $scope.subjectName="/ " + item.subjectName;
                        $scope.showVideoDiv=false;                     
                        $scope.videoObject=[];                       
                        $scope.notesObject=[];
                        $scope.classId=$window.localStorage.getItem(CHALKNDUST.CLASSID);
                        GetDashboardDataBySubject.get({classId:$scope.classId,subjectId:item.subjectId},function(response){
                            if(response){
                              $scope.videoObject=response.videos;
                              if($scope.videoObject.length===0){
                                $scope.showVideoDiv=true;
                              }
                              angular.forEach($scope.videoObject, function(value, key) {
                                  value.videoEmbedLink= value.videoEmbedLink.replace("watch?v=", "embed/");
                                });
                              
                            }
                        },function(error){
                            showAlert('danger',error.data.message);
                        });
                    }
    }]);
    
    //* Admin Controller
    
    homeModule.controller('AdminController', ['$stateParams','$window', '$scope', '$filter', '$state', '$resource', '$location', '$rootScope', 'CHALKNDUST','$log',
      'GetUserDetailsService','StudentProfileUpdateService','ChangePasswordService','userClassLookUpService','GetSubjectsList',
      'createNewTopic','GetTopicsList','GetTopicDetailsService','deleteTopicDetailsService','updateTopicDetailsService','GetCommentsList',
      'deleteCommentDetailsService','GetStudentListService','CandDModalService','deleteStudentDetailsService','filterFilter', 
      'GetTopicRequestList','approveTopicRequestService', 'UpdateTopicImageService','GetTopicImageService','RegistrationService', 'SaveQuestionDetailsService',
      'GetQuestionList', 'updateQuestionDetailsService', 'deleteQuestionService', 'UpdateQuestionImageService','ResetPasswordService', 
    function($stateParams,$window,$scope, $filter, $state, $resource, $location, $rootScope, CHALKNDUST,$log,
       GetUserDetailsService,StudentProfileUpdateService,ChangePasswordService,userClassLookUpService,GetSubjectsList,
       createNewTopic,GetTopicsList,GetTopicDetailsService,deleteTopicDetailsService,updateTopicDetailsService,
       GetCommentsList,deleteCommentDetailsService,GetStudentListService,CandDModalService,
       deleteStudentDetailsService,filterFilter,GetTopicRequestList,approveTopicRequestService,UpdateTopicImageService,GetTopicImageService,
       RegistrationService,SaveQuestionDetailsService, GetQuestionList, updateQuestionDetailsService, deleteQuestionService, UpdateQuestionImageService, 
       ResetPasswordService) {
 
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
        if($window.localStorage.getItem(CHALKNDUST.TABNUMBER)!=null){
        $scope.tab = $window.localStorage.getItem(CHALKNDUST.TABNUMBER);
        }else{
        	$scope.tab=1;
        }
        $scope.setTab = function(newTab){
          $scope.tab = newTab; 
          $window.localStorage.setItem(CHALKNDUST.TABNUMBER,newTab);
          $scope.topicsList=[];
          $scope.commentsList=[];         
        };

        $scope.isSet = function(tabNum){
          return $scope.tab === tabNum;
        };
        
        $scope.securUuid=$window.localStorage.getItem(CHALKNDUST.SECURUUID);
        $scope.fullName=$window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
        if($window.localStorage.getItem(CHALKNDUST.EDITFLAG)=="false"){
        $scope.editFlag=false;
        }
        else  if($window.localStorage.getItem(CHALKNDUST.EDITFLAG)=="true"){
        $scope.editFlag=true;
        }
        $scope.backToAdminDashBoard=function(){
     	   $state.go('chalkanddust.adminhome');  
        };
         $scope.version = CHALKNDUST.VERSION;
         $scope.build = CHALKNDUST.BUILD;
         $scope.email = CHALKNDUST.EMAIL;
         $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
         GetUserDetailsService.get({securUuid:$scope.securUuid},  function(response) {
             if(response){
             	 $scope.userInfo = response;
             	 $scope.subjects =$scope.userInfo.subjects;

             	 $scope.academicInfo =$scope.userInfo.academicInfo;
             	 
             	 $scope.parentsInfo =$scope.userInfo.parentsInfo;
             	 $scope.studentSubjects=""; 
             	 if($scope.subjects!=null){
             	 for(var i=0;i<$scope.subjects.length;i++){
             		 if($scope.studentSubjects==""){
             			 $scope.studentSubjects=$scope.subjects[i].subjectName;
             		 }else{
             			 $scope.studentSubjects=$scope.studentSubjects+", "+$scope.subjects[i].subjectName;
             		 }
             		 
             	 }}
             }
         }, function(error) {
         	showAlert('danger',error.data.message);
         });
         
         this.updateProfile = function() {
             $scope.userInfo.academicInfo = $scope.academicInfo;
             $scope.userInfo.parentsInfo = $scope.parentsInfo;
             StudentProfileUpdateService.save({}, $scope.userInfo, function(
                     response) {
                 if (response) {
                     console.log(response);
                     $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);                            
                     $state.reload();
                 }
                 
             }, function(error) {
                 showAlert('danger', error.data.message);
             });
         };
         
         $scope.editProfile = function(){
         	$window.localStorage.setItem(CHALKNDUST.EDITFLAG,true);                	
         	 $state.reload();
         };
         
         this.changePassword = function() {
         	ChangePasswordService.save({}, $scope.userInfo, function(
                     response) {
                 if (response) {
                     console.log(response);
                     $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);                            
                     $state.reload();
                 }
                 
             }, function(error) {
                 showAlert('danger', error.data.message);
             });
         };

         userClassLookUpService.query(function(classes) {
             $scope.classes = classes;
             //console.log(classes);
         },onRequestFailure);
         
         function onRequestFailure(error) {
             showAlert('danger', error.data.message);
     };
     $scope.topicDetails = [];
     $scope.commentDetails=[];
     $scope.studentList=[];
     $scope.topicRequestListDetails=[];
     
     var resetOptions = function() {
    	 $scope.topicDetails.subjectId = ""; 
    	 $scope.topicDetails.listsubjectId = ""; 
     };

     var resetOptionsComments=function(){
    	 $scope.commentDetails.subjectId = ""; 
    	 $scope.commentDetails.listsubjectId = ""; 
    	 $scope.commentDetails.topicId = ""; 
    	 $scope.commentDetails.listTopicId = ""; 
     }
     
     $scope.showSubjectsList = function(classId) {
    	if (!classId) {
             return;
         }
         GetSubjectsList.query({classId:classId}, function(response) {
        	 resetOptions();
        	 resetOptionsComments();
        	 $scope.topicsList=[];
        	 $scope.commentsList=[];
        	 $scope.questionList=[];
        	 console.log(classId);
             $scope.subjectsList = response;
         }, onRequestFailure);

     };
     
     $scope.showTopicDetails = function(classId,subjectId) {
    	 console.log(classId+"--------------------------"+classId);
    	 console.log(subjectId+"--------------------------"+subjectId);
    	if (!classId) {
    		resetOptions();
    		if(!subjectId){
    			return;
    		}             
         }
    	
         GetTopicsList.query({classId:classId,subjectId:subjectId}, function(response) {
        	 $scope.commentsList=[];
        	 $scope.commentDetails.topicId = ""; 
        	 $scope.commentDetails.listTopicId = "";
        	 
        	 if(response){
        		 $scope.topicsListDetails = response;
             	$scope.totalItemstopicsList = $scope.topicsListDetails.length;
                 $scope.currentPagetopicsList = 1;
                 $scope.itemsPerPagetopicsList = 5;
                 $scope.maxSizetopicsList = 5;
                 
                 $scope.$watch('search', function (newVal, oldVal) {
             		$scope.topicsList = filterFilter($scope.topicsListDetails, newVal);
             		$scope.totalItemstopicsList = $scope.topicsList.length;
             		//$scope.maxSizetopicsList = Math.ceil($scope.totalItemstopicsList / $scope.itemsPerPagetopicsList);
             		$scope.currentPagetopicsList = 1;
             	}, true);
 	              $scope.getTopicsData = function () {
 	                  // keep a reference to the current instance "this" as the context is changing
 	                  var self = this;
 	                  console.log(self.currentPagetopicsList);
 	                  var itemsPerPagetopicsList = self.itemsPerPagetopicsList; 
 	                  var offset = (self.currentPagetopicsList-1) * itemsPerPagetopicsList;
 	                  $scope.topicsList = $scope.topicsListDetails.slice(offset, offset + itemsPerPagetopicsList)
 	
 	              };
               $scope.getTopicsData();
             }
         }, onRequestFailure);

     };

     $scope.showTopicDetailsForCommentsPage = function(classId,subjectId) {
    	 console.log(classId+"--------------------------"+classId);
    	 console.log(subjectId+"--------------------------"+subjectId);
    	if (!classId) {
    		resetOptions();
    		if(!subjectId){
    			return;
    		}             
         }
    	
         GetTopicsList.query({classId:classId,subjectId:subjectId}, function(response) {
        	 $scope.commentsList=[];
        	 $scope.commentDetails.topicId = ""; 
        	 $scope.commentDetails.listTopicId = "";
        	 if(response){
        		 $scope.topicsList = response;
             }
         }, onRequestFailure);

     };

     
     
     $scope.showCommentDetails = function(classId,subjectId,topicId) {
    	 console.log("classId--------------------------"+classId);
    	 console.log("subjectId--------------------------"+subjectId);
    	 console.log("TopicId--------------------------"+topicId);
    	if (!topicId) {
    		resetOptions(); 
    		if (!subjectId) {
    			if(!topicId){
        			return;
        		}            
             }
         }
    	
         GetCommentsList.query({classId:classId,subjectId:subjectId,topicId:topicId}, function(response) {
             if(response){
            	 $scope.commentsListDetails = response;
             	 $scope.totalItemscommentsList = $scope.commentsListDetails.length;
                 $scope.currentPagecommentsList = 1;
                 $scope.itemsPerPagecommentsList = 5;
                 $scope.maxSizecommentsList = 5;
                 
                 $scope.$watch('search', function (newVal, oldVal) {
             		$scope.commentsList = filterFilter($scope.commentsListDetails, newVal);
             		$scope.totalItemscommentsList = $scope.commentsList.length;
             		//$scope.maxSizecommentsList = Math.ceil($scope.totalItemscommentsList / $scope.itemsPerPagecommentsList);
             		$scope.currentPagecommentsList = 1;
             	}, true);
                 
 	              $scope.getCommentsData = function () {
 	                  // keep a reference to the current instance "this" as the context is changing
 	                  var self = this;
 	                  console.log(self.currentPagecommentsList);
 	                  var itemsPerPagecommentsList = self.itemsPerPagecommentsList; 
 	                  var offset = (self.currentPagecommentsList-1) * itemsPerPagecommentsList;
 	                  $scope.commentsList = $scope.commentsListDetails.slice(offset, offset + itemsPerPagecommentsList)
 	
 	              };
               $scope.getCommentsData();
              }
         }, onRequestFailure);

     };

     function showStudentList(){
    	 GetStudentListService.query({}, function(response) {
        	 if(response){
             	$scope.studentListDetails = response;
            	$scope.totalItemsstudentList = $scope.studentListDetails.length;
                $scope.currentPageStudentList = 1;
                $scope.itemsPerPageStudentList = 8;
                $scope.maxSizeStudentList = 5;
                
                $scope.$watch('search', function (newVal, oldVal) {
            		$scope.studentList = filterFilter($scope.studentListDetails, newVal);
            		$scope.totalItemsstudentList = $scope.studentList.length;
            		//$scope.maxSizeStudentList = Math.ceil($scope.totalItemsstudentList / $scope.itemsPerPageStudentList);
            		$scope.currentPageStudentList = 1;
            	}, true);
                
	              $scope.getData = function () {
	                  // keep a reference to the current instance "this" as the context is changing
	                  var self = this;
	                  console.log(self.currentPageStudentList);
	                  var itemsPerPageStudentList = self.itemsPerPageStudentList; 
	                  var offset = (self.currentPageStudentList-1) * itemsPerPageStudentList;
	                  $scope.studentList = $scope.studentListDetails.slice(offset, offset + itemsPerPageStudentList)
	
	              };
              $scope.getData();
             }
         }, onRequestFailure);
     };
     
     /*******Reset Student Login Password ********/
     this.resetPassword = function(securUuid){
    	 var modalOptionsConfirm = {
   	            header : 'Note',
   	            body : 'Do you want to reset student login password?',
   	            btn : 'OK'
   	        };
   	    CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
   	    	ResetPasswordService.get({securUuid:securUuid},  function(response) {
              if(response){
              	 console.log(response);
              	var modalOptions = {
                       header : 'Note',
                       body : 'Password has been reset successfully and new Password has been sent to the student via email.',
                       btn : 'OK'
                   };

               CandDModalService.showModal({}, modalOptions).then(function(result) {
                       $log.info(result);
                   });
              	 $state.reload();
              	 }
   	       }, function(error) {
   	       	showAlert('danger',error.message);
   	       })
   	    });
     };
     
     /******* Save Question********/
     $scope.questionDetails={};
     $scope.saveQuestion = function(fileData) {
    	 SaveQuestionDetailsService.save({}, $scope.questionDetails, function(
    	            response) {
    	        if (response) {
    	          var modalOptions = {
	                    header : 'Note',
	                    body : 'Question has been saved.',
	                    btn : 'OK'
	                };
    	          
    	            CandDModalService.showModal({}, modalOptions).then(function(result) {
	                    $log.info(result);
	                });
    	            console.log(response);
    	            if(!angular.isUndefined(fileData)){
                     	updateQuestionPhoto(fileData,response.questionSecuruuid);
                     }
    	            $state.reload();
    	        }
    	        
    	    }, function(error) {
    	        showAlert('danger', error.data.message);
    	    });
    	};
     

     /******* Show Question List ********/	
     $scope.showQuestionDetails = function(classId,subjectId, classes, subjectsList) {
    	 console.log("Fetching questions detailed list "+classId+"-"+classId+" & "+subjectId+"-"+subjectId);
    	 
    	 $scope.classes=classes;
    	 $scope.subjectsList=subjectsList;
    	 
    	if (!classId) {
    		resetOptions();
    		if(!subjectId){
    			return;
    		}             
         }
         GetQuestionList.query({classId:classId,subjectId:subjectId}, function(response) {
        	 if(response){
        		 $scope.questionListDetails = response;
             	$scope.totalItemsQuestionList = $scope.questionListDetails.length;
                 $scope.currentPageQuestionList = 1;
                 $scope.itemsPerPageQuestionList = 5;
                 $scope.maxSizeQuestionList = 5;
                 
                 $scope.$watch('search', function (newVal, oldVal) {
             		$scope.questionList = filterFilter($scope.questionListDetails, newVal);
             		$scope.totalItemsQuestionList = $scope.questionList.length;
             		//$scope.maxSizetopicsList = Math.ceil($scope.totalItemstopicsList / $scope.itemsPerPagetopicsList);
             		$scope.currentPageQuestionList = 1;
             	}, true);
 	              $scope.getQuestionData = function () {
 	                  // keep a reference to the current instance "this" as the context is changing
 	                  var self = this;
 	                  console.log(self.currentPageQuestionList);
 	                  var itemsPerPageQuestionList = self.itemsPerPageQuestionList; 
 	                  var offset = (self.currentPageQuestionList-1) * itemsPerPageQuestionList;
 	                  $scope.questionList = $scope.questionListDetails.slice(offset, offset + itemsPerPageQuestionList)
 	              };
               $scope.getQuestionData();
             }
         }, onRequestFailure);

     };
     
     /******* Edit Question ********/	
     $scope.editQuestion = function(quesSecurUuid){
    	 $scope.questionDetails={};
    	 var found = $filter('filter')($scope.questionListDetails, {questionSecuruuid: quesSecurUuid}, true);
         if (found.length) {
        	 $scope.questionDetails = found[0];
        	 $scope.setTab(10);
         } else{
        	 console.log("question data not found");
        	 $state.go('chalkanddust.questionlist');
         }
     };
     
     /******* Update Question ********/	
     $scope.updateQuestion=function(fileData, classes, subjectsList){
    	 console.log("updating question :"+$scope.questionDetails.questionSecuruuid);
    	 $scope.classes=classes;
    	 $scope.subjectsList=subjectsList;
    	 
    	 updateQuestionDetailsService.save({}, $scope.questionDetails, function(response) {
 	        if (response) {
 	            console.log(response);
 	            if(!angular.isUndefined(fileData)){
 	            	updateQuestionPhoto(fileData,$scope.questionDetails.questionSecuruuid);
 	             }
 	        	var modalOptions = {
 	                    header : 'Note',
 	                    body : 'Question is updated successfully',
 	                    btn : 'OK'
 	                };
 	            	CandDModalService.showModal({}, modalOptions).then(function(result) {
 	            	$scope.setTab(11);
 	            });
 	        }
 	    }, function(error) {
 	        showAlert('danger', error.message);
 	    });
     };
     
     /******* Update Question Photo ********/
 	var updateQuestionPhoto = function (fileData,securUuid) {
 	    var file = fileData;
 	    var formData = new FormData();
 	    formData.append('file', file);
 	    formData.append('name', file.name);
 	    formData.append('documentType', file.type);
 	    UpdateQuestionImageService.upload(formData, securUuid, function(response) {
 	        showAlert("success", "Question Image updated successfully.");                        
 	    }, onRequestFailure);
 	};
     
     /******* Delete Question ********/	
     $scope.deleteQuestion=function(questionSecuruuid){
  	   var modalOptionsConfirm = {
  	            header : 'Note',
  	            body : 'Do you want to delete the question?',
  	            btn : 'OK'
  	        };
  	    CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
    	 deleteQuestionService.get({questionSecuruuid:questionSecuruuid},  function(response) {
             if(response){
             	 console.log(response);
             	var modalOptions = {
                      header : 'Note',
                      body : 'Question deleted successfully',
                      btn : 'OK'
                  };

              CandDModalService.showModal({}, modalOptions).then(function(result) {
                      $log.info(result);
                  });
             	 $state.reload();
             	 }
  	       }, function(error) {
  	       	showAlert('danger',error.message);
  	       })
  	    });
     };
     
     $scope.isEmpty = function(obj) {
    	 for(var prop in obj) {
    	      if(obj.hasOwnProperty(prop))
    	          return false;
    	  }
    	  return true;
     };
     
     $scope.topicDetailsToSave = {};
     this.createTopic = function(fileData) {   	
    	 angular.extend($scope.topicDetailsToSave,$scope.topicDetails);    	 
         createNewTopic.save({}, $scope.topicDetailsToSave, function(
                 response) {
             if (response) {
                 console.log(response);
                 
                 if(!angular.isUndefined(fileData)){
                 	updateTopicPhoto(fileData,response.securUuid);
                 }
                 var modalOptions = {
                         header : 'Note',
                         body : 'New Topic is Created',
                         btn : 'OK'
                     };
                 CandDModalService.showModal({}, modalOptions).then(function(result) {
                	 $state.reload();
                 });
                 
             }
             
         }, function(error) {
             showAlert('danger', error.data.message);
         });
     };
     
     this.editTopic=function(securUuid){
    	 GetTopicDetailsService.get({securUuid:securUuid},  function(response) {
             if(response){
             	 $scope.topicDetails = response;
             	 console.log($scope.topicDetails);
             	 if($scope.topicDetails.topicImage!=null){
             	 GetTopicImageService.get({securUuid:$scope.topicDetails.securUuid},  function(result) {
                 	$scope.topicImageLink=result.topicImageLink;
             	 }, function(error) {
             	 showAlert('danger',error.data.message);
             	 });
             	}
             	 $scope.setTab(4);
             	 }
         }, function(error) {
         	showAlert('danger',error.data.message);
         }
   )};
   
   
   //delete topic
   this.deleteTopic=function(securUuid){
	   var modalOptionsConfirm = {
	            header : 'Note',
	            body : 'Deleting this topic will also delete associated comments.Do you want to continue?',
	            btn : 'OK'
	        };
	    CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
  	 deleteTopicDetailsService.get({securUuid:securUuid},  function(response) {
           if(response){
           	 console.log(response);
           	var modalOptions = {
                    header : 'Note',
                    body : 'Topic deleted successfully',
                    btn : 'OK'
                };

            CandDModalService.showModal({}, modalOptions).then(function(result) {
                    $log.info(result);
                });
           	 $state.reload();
           	 }
	       }, function(error) {
	       	showAlert('danger',error.data.message);
	       })
	    });
   };
   //update Topic

   this.updateTopic = function(fileData) {
    updateTopicDetailsService.save({}, $scope.topicDetails, function(
            response) {
        if (response) {
            console.log(response);
            if(!angular.isUndefined(fileData)){
             	updateTopicPhoto(fileData,$scope.topicDetails.securUuid);
             }
            
        	var modalOptions = {
                    header : 'Note',
                    body : 'Your Topic is updated successfully',
                    btn : 'OK'
                };
            CandDModalService.showModal({}, modalOptions).then(function(result) {
            	$state.reload();
                });
        }
        
	    }, function(error) {
	        showAlert('danger', error.data.message);
	    });
   };

   //delete comment
   this.deleteComment=function(commentUuid){
	   var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete this Comment ?',
            btn : 'OK'
        };
	   CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
    	 deleteCommentDetailsService.get({commentUuid:commentUuid},  function(response) {
    	        if(response){
    	        	 console.log(response);
    	         	var modalOptions = {
    	                    header : 'Note',
    	                    body : 'Comment deleted successfully',
    	                    btn : 'OK'
    	                };
    	            CandDModalService.showModal({}, modalOptions).then(function(result) {
    	                    $log.info(result);
    	                });
    	        	 $state.reload();
    	        	 }
    	    }, function(error) {
    	    	showAlert('danger',error.data.message);
    	    })

        });
   };

	/****** delete student ********/
	this.deleteStudent=function(securUuid){
		var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete this Student ?',
            btn : 'OK'
        };
		CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
    	 deleteStudentDetailsService.get({securUuid:securUuid},  function(response) {
	        if(response){
	        	 console.log(response);
	         	var modalOptions = {
	                    header : 'Note',
	                    body : 'Student deleted successfully',
	                    btn : 'OK'
	                };
	            CandDModalService.showModal({}, modalOptions).then(function(result) {
	                    $log.info(result);
	                });
	        	 $state.reload();
	        	 }
    	    }, function(error) {
    	    	showAlert('danger',error.data.message);
    	    })

        });
	};

	//show topic request list
	function showTopicRequestList () {
	
		GetTopicRequestList.query({}, function(response) {
   	 
			if(response){
	   		 $scope.topicRequestListDetails = response;
	        	$scope.totalItemsTopicRequestList = $scope.topicRequestListDetails.length;
	            $scope.currentPageTopicRequestList = 1;
	            $scope.itemsPerPageTopicRequestList = 5;
	            $scope.maxSizeTopicRequestList = 5;
	            
	            $scope.$watch('search', function (newVal, oldVal) {
	        		$scope.topicRequestList = filterFilter($scope.topicRequestListDetails, newVal);
	        		$scope.totalItemsTopicRequestList = $scope.topicRequestList.length;
	        		//$scope.maxSizetopicsList = Math.ceil($scope.totalItemstopicsList / $scope.itemsPerPagetopicsList);
	        		$scope.currentPageTopicRequestList = 1;
	        	}, true);
	              $scope.getTopicRequestData = function () {
	                  // keep a reference to the current instance "this" as the context is changing
	                  var self = this;
	                  console.log(self.currentPageTopicRequestList);
	                  var itemsPerPageTopicRequestList = self.itemsPerPageTopicRequestList; 
	                  var offset = (self.currentPageTopicRequestList-1) * itemsPerPageTopicRequestList;
	                  $scope.topicRequestList = $scope.topicRequestListDetails.slice(offset, offset + itemsPerPageTopicRequestList)
	
	              };
	          $scope.getTopicRequestData();
			}
		}, onRequestFailure);
	};

	//approve topic request
	this.approveTopicRequest=function(requestSecurUuid){
		var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to approve this Topic Request?',
            btn : 'OK'
	    };
	    CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
	    	approveTopicRequestService.get({requestSecurUuid:requestSecurUuid},  function(response) {
	        if(response){
	        	 console.log(response);
	         	var modalOptions = {
	                    header : 'Note',
	                    body : 'Topic request approved successfully',
	                    btn : 'OK'
	                };
	            CandDModalService.showModal({}, modalOptions).then(function(result) {
	                    $log.info(result);
	                });
	        	 $state.reload();
	        	 }
    	    }, function(error) {
    	    	showAlert('danger',error.data.message);
    	    })

        });
	};	



	showStudentList();
	showTopicRequestList();
	
	this.goBackToAdminHomePage=function(){
		$state.go('chalkanddust.adminhome');
	};
		
	
	
	$scope.isUndefined = function (thing) {
	    return (typeof thing === "undefined");
	};



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
	
	/*
	 * 
	 * 
	 * Registration code will start from here.
	 * */
	
	
	$scope.userDetails = {};
	
	
	$scope.inputType = 'password';
	$scope.hideShowPassword = function() {
	    if ($scope.inputType === 'password') {
	        $scope.inputType = 'text';
	    } else {
	        $scope.inputType = 'password';
	    }
	};
	
	this.register = function() {
	                            
	    RegistrationService.save({}, $scope.userDetails, function(
	            response) {
	        if (response) {
	          var modalOptions = {
	                    header : 'Note',
	                    body : 'New user has been created and an email has been sent to their registered email address.',
	                    btn : 'OK'
	                };
	
	            CandDModalService.showModal({}, modalOptions).then(function(result) {
	                    $log.info(result);
	                });
	            console.log(response);
	            $state.reload();
	        }
	        
	    }, function(error) {
	        showAlert('danger', error.data.message);
	    });
	};
	
	/*
	 * 
	 * 
	 * Registration Code ends here
	 * */
	var Id = $stateParams.id;
	//Show Student's Details
	
		if (!Id) {
			return;
		}
		GetUserDetailsService.get({securUuid:Id},  function(response) {
	        if(response){
	        	 $scope.userInfoToShow = response;
	        	 $scope.subjectsToShow =$scope.userInfoToShow.subjects;
	
	        	 $scope.academicInfoToShow =$scope.userInfoToShow.academicInfo;
	        	 
	        	 $scope.parentsInfoToShow =$scope.userInfoToShow.parentsInfo;
	        	 $scope.studentSubjectsToShow=""; 
	        	 if($scope.subjectsToShow!=null){
	        	 for(var i=0;i<$scope.subjectsToShow.length;i++){
	        		 if($scope.studentSubjectsToShow==""){
	        			 $scope.studentSubjectsToShow=$scope.subjectsToShow[i].subjectName;
	        		 }else{
	        			 $scope.studentSubjectsToShow=$scope.studentSubjectsToShow+", "+$scope.subjectsToShow[i].subjectName;
	        		 }
	        		 
	        	 }}
	        	
	        }
	    }, function(error) {
	    	showAlert('danger',error.data.message);
	    });
	
	
	}]);
});    
