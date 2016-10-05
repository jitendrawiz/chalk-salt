'use strict';

define([ 'angular' ], function(angular) {

  var homeService = angular.module('Home.service', [ 'System.configuration' ]);

  homeService.factory('HomeService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/enquiry', {}, {
      save : {
        method : 'POST'
      }
    });
  } ]);

  homeService.factory('HomeGuestService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/guest/login', {}, {
      save : {
        method : 'POST'
      }
    });
  } ]);

  homeService.factory('userClassLookUpService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/classes', {}, {});
  } ]);

  homeService.factory('userNotesLookupService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/notes-master/details/:classId', {
      classId : '@classId'
    }, {
      get : {
        method : 'GET'
      }
    });
  } ]);

  homeService.factory('StudentAchievementLookupService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/students/achievement-details', {}, {
      get : {
        method : 'GET',
        isArray: true
      }
    });
  } ]);
});