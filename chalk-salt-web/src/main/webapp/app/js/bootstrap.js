'use strict';

define([ 'require', 'angular', './configuration', './httpInterceptor', './app',
        './directives', './filters', '../modules/login/js/loginController',
        '../modules/agent/js/agentController',
        '../modules/agent/modules/registration/js/registrationController' ],
        function(require, ng) {
            require([ 'domReady' ], function(domReady) {
                domReady(function() {
                    ng.bootstrap(document, [ 'chalkAndDust' ]);
                });
            });
        });
