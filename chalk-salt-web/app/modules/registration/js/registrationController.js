'use strict';

define([ 'angular', './registrationRouting', './registrationService'
        ], function(angular) {

    var registrationModule = angular.module('Registration.controller', [
            'Registration.router', 'System.configuration',
            'Registration.Service']);

    registrationModule.controller('RegistrationController', [
            '$scope',
            '$state',
            '$resource',
            'CHALKNDUST',
            'RegistrationService',            
            function($scope, $state, $resource, CHALKNDUST, RegistrationService) {

                var showAlert = function(type, message) {
                    $scope.alert = {};
                    $scope.alert.type = type;
                    $scope.alert.message = message;
                    $scope.alert.show = true;
                };

                $scope.closeAlert = function() {
                    $scope.alert = {};
                    $scope.alert.show = false;
                    return true;
                };
                
               
                $scope.userDetails = {};
                

                $scope.inputType = 'password';
                $scope.hideShowPassword = function() {
                    if ($scope.inputType === 'password') {
                        $scope.inputType = 'text';
                    } else {
                        $scope.inputType = 'password';
                    }
                };

                
                this.register = function() {
                                            
                    RegistrationService.save({}, $scope.userDetails, function(
                            response) {
                        if (response) {
                            showAlert('New user has been created and an email has been sent to their registered email address.');
                            $state.go('propco.agent.home');
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
            } ]);

});