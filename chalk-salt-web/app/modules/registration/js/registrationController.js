'use strict';

define([ 'angular', './registrationRouting', './registrationService','../../CandDModal/js/CandDModalService'
        ], function(angular) {

    var registrationModule = angular.module('Registration.controller', [
            'Registration.router', 'System.configuration',
            'Registration.Service','CandDModal']);

    registrationModule.controller('RegistrationController', [
            '$scope',
            '$state',
            '$resource',
            'CHALKNDUST',
            'RegistrationService', 'userClassLookUpService','CandDModalService','$log',           
            function($scope, $state, $resource, CHALKNDUST, RegistrationService,userClassLookUpService,CandDModalService,$log) {

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
                
                userClassLookUpService.query(function(classes) {
                    $scope.classes = classes;
                    console.log(classes);
                },onRequestFailure);

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
                        	var modalOptions = {
                                    header : 'Note',
                                    body : 'New user has been created and an email has been sent to their registered email address.',
                                    btn : 'OK'
                                };

                            CandDModalService.showModal({}, modalOptions).then(function(result) {
                                    $log.info(result);
                                });
                            console.log(response);
                            $state.go('chalkanddust.login');
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
                function onRequestFailure(error) {
                        showAlert('danger', error.data.message);
                };
                
            } ]);

});