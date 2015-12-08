'use strict';
define([ 'angular','./administrationRouting'], function(angular) {
    var administrationModule = angular.module('Administration.controller', ['Administration.router','System.configuration','pretty-checkable']);
    administrationModule.controller('AdministrationController', [
                '$scope',
                '$state',
                '$resource',
                'PROPCO',
                '$http',
                '$uibModal',
                function($scope, $state, $resource, PROPCO,$http,$uibModal) {
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
                $scope.userManipulation = false;
                $scope.userData =[];
                console.log(JSON.stringify($scope.userData));
                $scope.startSearch = function (){
                    $http.get('./modules/agent/modules/userAdministration/data.json').success(function(result) {
                        $scope.userData= result;
                    }); 
                }
                $scope.performSearch = function(){
                    $http.get('./modules/agent/modules/userAdministration/data.json').success(function(result) {
                        $scope.userData= result;
                    }); 
                    $scope.userManipulation = true;
                    
                }
                
                $scope.tab = 1;

                $scope.setTab = function(newTab){
                  $scope.tab = newTab;
                };

                $scope.isSet = function(tabNum){
                  return $scope.tab === tabNum;
                };
                
                $scope.showCopyUser = function(){
                    $scope.enableCopyUser = true;
                }
                
                //Date picker functions
                $scope.today = function() {
                    $scope.dt = new Date();
                  };
                  $scope.today();

                  $scope.clear = function () {
                    $scope.dt = null;
                  };

                  $scope.toggleMin = function() {
                    $scope.minDate = $scope.minDate ? null : new Date();
                  };
                  $scope.toggleMin();
                  $scope.maxDate = new Date(2020, 5, 22);

                  $scope.open = function($event) {
                    $scope.status.opened = true;
                  };

                  $scope.setDate = function(year, month, day) {
                    $scope.dt = new Date(year, month, day);
                  };

                  $scope.dateOptions = {
                    formatYear: 'yy',
                    startingDay: 1
                  };

                  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
                  $scope.format = $scope.formats[0];

                  $scope.status = {
                    opened: false
                  };
                // For Dropdown
                  
                $scope.items = [
                                'The first choice!',
                                'And another choice for you.',
                                'but wait! A third!'
                              ];
                $scope.toggleDropdown = function($event) {
                    $event.preventDefault();
                    $event.stopPropagation();
                    $scope.status.isopen = !$scope.status.isopen;
                  };
                  //accordian
                  $scope.status = {
                          isFirstOpen: true,
                          isFirstDisabled: false
                        };
                  
                  $scope.groups=[];
                  $scope.kpis=[];
                  $scope.getGroupData = function(){
                      $http.get('./modules/agent/modules/userAdministration/templates/grpData.json').success(function(result) {
                          console.log(JSON.stringify(result));
                          $scope.groups = result.groups;
                          $scope.kpis = result.kpis;
                          console.log(JSON.stringify($scope.groups));
                          console.log(JSON.stringify($scope.kpis));
                      }); 
                  }
                  
                  //Modal
                  $scope.openModal = function(size) {
                      var modalInstance = $uibModal.open({
                        templateUrl: 'modules/agent/modules/userAdministration/templates/modal.tpl.html',
                        size:size,
                        resolve: {
                          items: function () {
                            return $scope.items;
                          }
                        }
                      });
                      
                      $scope.showModal= true;
                  }
                  
                  
                  
                              
                    
                }]);
    
});

