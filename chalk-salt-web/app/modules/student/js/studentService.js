'use strict';

define([ 'angular' ], function(angular) {

    var studentService = angular.module('Student.service', [ 'System.configuration','ngResource' ]);

    
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
        return $resource(ENV.API_END_POINT + 'private/discussion/comments/statistics/:classId/:subjectId/:topicId',{
        	classId : '@classId',
        	subjectId : '@subjectId',
        	topicId : '@topicId'
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
});