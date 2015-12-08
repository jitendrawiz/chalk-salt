'use strict';

define([ 'angular' ], function(angular) {

    var registrationService = angular.module('Registration.Service', [ 'System.configuration' ]);

    registrationService.factory('RegistrationService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/user/register',{}, {
        save : {
            method : 'POST'
        }
        
    });
    } ]);
    
    registrationService.factory('DomainListService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/domains',{
            save : {
                method : 'POST'
            }
            
        });
        } ]);
});