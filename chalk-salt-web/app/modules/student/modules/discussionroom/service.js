'use strict';
define([ 'angular' ], function(angular) {

    var module = angular.module('Student.discussionroom.service', []);

    module.factory('GetTopicStatistics', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/subjects/:studentClassId',{
        	studentClassId : '@studentClassId'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    module.factory('GetTopicsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/statistics/:classId/:subjectId',{
            classId : '@classId',
            subjectId : '@subjectId'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    
    
    
    module.factory('GetCommmentsOfTopicService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/comments/statistics/:classId/:subjectId/:topicId/:isGuest',{
            classId : '@classId',
            subjectId : '@subjectId',
            topicId :'@topicId',
            isGuest:'@isGuest'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    
    module.factory('CommentService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/comments',{}, {
            save : {
                method : 'POST'
            }
            
        });
        } ]);    

    module.factory('topicDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topic/:classId/:subjectId/:topicId',{
            classId : '@classId',
            subjectId : '@subjectId',
            topicId :'@topicId'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    module.factory('getSubjectNameService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/subject/:classId/:subjectId',{
            classId : '@classId',
            subjectId : '@subjectId'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    module.factory('getDetailsOfCommentService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/editComment/:commentUuid',{
            commentUuid : '@commentUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    module.factory('updateCommentDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/comment/update',{}, {
            save : {
                method : 'POST'
            }
            
        });
        } ]);    
    
    module.factory('SaveTopicRequest', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/students/topics/request/',{}, {
            save : {
                method : 'POST'
            }
            
        });
        } ]); 
    module.factory('UpdateTopicImageService', [ '$http', 'ENV', function($http, ENV) {
        return {
            upload : function(formData, securUuid, successCallback, errorCallback) {
                var uploadUrl = ENV.API_END_POINT + "private/students/topics/request/photo/" + securUuid;

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