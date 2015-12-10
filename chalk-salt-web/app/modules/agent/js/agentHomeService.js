'use strict';

define([ 'angular' ], function(angular) {

    var homeService = angular.module('AgentHome.Service', [ 'System.configuration' ]);

    homeService.factory('DashboardSaveService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/icons', {}, {
            save : {
                method : 'POST'
            }

        });
    } ]);

    homeService.factory('GetDashboardService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/icons', {}, {
            get : {
                method : 'GET',
                isArray : true
            }

        });
    } ]);

});