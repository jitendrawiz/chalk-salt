'use strict';

/**
 * Interceptor for performing action at http calls
 */
define([ 'angular' ], function(angular) {
  var module = angular.module('httpInterceptor', [ 'ngResource' ]);

  module.config([ '$httpProvider', function($httpProvider) {
    if (!$httpProvider.defaults.headers.get) {
      $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    $httpProvider.interceptors.push([ '$q', '$injector', function($q, $injector) {
      return {
        request : function(config) {
          var url = config.url;
          if (url.indexOf("notification") != -1) {
            $('#loading').hide();
          } else {
            $('#loading').show();
            $('#loading img').show();
          }
          return $q.when(config);
        },
        response : function(response) {
          if (!/^(f|ht)tp?:\/\//i.test(response.config.url)) {
            $('#loading').hide();
            $('#loading img').hide();

          }
          var $http = $injector.get('$http');
          if ($http.pendingRequests.length < 1) {
            $('#loading').hide();
            $('#loading img').hide();
          }
          return $q.when(response);
        },
        responseError : function(response) {
          $('#loading').hide();
          $('#loading img').hide();
          return $q.reject(response);
        },
        requestError : function(response) {
          $('#loading').hide();
          $('#loading img').hide();
          return $q.reject(response);
        }

      };
    } ]);
  } ]);

  return module;
});
