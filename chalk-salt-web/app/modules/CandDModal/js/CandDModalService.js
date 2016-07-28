'use strict';
define([ 'angular' ], function(angular) {
  var CandDModal = angular.module('CandDModal', [ 'System.configuration' ]);
  CandDModal.service('CandDModalService', [ '$modal', function($modal) {

    var modalDefaults = {
      backdrop : true,
      keyboard : true,
      modalFade : true,
      templateUrl : './modules/CandDModal/templates/CandDModal.tpl.html'
    };

    var modalOptions = {
      header : 'Note',
      body : 'Perform this action?',
      btn : 'Ok'
    };

    this.showModal = function(customModalDefaults, customModalOptions) {
      if (!customModalDefaults) {
        customModalDefaults = {};
      }
      customModalDefaults.backdrop = 'static';
      return this.show(customModalDefaults, customModalOptions);
    };

    this.showConfirm = function(customModalDefaults, customModalOptions) {
      if (!customModalDefaults) {
        customModalDefaults = {};
      }
      customModalDefaults.backdrop = 'static';
      customModalDefaults.keyboard = 'false';
      customModalDefaults.templateUrl = './modules/CandDModal/templates/CandDHeaderModal.tpl.html';
      return this.show(customModalDefaults, customModalOptions);
    };

    this.showGuestForm = function(customModalDefaults, customModalOptions) {
      var modalOptions = {}
      if (!customModalDefaults) {
        customModalDefaults = {};
      }
      customModalDefaults.backdrop = 'static';
      customModalDefaults.keyboard = 'false';
      customModalDefaults.templateUrl = './modules/CandDModal/templates/guest_user_login.tpl.html';
      var classesArray = [];
      for (var i = 0; i < customModalOptions.length; i++) {
        if (!angular.isUndefined(customModalOptions[i])) {
          classesArray.push(customModalOptions[i]);
        }
      }
      return this.showGuest(customModalDefaults, classesArray);
    }

    this.show = function(customModalDefaults, customModalOptions) {
      // Create temp objects to work with since we're in a singleton
      // service
      var tempModalDefaults = {};
      var tempModalOptions = {};

      // Map angular-ui modal custom defaults to modal defaults defined in
      // service
      angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

      // Map modal.html $scope custom properties to defaults defined in
      // service
      angular.extend(tempModalOptions, modalOptions, customModalOptions);

      if (!tempModalDefaults.controller) {

        tempModalDefaults.controller = function($scope, $modalInstance) {
          $scope.modalOptions = tempModalOptions;
          $scope.ok = function(result) {
            $modalInstance.close(result);
          };
          $scope.close = function() {
            $modalInstance.dismiss('cancel');
          };
          $scope.cancel = function(result) {
            $modalInstance.close(result);
          };

        }
      }

      return $modal.open(tempModalDefaults).result;
    };

    this.showGuest = function(customModalDefaults, customModalOptions) {
      // Create temp objects to work with since we're in a singleton
      // service
      var tempModalDefaults = {};
      var tempModalOptions = {};

      // Map angular-ui modal custom defaults to modal defaults
      // defined in service
      angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

      // Map modal.html $scope custom properties to defaults defined
      // in service
      angular.extend(tempModalOptions, {}, customModalOptions);

      if (!tempModalDefaults.controller) {

        tempModalDefaults.controller = function($scope, $modalInstance) {

          $scope.modalOptions = tempModalOptions;

          $scope.ok = function(result) {
            $modalInstance.close(result);
          };
          $scope.close = function() {
            $modalInstance.dismiss('cancel');
          };
          $scope.cancel = function(result) {
            $modalInstance.close(result);
          };

        }
      }

      return $modal.open(tempModalDefaults).result;
    };

  } ]);

});