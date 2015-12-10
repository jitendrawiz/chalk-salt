'use strict';
define([ 'angular' ], function(angular) {
    var propcoModal = angular.module('PropcoModal',['System.configuration']);
    propcoModal.service('PropcoModalService', [ '$modal',function($modal) {

        var modalDefaults = {
            backdrop : true,
            keyboard : true,
            modalFade : true,
            templateUrl : '/modules/PropCoModal/templates/propCoModal.tpl.html'
        };

        var modalOptions = {
            header:'Note',
            body : 'Perform this action?',
            btn : 'Ok'
        };

        this.showModal = function(customModalDefaults, customModalOptions) {
            if (!customModalDefaults)
                customModalDefaults = {};
            customModalDefaults.backdrop = 'static';
            return this.show(customModalDefaults, customModalOptions);
        };

        this.show = function(customModalDefaults, customModalOptions) {
            //Create temp objects to work with since we're in a singleton service
            var tempModalDefaults = {};
            var tempModalOptions = {};

            //Map angular-ui modal custom defaults to modal defaults defined in service
            angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

            //Map modal.html $scope custom properties to defaults defined in service
            angular.extend(tempModalOptions, modalOptions, customModalOptions);

            if (!tempModalDefaults.controller) {
                tempModalDefaults.controller = function($scope, $modalInstance) {
                    $scope.modalOptions = tempModalOptions;
                    $scope.modalOptions.ok = function(result) {
                        $modalInstance.dismiss('cancel');
                    };
                    $scope.modalOptions.close = function(result) {
                        $modalInstance.dismiss('cancel');
                    };
                }
            }

            return $modal.open(tempModalDefaults).result;
        };

    } ]);

});