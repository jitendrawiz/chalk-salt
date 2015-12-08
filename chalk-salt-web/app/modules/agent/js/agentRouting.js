'use strict';
define([ 'angular', 'text!../templates/header.tpl.html','text!../templates/home.tpl.html' ], function(angular, templateHeader, templateHome) {
    var agentRouter = angular.module('Agent.router', []);

    agentRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('propco.agent', {
            url : '/agent',
            abstract : true,
            template : templateHeader,
            controller : 'AgentController as agentCtrl'
        })
        .state('propco.agent.home', {
            url : '/home',
            template : templateHome,
            controller : 'AgentController as agentCtrl'
        });
    }]);
});