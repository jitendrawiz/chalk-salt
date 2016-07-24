'use strict';

define([ 'angular' ], function(angular) {

  var testService = angular.module('Student.testservice', [ 'System.configuration', 'ngResource' ]);

  testService.factory('GetTestQuestionsListService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/test/list/:classId/:subjectId/:type/:scheduleTestUuid', {
      classId : '@classId',
      subjectId : '@subjectId',
      type : '@type',
      scheduleTestUuid:'@scheduleTestUuid'
    }, {
      get : {
        method : 'GET'
      }
    });
  } ]);
  
  
  testService.service('helperService', function () {
    this.hello = function () {
        return "Hello World";
    };
    this.toBool = function (val) {
        if (val == 'undefined' || val == null || val == '' || val == 'false' || val == 'False')
            return false;
        else if (val == true || val == 'true' || val == 'True')
            return true;
        else
            return 'unidentified';
    };
    this.shuffle = function (array) {
        var currentIndex = array.length, temp, randomIndex;

        while (0 !== currentIndex) {
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            temp = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temp;
        }
        return array;
    }
    this.extend = function (out) {
        out = out || {};

        for (var i = 1; i < arguments.length; i++) {
            if (!arguments[i])
                continue;

            for (var key in arguments[i]) {
                if (arguments[i].hasOwnProperty(key))
                    out[key] = arguments[i][key];
            }
        }
        return out;
    };
});
  
  
  testService.factory('saveAnswersService', [ '$resource', 'ENV', function($resource, ENV) {
    return $resource(ENV.API_END_POINT + 'private/test/answers/saveOnSubmit', {}, {
      save : {
        method : 'PUT'
      }
    });
  } ]);

});