'use strict';

define([ 'angular' ], function(angular) {

    var homeService = angular.module('Student.service', [ 'System.configuration' ]);

    homeService.factory('StudentService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/student',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
});