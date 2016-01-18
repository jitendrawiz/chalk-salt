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
});