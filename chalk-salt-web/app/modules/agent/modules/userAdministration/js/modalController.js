'use strict';
define([ 'angular' ], function(angular) {
    var modalControlModule = angular.module('Modal.controller', [ 'System.configuration' ]);
    modalControlModule.controller('ModalController', [ '$scope', '$modalInstance', 'items', '$timeout',
            function($scope, $modalInstance, items, $timeout) {
                $scope.dtDisabled = new Date();
                $scope.open = function() {
                    $timeout(function() {
                        $scope.opened = true;
                    });
                };
                $scope.ok = function() {
                    $modalInstance.close($scope.dtDisabled);
                };

                $scope.cancel = function() {
                    $modalInstance.dismiss('cancel');
                };

            } ]);
});