'use strict';

define([ 'angular', './studentRouting', './studentService' ], function(angular) {

    var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service','Student.discussionroom.controller']);
    
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
    
    homeModule.controller('AdminController', ['$window', '$scope', '$state', '$resource', '$location', '$rootScope', 'CHALKNDUST',
      'GetUserDetailsService','StudentProfileUpdateService','ChangePasswordService','userClassLookUpService','GetSubjectsList',
      'createNewTopic','GetTopicsList','GetTopicDetailsService','deleteTopicDetailsService','updateTopicDetailsService',
    function($window,$scope, $state, $resource, $location, $rootScope, CHALKNDUST,
       GetUserDetailsService,StudentProfileUpdateService,ChangePasswordService,userClassLookUpService,GetSubjectsList,
       createNewTopic,GetTopicsList,GetTopicDetailsService,deleteTopicDetailsService,updateTopicDetailsService) {

 
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
          $scope.topicsList={};
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
             console.log(classes);
         },onRequestFailure);
         
         function onRequestFailure(error) {
             showAlert('danger', error.data.message);
     };
     $scope.topicDetails = {};

     var resetOptions = function() {
    	 $scope.topicDetails.subjectId = ""; 
    	 $scope.topicDetails.listsubjectId = ""; 
     };

     
     $scope.showSubjectsList = function(classId) {
    	if (!classId) {
             return;
         }
         GetSubjectsList.query({classId:classId}, function(response) {
        	 resetOptions();
        	 $scope.topicsList={};
        	 console.log(classId);
             $scope.subjectsList = response;
         }, onRequestFailure);

     };

     $scope.showTopicDetails = function(classId,subjectId) {
    	 console.log(classId+"--------------------------"+subjectId);
    	if (!classId) {
    		resetOptions();
    		if(!subjectId){
    			return;
    		}             
         }
    	
         GetTopicsList.query({classId:classId,subjectId:subjectId}, function(response) {
             $scope.topicsList = response;
         }, onRequestFailure);

     };

         
     $scope.isEmpty = function(obj) {
    	 for(var prop in obj) {
    	      if(obj.hasOwnProperty(prop))
    	          return false;
    	  }
    	  return true;
     };
     this.createTopic = function() {
         
         createNewTopic.save({}, $scope.topicDetails, function(
                 response) {
             if (response) {
                 console.log(response);
                 alert("New Topic is Created.");
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
	   if(confirm("Do you want to delete this topic")){
  	 deleteTopicDetailsService.get({securUuid:securUuid},  function(response) {
           if(response){
           	 console.log(response);
           	 alert("Topic deleted successfully");
           	 $state.reload();
           	// $scope.setTab(4);
           	 }
       }, function(error) {
       	showAlert('danger',error.data.message);
       })
       }
 };
 //update Topic

this.updateTopic = function() {
    updateTopicDetailsService.save({}, $scope.topicDetails, function(
            response) {
        if (response) {
            console.log(response);
            alert("Your Topic is updated successfully");
        //    $window.localStorage.setItem(CHALKNDUST.EDITFLAG,false);                            
            $state.reload();
        }
        
    }, function(error) {
        showAlert('danger', error.data.message);
    });
};

}]);
});    