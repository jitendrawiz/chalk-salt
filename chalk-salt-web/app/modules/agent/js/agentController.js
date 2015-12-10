'use strict';
define([ 'angular', './agentRouting','./dashboardController','angularDragDrop'], function(angular) {

    var module = angular.module('Agent.controller', [ 'Agent.router','ngDragDrop','Dashboard.controller' ]);
    module.controller('AgentController', [
            '$scope',
            '$state',
            '$http',
            '$resource',
            'PROPCO',
            '$rootScope',
            function($scope, $state,$http, $resource, PROPCO, $rootScope) {
                $rootScope.editWorkspace = false;
            } ]);
});