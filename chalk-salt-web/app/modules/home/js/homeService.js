'use strict';

define([ 'angular' ], function(angular) {

    var homeService = angular.module('Home.service', [ 'System.configuration' ]);

    homeService.factory('HomeService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/home',{}, {
            save : {
                method : 'POST'
            }
        });
    } ]);
});