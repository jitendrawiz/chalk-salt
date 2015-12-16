'use strict';
define([ 'angular','./agentHomeService','angularDragDrop' ], function(angular) {

    var dashboardModule = angular.module('Dashboard.controller', ['AgentHome.Service']);
    dashboardModule.controller('DashboardController', [
            '$scope',
            '$state',
            '$http',
            '$resource',
            'PROPCO',
            '$rootScope',
            'DashboardSaveService',
            'GetDashboardService','$log',
            function($scope, $state,$http, $resource, PROPCO, $rootScope, DashboardSaveService, GetDashboardService,$log) {
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
                getDashboard();
                $rootScope.editWorkspace = false;

                $scope.bookmarkedItems = [];
                $scope.bookmarkedItemsColumnOne = [];
                $scope.bookmarkedItemsColumnTwo = [];
                
                $rootScope.menuItemsList = [];
                $http.get('./resources/i18n/locale-en_US_globalMenus.json').success(function(result) {
                    $rootScope.menuItemsList= result.menuItemsList;
                });
                

                $scope.hoverIn = function() {
                    this.hoverEdit = true;
                };

                $scope.hoverOut = function() {
                    this.hoverEdit = false;
                };

                $log.info("hi");
                $scope.remove = function(index, modelKey) {
                    if (modelKey === 'columnOne') {
                        $scope.iconsListColumnOne.splice(index, 1);
                    } else if (modelKey === 'columnTwo') {
                        $scope.bookmarkedItemsColumnOne.splice(index, 1);
                    } else if (modelKey === 'columnThree') {
                        $scope.iconsListColumnTwo.splice(index, 1);
                    } else if (modelKey === 'columnFour') {
                        $scope.bookmarkedItemsColumnTwo.splice(index, 1);
                    }

                };
                
                
                $scope.iconsList = [];
                $scope.iconsListColumnOne = [];
                $scope.iconsListColumnTwo = [];
                function getDashboard() {
                    GetDashboardService.get({}, function(response) {
                        $scope.iconsList = response;
                        $scope.iconsListColumnOne = $scope.iconsList.slice(0, 8);
                        $scope.iconsListColumnTwo = $scope.iconsList.slice(9, 16);
                    }, function(error) {
                        console.log(error.data);
                        $log.info(error.data);
                    });
                }

                this.saveDashboard = function() {

                    $scope.bookmarkedItems = $scope.iconsListColumnOne.concat($scope.iconsListColumnTwo, $scope.bookmarkedItemsColumnOne,
                            $scope.bookmarkedItemsColumnTwo);
                    angular.forEach($scope.bookmarkedItems, function(value, key) {
                        delete value.drag;
                    });
                    var selectedItems = $scope.bookmarkedItems;
                    DashboardSaveService.save(selectedItems, function(response) {
                        if (response) {
                            showAlert('success', 'All icons saved succesfully');
                            getDashboard();
                            $rootScope.editWorkspace = false;
                            $scope.bookmarkedItemsColumnOne = [];
                            $scope.bookmarkedItemsColumnTwo = [];
                        }
                    }, function(error) {
                        console.log(error.data.message);
                    });
                };
                
                this.showEditScreen = function(){
                    $rootScope.editWorkspace = true;
                };
                
               this.disableEditScreen = function(){
                   $rootScope.editWorkspace = false;
               }
                
            } ]);
});