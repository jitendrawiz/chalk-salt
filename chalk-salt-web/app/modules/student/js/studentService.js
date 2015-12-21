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
});