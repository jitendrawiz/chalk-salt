'use strict';

define([ 'angular' ], function(angular) {

  var testService = angular.module('Student.testservice', [ 'System.configuration', 'ngResource' ]);

  testService.factory('GetTestQuestionsListService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/test/list/:classId/:subjectId/:type', {
      classId : '@classId',
      subjectId : '@subjectId',
      type : '@type'
    }, {
      get : {
        method : 'GET'
      }
    });
  } ]);
  

});