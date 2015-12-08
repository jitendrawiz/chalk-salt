'use strict';

/**
 * Interceptor for performing action at http calls
 */
define(['angular'], function (angular) {
	var module = angular.module('httpInterceptor',['ngResource']);

	module.config(['$httpProvider', function ($httpProvider) {
		$httpProvider.interceptors.push(['$q','$injector', function ($q, $injector) {
			return {
				request: function (config) {
				    $('#loading').show();
				    $('#loading img').show();
					return $q.when(config);
				},
				response: function (response) {
				  var $http = $injector.get('$http');
				  if ($http.pendingRequests.length < 1) {
				      $('#loading').hide();
				      $('#loading img').hide();
				  }
				  return $q.when(response);
				},
				responseError: function (response) {
				    $('#loading').hide();
				    $('#loading img').hide();
					return $q.reject(response);
				},
				requestError: function (response) {
				    $('#loading').hide();
				    $('#loading img').hide();
					return $q.reject(response);
				}

			};
		}]);
	}]);

	return module;
});
