'use strict';
define([ 'angular' ], function(angular) {
    var module = angular.module('Common.services', [ 'ngResource', 'System.configuration' ]);

    /**
     * Get agency logo link service
     * 
     * @input documentUuid
     */
    module.factory('LogoLinkService', [ '$http', 'SYSTEM', function($http, SYSTEM) {
        return {
            get : function(documentUuid, successCallback, errorCallback) {
                $http.get(SYSTEM.ENV.API_END_POINT + 'agencies/logo/' + documentUuid).success(successCallback).error(errorCallback);
            }
        };
    } ]);

    /**
     * Get domain logo link service
     * 
     * @input documentUuid
     */

    module.factory('DomainLogoLinkService', [ '$http', 'SYSTEM', function($http, SYSTEM) {
        return {
            get : function(documentUuid, successCallback, errorCallback) {
                $http.get(SYSTEM.ENV.API_END_POINT + 'domains/logo/' + documentUuid).success(successCallback).error(errorCallback);
            }
        };
    } ]);

    /**
     * Get document download service
     */

    module.factory('DocumentDownloadService', [ '$http', 'SYSTEM', function($http, SYSTEM) {
        return {
            get : function(applicationId, documentUuid, successCallback, errorCallback) {
                $http.get(SYSTEM.ENV.API_END_POINT + 'applications/' + applicationId + '/documents/' + documentUuid, {
                    responseType : 'arraybuffer'
                }).then(successCallback, errorCallback);
            }
        };
    } ]);

    module.factory('ReportDownloadService', [ '$http', 'SYSTEM', function($http, SYSTEM) {
        return {
            get : function(applicationId, documentUuid, successCallback, errorCallback) {
                $http.get(SYSTEM.ENV.API_END_POINT + 'applications/' + applicationId + '/documents/' + documentUuid + '/application-report', {
                    responseType : 'arraybuffer'
                }).then(successCallback, errorCallback);
            }
        };
    } ]);

    /**
     * Get document download link service
     */
    module.factory('DocumentDownloadLinkService', [ 'SYSTEM', function(SYSTEM) {
        return {
            get : function(applicationId, documentUuid) {
                return SYSTEM.ENV.API_END_POINT + 'applications/' + applicationId + '/documents/' + documentUuid;
            }
        };
    } ]);

    /**
     * Get uploaded document path service for sample document
     */

    module.factory('GetUploadedDocumentService', [ '$http', 'SYSTEM', function($http, SYSTEM) {
        return {
            get : function(documentUuid, successCallback, errorCallback) {
                $http.get(SYSTEM.ENV.API_END_POINT + 'applications/documents/' + documentUuid).then(successCallback, errorCallback);
            }
        };
    } ]);

    /**
     * delete document/Annexure service
     */

    module.factory('DeleteDocumentService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'applications/documents/:documentUuid', {
            documentUuid : '@documentUuid',
            isAnnexure : '@isAnnexure'
        }, {
            remove : {
                method : 'DELETE'
            }
        });
    } ]);

    /**
     * Get system look up data service
     */
    module.factory('GetLookupDataService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'system/lookup-data', {}, {});
    } ]);

    /**
     * Get list of all branches for an agency
     * 
     * @input agencyId
     */
    module.factory('GetBranchListService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId/branches', {
            agencyId : '@agencyId'
        }, {});
    } ]);

    /**
     * Get case details service
     * 
     * @input case id
     */
    module.factory('GetCaseDetailsService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'cases/:caseId', {
            caseId : '@caseId'
        }, {
            get : {
                method : 'GET'
            }
        });
    } ]);

    /**
     * Get all roles for a user service
     */
    module.factory('GetUserRolesService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'users/:userId/roles', {
            userId : '@userId'
        }, {});
    } ]);

    /**
     * System Roles look up service
     */
    module.factory('RolesLookUpService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'system/roles', {}, {});
    } ]);

    /**
     * Get domain details service
     * 
     * @input domainId
     */
    module.factory('GetDomainDetailService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'domains/:domainId', {
            domainId : '@domainId'
        }, {});
    } ]);

    /**
     * Get agency details service
     * 
     * @input agencyId
     */
    module.factory('GetAgencyDetailService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId', {
            agencyId : '@agencyId'
        }, {});
    } ]);

    /**
     * Update Agency details service
     * 
     * @input agencyId
     */
    module.factory('UpdateAgencyDetailService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId', {
            agencyId : '@agencyId'
        }, {
            update : {
                method : 'PUT'
            }
        });
    } ]);

    /**
     * Add agency branch service
     * 
     * @input agencyId ( agency id )
     */
    module.factory('AddAgencyBranchService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId/branches', {
            agencyId : '@agencyId'
        }, {
            post : {
                method : 'POST'
            }
        });

    } ]);

    /**
     * Get branch details service
     * 
     * @input agencyBranchId
     * @input agencyId
     */
    module.factory('GetBranchDetailService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId/branches/:agencyBranchId', {
            agencyId : '@agencyId',
            agencyBranchId : '@agencyBranchId'
        }, {});
    } ]);

    /**
     * Update branch details service
     * 
     * @input agencyId
     * @input agencyBranchId
     */
    module.factory('UpdateBranchDetailService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'agencies/:agencyId/branches/:agencyBranchId', {
            agencyId : '@agencyId',
            agencyBranchId : '@agencyBranchId'
        }, {
            update : {
                method : 'PUT'
            }
        });
    } ]);

    /**
     * Add User Service(Domain Admin, Agency Admin, Branch User)
     * 
     */
    module.factory('AddUserService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'users', {}, {
            post : {
                method : 'POST'
            }
        });

    } ]);

    /**
     * Get user details service
     * 
     * @input userId
     */
    module.factory('GetUserDetailsService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'users/:userId', {
            userId : '@userId'
        }, {});
    } ]);

    /**
     * Update user details service
     * 
     * @input userId
     */
    module.factory('UpdateUserDetailsService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'users/:userId', {
            userId : '@userId'
        }, {
            update : {
                method : 'PUT'
            }
        });
    } ]);

    /**
     * Get package list service
     */
    module.factory('GetPackageListService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'packages', {}, {
            get : {
                method : 'GET'
            }
        });
    } ]);

    /**
     * Reset user password service
     * 
     * @input userId
     */
    module.factory('ResetPasswordService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'users/:userId/reset-password', {
            userId : '@userId'
        }, {
            update : {
                method : 'PUT'
            }
        });
    } ]);

    /**
     * Get list of accessible reports for the user
     */
    module.factory('GetAccessibleReportsService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'system/reports', {}, {});
    } ]);

    /**
     * Get embedded report iFrame url service /api/system/reports/{reportUri}
     */
    module.factory('GetEmbeddedReportUrlService', [ '$resource', 'SYSTEM', function($resource, SYSTEM) {
        return $resource(SYSTEM.ENV.API_END_POINT + 'system/report-uri', {
            reportUri : '@reportUri',
            agencyId : '@agencyId'
        }, {});
    } ]);

    /**
     * Get Titles service
     */
    module.factory('GetTitlesService', [
            'SYSTEM',
            function() {
                return {
                    get : function() {
                        var titles = [ 'Mr', 'Mrs', 'Ms', 'Miss', 'Admiral', 'AdmiralSir', 'Baron', 'Baroness', 'Begum', 'Bishop', 'Brigadier',
                                'Canon', 'Captain', 'Cardinal', 'Chief', 'Colonel', 'Commander', 'Commodore', 'Corporal', 'Count', 'Countess',
                                'Dame', 'Dr', 'Duchess', 'Duke', 'Earl', 'Emir', 'Father', 'FltLt', 'General', 'GroupCaptain', 'Hon', 'HonMrs',
                                'Lady', 'Lieutenant', 'Lord', 'LtCdr', 'LtCol', 'Madame', 'Major', 'MajorGeneral', 'Marchioness', 'Marquis',
                                'Mother', 'Nazir', 'Officer', 'PettyOfficer', 'Priest', 'Prince', 'Princess', 'Private', 'Professor', 'Rabbi',
                                'Rajah', 'Ranee', 'RearAdmiral', 'Rev', 'RevCanon', 'RevDr', 'RevFather', 'RtHon', 'RtRev', 'Sergeant', 'Sheikh',
                                'Sir', 'Sister', 'SquadronLeader', 'SubLt', 'Sultan', 'Sultana', 'TheHon', 'TheHonMrs', 'TheLady', 'TheRev',
                                'TheVenerable', 'Viscount', 'Viscountess', 'WingCommander' ];
                        return titles;
                    }
                };
            } ]);

    /**
     * Common utility functions module.
     */
    angular.module('Common.util', [ 'ngResource' ]).factory(
            'SystemUtil',
            [
                    '$window',
                    '$filter',
                    'SYSTEM',
                    'DOMAIN_NAME',
                    function($window, $filter, SYSTEM, DOMAIN_NAME) {
                        return {
                            /**
                             * Method to reset local storage.
                             * 
                             * @param list
                             *            of items to be removed. Add key of the
                             *            item to remove to 'itemList' array.
                             */
                            resetLocalStorage : function(itemList) {
                                if (!itemList) {
                                    var itemList = new Array();
                                    itemList = [ DOMAIN_NAME + SYSTEM.AGENT_DETAILS, DOMAIN_NAME + SYSTEM.APPLICATION_ID,
                                            DOMAIN_NAME + SYSTEM.CASE_ID, DOMAIN_NAME + SYSTEM.ADMIN_DETAILS,
                                            DOMAIN_NAME + SYSTEM.DOMAIN_ADMIN_DETAILS, DOMAIN_NAME + SYSTEM.DOMAIN_SELECTED_AGENCY,
                                            DOMAIN_NAME + SYSTEM.AUTH_TOKEN, DOMAIN_NAME + SYSTEM.TERMS_AND_CONDITIONS ];
                                }
                                angular.forEach(itemList, function(value) {
                                    $window.localStorage.removeItem(value);
                                });
                            },

                            /**
                             * Method to set an item in local storage. Receives
                             * Key and Value of item to set
                             */
                            setItem : function(key, value) {
                                if (angular.isObject(value)) {
                                    $window.localStorage.setItem(DOMAIN_NAME + key, JSON.stringify(value));
                                } else {
                                    $window.localStorage.setItem(DOMAIN_NAME + key, value);
                                }
                            },

                            /**
                             * Method to get an item from local storage.
                             * Receives Key of item and true/false or blank on
                             * basis of need to parse the object. Value of
                             * 'parseJson' will be true if the item is object
                             */
                            getItem : function(key, parseJson) {
                                var data = $window.localStorage.getItem(DOMAIN_NAME + key);
                                if (parseJson) {
                                    data = JSON.parse(data);
                                }
                                return data;
                            },

                            /**
                             * Method to remove an item from local storage on
                             * basis of item's key.
                             */
                            removeItem : function(key) {
                                $window.localStorage.removeItem(DOMAIN_NAME + key);
                            },

                            /**
                             * Method to convert MySql date to UK date format
                             */
                            displayUKDate : function(date) {
                                return $filter('date')(date, 'dd/MM/yyyy');
                            },

                            /**
                             * Method to convert MySql date to UK date time
                             */
                            displayUKDateTime : function(date) {
                                return $filter('date')(date, 'dd/MM/yyyy hh:mm a');
                            },

                            /**
                             * Method to format integers to have at least two
                             * digits.
                             */
                            twoDigits : function(n) {
                                return n < 10 ? '0' + n : n;
                            },

                            /**
                             * Method to calculate next duration date
                             * 
                             * @input duration in months
                             */
                            nextDurationDate : function(currentDate, duration) {
                                var date = new Date(currentDate);
                                var months = parseInt(duration, 10);
                                date.setMonth(date.getMonth() + months);
                                date.setDate(date.getDate() - 1);
                                return date;
                            }
                        };
                    } ]);
});