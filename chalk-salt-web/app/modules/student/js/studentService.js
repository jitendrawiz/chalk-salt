'use strict';

define([ 'angular' ], function(angular) {

    var studentService = angular.module('Student.service', [ 'System.configuration' ]);

    
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
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        } ]); 
    
    
});