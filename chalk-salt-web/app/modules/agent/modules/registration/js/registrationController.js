'use strict';

define([ 'angular', './registrationRouting', './registrationService', 'angularDragDrop'
        ], function(angular) {

    var registrationModule = angular.module('Registration.controller', [
            'Registration.router', 'System.configuration',
            'Registration.Service', 'ngDragDrop']);

    registrationModule.controller('RegistrationController', [
            '$scope',
            '$state',
            '$resource',
            'PROPCO',
            'RegistrationService',
            'DomainListService',
            function($scope, $state, $resource, PROPCO, RegistrationService,
                    DomainListService) {

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
                
                $scope.domainsList = [];
                $scope.defaultDomain =[];
                $scope.userDetails = {};
                $scope.userDetails.defaultDomain ={};
                $scope.userDetails.assignedDomains = [];
                $scope.domainListValidation = {
                    accept : function() {

                        if ( $scope.defaultDomain.length >= 2) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                };

                $scope.inputType = 'password';
                $scope.hideShowPassword = function() {
                    if ($scope.inputType === 'password') {
                        $scope.inputType = 'text';
                    } else {
                        $scope.inputType = 'password';
                    }
                };

                
                this.register = function() {
                    var abc= $scope.defaultDomain[0].name;
                    $scope.userDetails.defaultDomain =  abc;
                        
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
                function getDomainsList() {
                    DomainListService.get({}, function(response) {
                        $scope.domainsList = response.domainNames;
                    }, function(error) {
                        console.log(error.data.message);
                        showAlert('danger', 'No domain has been specified');
                    });
                }
                getDomainsList();
            } ]);

});