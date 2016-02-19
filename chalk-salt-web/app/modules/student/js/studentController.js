'use strict';

define([ 'angular', './studentRouting', './studentService','../../CandDModal/js/CandDModalService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service','Student.discussionroom.controller','CandDModal']);
    
    homeModule.controller('StudentController', ['$window', '$scope', '$state', '$resource', '$location', '$rootScope', 'CHALKNDUST', 'GetUserDetailsService','StudentProfileUpdateService','ChangePasswordService',
            function($window,$scope, $state, $resource, $location, $rootScope, CHALKNDUST, GetUserDetailsService,StudentProfileUpdateService,ChangePasswordService) {

    		  
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
            }
    ]);
    
    
    
    
    
    //* Admin Controller
    
    homeModule.controller('AdminController', ['$window', '$scope', '$state', '$resource', '$location', '$rootScope', 'CHALKNDUST','$log',
      'GetUserDetailsService','StudentProfileUpdateService','ChangePasswordService','userClassLookUpService','GetSubjectsList',
      'createNewTopic','GetTopicsList','GetTopicDetailsService','deleteTopicDetailsService','updateTopicDetailsService','GetCommentsList',
      'deleteCommentDetailsService','GetStudentListService','CandDModalService','deleteStudentDetailsService',
    function($window,$scope, $state, $resource, $location, $rootScope, CHALKNDUST,$log,
       GetUserDetailsService,StudentProfileUpdateService,ChangePasswordService,userClassLookUpService,GetSubjectsList,
       createNewTopic,GetTopicsList,GetTopicDetailsService,deleteTopicDetailsService,updateTopicDetailsService,
       GetCommentsList,deleteCommentDetailsService,GetStudentListService,CandDModalService,deleteStudentDetailsService) {
 
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
        
        $scope.tab = 1;

        $scope.setTab = function(newTab){
          $scope.tab = newTab;          
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
             	console.log(response);
             	$scope.totalItems = $scope.topicsListDetails.length;
                 $scope.currentPage = 1;
                 $scope.itemsPerPage = 5;
                 $scope.maxSize = 5;

                 $scope.setPage = function(pageNo) {
                     $scope.currentPage = pageNo;
                 };

                 $scope.pageCount = function() {
                     return Math.ceil($scope.topicsListDetails.length / $scope.itemsPerPage);
                 };

                 $scope.$watch('currentPage + itemsPerPage', function() {
                     var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin + $scope.itemsPerPage;
                     $scope.topicsList = $scope.topicsListDetails.slice(begin, end);
                 });
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
              	console.log(response);
              	  $scope.totalItemsCom = $scope.commentsListDetails.length;
                  $scope.currentPageCom = 1;
                  $scope.itemsPerPageCom = 5;
                  $scope.maxSize = 5;

                  $scope.setPage = function(pageNo) {
                      $scope.currentPageCom = pageNo;
                  };

                  $scope.pageCount = function() {
                      return Math.ceil($scope.commentsListDetails.length / $scope.itemsPerPageCom);
                  };

                  $scope.$watch('currentPageCom + itemsPerPageCom', function() {
                      var begin = (($scope.currentPageCom - 1) * $scope.itemsPerPageCom), end = begin + $scope.itemsPerPageCom;
                      $scope.commentsList = $scope.commentsListDetails.slice(begin, end);
                  });
              }
         }, onRequestFailure);

     };
     function showStudentList(){
    	 GetStudentListService.query({}, function(response) {
        	 if(response){
        		 $scope.studentList = response;
             }
         }, onRequestFailure);
     };
         
     $scope.isEmpty = function(obj) {
    	 for(var prop in obj) {
    	      if(obj.hasOwnProperty(prop))
    	          return false;
    	  }
    	  return true;
     };
     $scope.topicDetailsToSave = {};
     this.createTopic = function() {   	
    	 angular.extend($scope.topicDetailsToSave,$scope.topicDetails);
         createNewTopic.save({}, $scope.topicDetailsToSave, function(
                 response) {
             if (response) {
                 console.log(response);
                 var modalOptions = {
                         header : 'Note',
                         body : 'New Topic is Created',
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
     
     this.editTopic=function(securUuid){
    	 GetTopicDetailsService.get({securUuid:securUuid},  function(response) {
             if(response){
             	 $scope.topicDetails = response;
             	 console.log($scope.topicDetails);
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

this.updateTopic = function() {
    updateTopicDetailsService.save({}, $scope.topicDetails, function(
            response) {
        if (response) {
            console.log(response);
        	var modalOptions = {
                    header : 'Note',
                    body : 'Your Topic is updated successfully',
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

//delete student
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

//Show Student's Details
$scope.showStudentDetails = function(securUuid) {
	if (!securUuid) {
		return;
	}
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
};

showStudentList();

}]);
});    
