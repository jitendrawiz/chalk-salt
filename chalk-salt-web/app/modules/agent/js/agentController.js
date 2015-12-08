'use strict';
define([ 'angular', './agentRouting', './agentHomeService', 'angularDragDrop' ], function(angular) {

    var headerModule = angular.module('Agent.controller', [ 'Agent.router', 'AgentHome.Service', 'ngDragDrop' ]);
    headerModule.controller('AgentController', [
            '$scope',
            '$state',
            '$http',
            '$resource',
            'PROPCO',
            '$rootScope',
            'AgentHomeService',
            'IconFetchService',
            function($scope, $state,$http, $resource, PROPCO, $rootScope, AgentHomeService, IconFetchService) {
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
             //   getIconList();

                $scope.bookmarkedItems = [];
                $scope.bookmarkedItemsPartOne = [];
                $scope.bookmarkedItemsPartTwo = [];
                
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

                $scope.remove = function(index, modelKey) {
                    if (modelKey === 'partOne') {
                        $scope.iconsListPartOne.splice(index, 1);
                    } else if (modelKey === 'partTwo') {
                        $scope.bookmarkedItemsPartOne.splice(index, 1);
                    } else if (modelKey === 'partThree') {
                        $scope.iconsListPartTwo.splice(index, 1);
                    } else if (modelKey === 'partFour') {
                        $scope.bookmarkedItemsPartTwo.splice(index, 1);
                    }

                };
                
                
                $scope.iconsList = [];
                $scope.iconsListPartOne = [];
                $scope.iconsListPartTwo = [];
                function getIconList() {
                    IconFetchService.save({}, function(response) {
                        $scope.iconsList = response.userIcons;
                        $scope.iconsListPartOne = $scope.iconsList.slice(0, 7);
                        $scope.iconsListPartTwo = $scope.iconsList.slice(8, 16);
                    }, function(error) {
                        console.log(error.data.message);
                    });
                }

                this.saveIconList = function() {

                    $scope.bookmarkedItems = $scope.iconsListPartOne.concat($scope.iconsListPartTwo, $scope.bookmarkedItemsPartOne,
                            $scope.bookmarkedItemsPartTwo);
                    angular.forEach($scope.bookmarkedItems, function(value, key) {
                        delete value.drag;
                        delete value.link;
                        delete value.name;
                        console.log(key);
                    });
                    var selectedItems = $scope.bookmarkedItems;
                    var iconsToSave = {
                        userIcons : selectedItems
                    };
                    AgentHomeService.save(iconsToSave, function(response) {
                        if (response) {
                            showAlert('success', 'All icons saved succesfully');
                            getIconList();
                            $scope.editWorkspace = false;
                            $scope.bookmarkedItemsPartOne = [];
                        }
                    }, function(error) {
                        console.log(error.data.message);
                        // showAlert('danger', error.data.message);
                    });
                };
                
            } ]);
});