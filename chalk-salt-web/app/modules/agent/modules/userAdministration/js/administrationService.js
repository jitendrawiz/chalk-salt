'use strict';
define([ 'angular' ], function(angular) {
    
    var administrationService = angular.module('Administration.Service', [ 'System.configuration' ]);
    
    administrationService.factory('GetDomainListService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/domains',{},{
            get : {
                method : 'GET',
                isArray:true
            }
        });
        } ]);
    administrationService.factory('QuickUserRegistrationService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/register',{}, {
            save : {
                method : 'POST'
            }
            
        });
        } ]);
    administrationService.factory('GetUsersListService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/users',{}, {
            get : {
                method : 'GET',
                isArray:true
            }
            
        });
        } ]);
        
    administrationService.factory('GetKPIListService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/kpis',{}, {
            get : {
                method : 'GET',
                isArray:true
            }
            
        });
        } ]);
    
    administrationService.factory('DisableUserService', [ '$resource', 'ENV', function($resource, ENV) {
        return $resource(ENV.API_END_POINT + 'private/user/disable',{}, {
            save : {
                method : 'POST'
            }
            
        });
        } ]);
    
        administrationService.factory('GetOfficeService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/offices',{}, {
                get : {
                    method : 'GET',
                    isArray:true
                }
                
            });
            } ]);
        
        administrationService.factory('GetUserTemplatesService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/templates',{}, {
                get : {
                    method : 'GET',
                    isArray:true
                }
                
            });
            } ]);
        
        administrationService.factory('GetMailServersService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/mailservers',{}, {
                get : {
                    method : 'GET',
                    isArray:true
                }
                
            });
            } ]);
        
        administrationService.factory('CreateUserService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/info',{}, {
                save : {
                    method : 'POST'
                }
                
            });
            } ]);
        
        administrationService.factory('GetUserDetailsService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/info/:securUuid',{
                securUuid : '@securUuid'
            }, {
                get : {
                    method : 'GET'
                }
                
            });
            } ]);
        
        administrationService.factory('GetWorkflowTemplatesService', [ '$resource', 'ENV', function($resource, ENV) {
            return $resource(ENV.API_END_POINT + 'private/user/workflow/templates',{
                securUuid : '@securUuid'
            }, {
                get : {
                    method : 'GET'
                }
                
            });
            } ]);
        
        
        
    
});


