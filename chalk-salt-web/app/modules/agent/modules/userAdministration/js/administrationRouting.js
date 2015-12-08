'use strict';

define([ 'angular', 'text!../templates/administration.tpl.html'], function(
        angular, templateAdministration) {

    var administrationRouter = angular.module('Administration.router', []);

    administrationRouter.config([ '$stateProvider', function($stateProvider) {

        $stateProvider.state('propco.agent.administration', {
            url : '/administration',
            template : templateAdministration,
            controller : 'AdministrationController as adminCtrl'
        });

    } ]);

});