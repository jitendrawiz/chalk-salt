'use strict';
define([ 'angular','./administrationRouting','./administrationService','./modalController'], function(angular) {
    var administrationModule = angular.module('Administration.controller', ['Administration.router','System.configuration','pretty-checkable','Administration.Service','Modal.controller']);
    administrationModule.controller('AdministrationController', [
                '$scope',
                '$state',
                '$resource',
                'PROPCO',
                '$http',
                '$uibModal','GetDomainListService','QuickUserRegistrationService','GetUsersListService',
                'GetKPIListService','DisableUserService','GetOfficeService','GetUserTemplatesService','GetMailServersService','CreateUserService','GetUserDetailsService','GetWorkflowTemplatesService',
                function($scope, $state, $resource, PROPCO,$http,$uibModal,GetDomainListService,QuickUserRegistrationService,
                        GetUsersListService,GetKPIListService,DisableUserService,GetOfficeService,GetUserTemplatesService,GetMailServersService,CreateUserService,GetUserDetailsService,GetWorkflowTemplatesService) {
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
                
                //User List serach
                $scope.startSearch = function (){
                    GetUsersListService.get({},function(response){
                        $scope.usersList = response;
                        
                    })
                }
                $scope.performSearch = function(){
                    $scope.userManipulation = true;
                }
                
                //Enable/disable Permission window
                $scope.showPermissions= false;
                
                $scope.enablePermissions = function (){
                    $scope.showPermissions= true;
                    $scope.displayPermissions = true;
                    $scope.enableCopyUser = false;
                }   
                
                $scope.disablePermissions = function (){
                    $scope.showPermissions= false;
                    $scope.displayPermissions = false;
                }
                $scope.disableCopyUser = function() {
                    $scope.enableCopyUser = false;
                    $scope.displayPermissions = false;
                }
                //Enable tabbing
                $scope.tab = 1;

                $scope.setTab = function(newTab){
                  $scope.tab = newTab;
                };

                $scope.isSet = function(tabNum){
                  return $scope.tab === tabNum;
                };
                
                $scope.showCopyUser = function(user){
                    $scope.enableCopyUser = true;
                    $scope.selectedUser = user;
                    fetchUserDetails();
                }
                
                //Date picker functions
                $scope.today = function() {
                    $scope.dt = new Date();
                  };
                  $scope.today();

                  $scope.clear = function () {
                    $scope.dt = null;
                  };
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

                  $scope.format = 'dd/MM/yyyy';

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
                  
                  $scope.permissions = {};
                  //Accordian
                  $scope.status = {
                          isFirstOpen: true,
                          isFirstDisabled: false
                        };
                  
                  $scope.groups=[];
                 function getGroupData(){
                      $http.get('./modules/agent/modules/userAdministration/templates/grpData.json').success(function(result) {
                          $scope.groups = result.groups;
                          $scope.accessRights = result.accessRights;
                          $scope.groupAccess = result.groupAccess;
                      }); 
                  }
                  
                  //Accordian Open/Close
                  $scope.switchAccordianIcon = function(){
                      $scope.status.open = !$scope.status.open;
                  }
                  
                  //Modal Implementation
                  $scope.openModal = function(size) {
                      var modalInstance = $uibModal.open({
                        templateUrl: 'modules/agent/modules/userAdministration/templates/modal.tpl.html',
                        controller: 'ModalController',
                        size:size,
                        resolve: {
                          items: function () {
                            return $scope.items;
                          }
                        }
                      });
                      
                      $scope.showModal= true;
                  }
                $scope.disableUser = function(){
                    var modalOptions = {
                            header:'Note',
                            body: 'New User has been created and an email has been sent to their register email address',
                            btn :'OK'
                        };
                    
                    PropCoModalService.showModal({}, modalOptions).then(function (result) {
                        console.log(result);
                    });
                }; 
                
                $scope.domainsList = [];
                $scope.userDetails = {};
                $scope.defaultDomain =[];
                $scope.assignedDomains = [];
                $scope.domainListValidation = {
                    accept : function() {

                        if ( $scope.defaultDomain.length >= 1 ) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                };

                // Show Password
                $scope.inputType = 'password';
                $scope.hideShowPassword = function() {
                    if ($scope.inputType === 'password') {
                        $scope.inputType = 'text';
                    } else {
                        $scope.inputType = 'password';
                    }
                };
                
                $scope.pwd = 'password';
                $scope.revealPassword = function() {
                    if ($scope.pwd === 'password') {
                        $scope.pwd = 'text';
                    } else {
                        $scope.pwd = 'password';
                    }
                };
                
                
                //Fetch Domain List
                $scope.domainsList=[];
                function getDomainsList() {
                    GetDomainListService.get({}, function(response) {
                        $scope.domainsList = response;
                        angular.forEach($scope.domainsList, function(value, key) {
                            value.isDefault = false;
                        })
                    }, function(error) {
                        console.log(error.data.message);
                        showAlert('danger', 'No domain has been specified');
                    });
                }
                 //Register user without access details
                
                $scope.register = function() {
                    
                  if($scope.defaultDomain.length){
                      $scope.defaultDomain[0].isDefault = true;
                  }
                    
                    $scope.domainDetails = $scope.assignedDomains.concat($scope.defaultDomain);
                    $scope.userDetails.domainDetails = $scope.domainDetails;
                    $scope.userDetails.sendEmail = true;
                    console.log(JSON.stringify($scope.userDetails));
                    QuickUserRegistrationService.save({}, $scope.userDetails, function(
                            response) {
                        if (response) {
                           $scope.disableUser();
                            $scope.showPermissions= true;
                        }
                        
                    }, function(error) {
                        showAlert('danger', error.data.message);
                    });
                };
                
                //Register user with access details
                $scope.saveUserInfo = function(){
                    if($scope.defaultDomain.length){
                        $scope.defaultDomain[0].isDefault = true;
                    }
                      
                      $scope.domainDetails = $scope.assignedDomains.concat($scope.defaultDomain);
                      $scope.userDetails.domainDetails = $scope.domainDetails;
                      $scope.userDetails.sendEmail = true;
                      $scope.userDetails.officeName = $scope.selectedOffice.officeName;
                      $scope.userDetails.officeUuid = $scope.selectedOffice.officeUuid;
                      $scope.userDetails.mailServerName = $scope.selectedServer.mailServerName;
                      $scope.userDetails.mailServerUuid = $scope.selectedServer.mailServerUuid;
                      console.log(JSON.stringify($scope.userDetails));
                    CreateUserService.save({},$scope.userDetails, function(response) {
                        if(response){
                            $scope.disableUser();
                            $scope.setTab(1);   
                        }
                        
                    });
                };
               function getKPIList(){
                    GetKPIListService.get({},function(response){
                        $scope.KPIs = response;
                    })
                };
                  
                this.disableUser = function(){
                    DisableUserService.save({},$scope.disabledFrom,function(response){
                        if(response){
                            alert('done');
                        }
                    })
                };
                
                function getOffices(){
                    GetOfficeService.get({},function(response){
                        $scope.offices = response;
                    })
                };
                
                function getUserTemplates(){
                    GetUserTemplatesService.get({},function(response){
                        $scope.userTemplates = response;
                    })
                };
                
                function getMailServers(){
                    GetMailServersService.get({},function(response){
                        $scope.mailServers = response;
                    })
                };
              
                //dropdown slected
                $scope.selectServer = function(item){
                    $scope.selectedServer = item;
                }
                
                $scope.selectTemplate = function(template) {
                    $scope.selectedTemplate = template;
                }
                
                $scope.selectOffice = function(office) {
                    $scope.selectedOffice = office;
                }
                
                $scope.selectDomain = function(domain) {
                    $scope.selectedDomain = domain;
                }
                
                //Fetch UserDetails
               function fetchUserDetails(){
                    FetchUserDetails.get({securUuid:$scope.selectedUser.securUuid},function(response){
                        if(response){
                            $scope.copyUserInfo = response;
                            console.log(JSON.stringify($scope.copyUserInfo));
                            $scope.userDetails= {};
                            $scope.userDetails.jobTitle = $scope.copyUserInfo.jobTitle;
                            /*$scope.userDetails.negotiator = $scope.copyUserInfo.negotiator;
                            $scope.userDetails.omitDiary = $scope.copyUserInfo.omitDiary;
                            $scope.userDetails.disableVisibility = $scope.copyUserInfo.disableVisibility;*/
                            $scope.userDetails.officeUuid = $scope.copyUserInfo.officeUuid;
                            $scope.userDetails.officeName = $scope.copyUserInfo.officeName;
                            $scope.userDetails.commission = $scope.copyUserInfo.commission;
                            $scope.userDetails.mailServerName = $scope.copyUserInfo.mailServerName;
                            $scope.userDetails.mailServerUuid = $scope.copyUserInfo.mailServerUuid;
                            $scope.userDetails.docMgtUser = $scope.copyUserInfo.docMgtUser;
                            $scope.userDetails.docMgtPassword = $scope.copyUserInfo.docMgtPassword;
                            $scope.userDetails.referencingId = $scope.copyUserInfo.referencingId;
                            $scope.userDetails.maxResults = $scope.copyUserInfo.maxResults;
                            $scope.userDetails.diaryClashLevel = $scope.copyUserInfo.diaryClashLevel;
                            $scope.userDetails.description = $scope.copyUserInfo.description;
                            $scope.userDetails.allowedInstances = $scope.copyUserInfo.allowedInstances;
                            
                        }
                    })
                    
                };
                
                
                //Function called on Page load
                getDomainsList();
                getGroupData();
                getOffices();
                getUserTemplates();
                getMailServers();
                getKPIList();
                
                    
                }]);
});

