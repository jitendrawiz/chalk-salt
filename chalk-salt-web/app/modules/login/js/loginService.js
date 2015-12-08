'use strict';

define([ 'angular' ], function(angular) {

    var loginService = angular.module('Login.service', [ 'System.configuration' ]);

    loginService.factory('LoginService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/login', {
            save : {
                method : 'POST'
            }
        });
    } ]);
});