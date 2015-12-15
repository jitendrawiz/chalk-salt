'use strict';

define([ 'require', 'angular', './configuration', './httpInterceptor', './app',
        './directives', './filters','../modules/home/js/homeController', '../modules/login/js/loginController',
        '../modules/agent/js/agentController',
        
       '../modules/agent/modules/userAdministration/js/administrationController',
        '../modules/agent/modules/registration/js/registrationController' ],
        function(require, ng) {
            require([ 'domReady' ], function(domReady) {
                domReady(function() {
                    ng.bootstrap(document, [ 'chalkAndDust' ]);
                });
            });
        });