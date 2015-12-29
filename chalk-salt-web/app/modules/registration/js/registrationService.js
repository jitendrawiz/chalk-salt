'use strict';

define([ 'angular' ], function(angular) {

    var registrationService = angular.module('Registration.Service', [ 'System.configuration' ]);

    registrationService.factory('RegistrationService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/users',{}, {
        save : {
            method : 'POST'
        }
        
    });
    } ]);    
    
    registrationService.factory('userClassLookUpService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/classes', {}, {});
    } ]);
});