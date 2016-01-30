'use strict';
define([ 'angular' ], function(angular) {

    var module = angular.module('Student.discussionroom.service', []);

    module.factory('GetUserDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users/:securUuid',{
            securUuid : '@securUuid'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
    module.factory('GetTopicStatistics', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/discussion/topics/subjects/:studentClassId',{
        	studentClassId : '@studentClassId'
        }, {
            get : {
                method : 'GET'
            }
            
        });
        
    }]);
    
});