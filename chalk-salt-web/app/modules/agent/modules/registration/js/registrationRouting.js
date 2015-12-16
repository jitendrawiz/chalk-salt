'use strict';

define([ 'angular', 'text!../templates/registration.tpl.html' ], function(
        angular, templateRegistration) {

    var registrationRouter = angular.module('Registration.router', []);

    registrationRouter.config([ '$stateProvider', function($stateProvider) {

        $stateProvider.state('chalkanddust.agent.registration', {
            url : '/registration',
            template : templateRegistration,
            controller : 'RegistrationController as regCtrl'
        });

    } ]);

});