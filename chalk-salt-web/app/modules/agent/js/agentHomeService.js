'use strict';

define([ 'angular' ], function(angular) {

    var homeService = angular.module('AgentHome.Service', [ 'System.configuration' ]);

    homeService.factory('AgentHomeService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/home/icon/save',{}, {
        save : {
            method : 'POST'
        }
        
    });
    }]);
    homeService.factory('IconFetchService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/home/icon/list',{}, {
            save : {
                method : 'POST'
            }
            
        });
        }]);
    
    
    
});