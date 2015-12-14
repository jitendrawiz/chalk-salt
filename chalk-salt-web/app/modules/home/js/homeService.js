'use strict';

define([ 'angular' ], function(angular) {

    var loginService = angular.module('Home.service', [ 'System.configuration' ]);

    loginService.factory('HomeService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/home',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
});