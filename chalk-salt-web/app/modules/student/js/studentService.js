'use strict';

define([ 'angular' ], function(angular) {

    var studentService = angular.module('Student.service', [ 'System.configuration','ngResource' ]);
      /* General Services starts here*/
    
    studentService.factory('GetUserDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        } ]); 
    
    studentService.factory('StudentProfileUpdateService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users/update',{}, {
            save : {
                method : 'POST'
            }
        });
        } ]);
    
    studentService.factory('ChangePasswordService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users/changepassword',{}, {
            save : {
                method : 'POST'
            }
        });
        } ]);
    
    

    studentService.factory('GetSubjectsList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/subjects/:classId',{
            classId : '@classId'
        }, {
            get : {
                method : 'GET'
            }
        });
        } ]); 
    
    /* General Services ends here*/
    /* Discussion Room Services starts here*/
    
    studentService.factory('createNewTopic', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics',{}, {
            save : {
                method : 'POST'
            }
        });
        } ]);    
        
    studentService.factory('GetTopicsList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/:classId/:subjectId',{
            classId : '@classId',
            subjectId:'@subjectId'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]); 
    
    studentService.factory('GetTopicDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/edittopic/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
    } ]); 
    
    studentService.factory('deleteTopicDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/deletetopic/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]); 
  
    studentService.factory('updateTopicDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/update',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
    
    studentService.factory('GetCommentsList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/comments/statistics/:classId/:subjectId/:topicId/:isGuest',{
        	classId : '@classId',
        	subjectId : '@subjectId',
        	topicId : '@topicId',
        	isGuest:'@isGuest'
        }, {
            get : {
                method : 'GET'
            }
        });
   }]);
    
    studentService.factory('deleteCommentDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/deletecomment/:commentUuid',{
        	commentUuid : '@commentUuid'
        }, {
            get : {
                method : 'GET'
            }
        });
   } ]);
    
    /* Discussion Room Services ends here*/
    
    /* Student Related/Discussion Room topic request Services starts here*/
    
    studentService.factory('GetStudentListService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/students',{}, {
            get : {
                method : 'GET'
            }
        });
   } ]);
    
    studentService.factory('deleteStudentDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/students/delete/:securUuid',{
        	securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
        });
   } ]);
  
    studentService.factory('GetTopicRequestList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/requests',{}, {
            get : {
                method : 'GET'
            }
        });
   } ]);
    
    studentService.factory('approveTopicRequestService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/requests/:requestSecurUuid',{
        	requestSecurUuid:'@requestSecurUuid'
        }, {
            get : {
                method : 'GET'
            }
        });
   } ]);
    /* Student Related/Discussion Room topic request Services ends here*/   
    
    /* All media/photo update/delete/upload Services starts here*/
    studentService.factory('UpdateProfilePhotoService', [ '$http', 'ENV', function($http, ENV) {
        return {
            upload : function(formData, securUuid, successCallback, errorCallback) {
                var uploadUrl = ENV.API_END_POINT + "private/users/update/profile/photo/" + securUuid;

                $http.post(uploadUrl, formData, {
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }
                }).success(function(response) {
                    successCallback(response);
                }).error(function(error) {
                    errorCallback(error);
                });
            }
        };
    } ]);

    studentService.factory('GetUserPhotoService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/photo/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        } ]); 
    
    studentService.factory('DeletePhotoService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/photo/delete/:securUuid', {
        	securUuid : '@securUuid'
        }, {
            remove : {
                method : 'DELETE'
            }
        });
    } ]);
    
    studentService.factory('UpdateTopicImageService', [ '$http', 'ENV', function($http, ENV) {
        return {
            upload : function(formData, securUuid, successCallback, errorCallback) {
                var uploadUrl = ENV.API_END_POINT + "private/users/update/topic/photo/" + securUuid;

                $http.post(uploadUrl, formData, {
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }
                }).success(function(response) {
                    successCallback(response);
                }).error(function(error) {
                    errorCallback(error);
                });
            }
        };
    } ]);
    
    studentService.factory('GetTopicImageService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/topic/image/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        } ]); 
    /* All media/photo update/delete/upload Services ends here*/
    
    /* All Exam Services starts here*/
    studentService.factory('SaveQuestionDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/exam/questions/add',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
    
    studentService.factory('GetQuestionList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/exam/questions/:classId/:subjectId',{
            classId : '@classId',
            subjectId:'@subjectId'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]); 
    
    studentService.factory('updateQuestionDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/exam/questions/update',{}, {
            save : {
                method : 'POST'
            }
        });
    }]);
    
    studentService.factory('deleteQuestionService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/exam/questions/delete/:questionSecuruuid',{
            securUuid : '@questionSecuruuid'
        }, {
            get : {
                method : 'GET'
            }
        });
    }]);
    
    studentService.factory('UpdateQuestionImageService', [ '$http', 'ENV', function($http, ENV) {
        return {
            upload : function(formData, securUuid, successCallback, errorCallback) {
                var uploadUrl = ENV.API_END_POINT + "private/exam/questions/update/photo/" + securUuid;

                $http.post(uploadUrl, formData, {
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }
                }).success(function(response) {
                    successCallback(response);
                }).error(function(error) {
                    errorCallback(error);
                });
            }
        };
    } ]);
    
    /*Get Dashboard details according to subject*/
    studentService.factory('GetDashboardDataBySubject', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/exam/data/:classId/:subjectId',{
            classId : '@classId',
            subjectId:'@subjectId'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]);
    
    /* All Exam Services ends here*/

    studentService.factory('ResetPasswordService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users/reset-password/:securUuid',{
            securUuid : '@securuuid'
        }, {
            get : {
                method : 'GET'
            }
        });
    }]);
    
    
    
    /*Video Master/List services starts from here*/
    
    
    studentService.factory('saveVideoMasterData', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/video-master/details/add',{}, {
            save : {
                method : 'POST'
            }
        });
    }]);

    
    studentService.factory('GetVideoContentList', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/video-master/details/:classId/:subjectId',{
          classId : '@classId',
          subjectId:'@subjectId'
        }, {
            get : {
                method : 'GET'
            }
        });
    }]);
   
    studentService.factory('GetVideoDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/video-master/details/edit/:videoUuid',{
          videoUuid : '@videoUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
    } ]); 
    
    
    studentService.factory('updateVideoDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/video-master/details/update',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
  
    studentService.factory('deleteVideoDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/video-master/details/delete/:videoUuid',{
          videoUuid : '@videoUuid'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]); 
  
    /*Video master/List services ends here*/
    
    
    /*notes services starts from here*/
    studentService.factory('createNotesContentService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/notes-master/details/save',{}, {
            save : {
                method : 'POST'
            }
        });
        } ]);  
    
    studentService.factory('UpdateNotesFileService', [ '$http', 'ENV', function($http, ENV) {
        return {
            upload : function(formData, notesUuid, successCallback, errorCallback) {
                var uploadUrl = ENV.API_END_POINT + "private/notes-master/details/update/notes/file/" + notesUuid;

                $http.post(uploadUrl, formData, {
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }
                }).success(function(response) {
                    successCallback(response);
                }).error(function(error) {
                    errorCallback(error);
                });
            }
        };
    } ]);
    
    
    
    /*notes services ends here*/
    
});