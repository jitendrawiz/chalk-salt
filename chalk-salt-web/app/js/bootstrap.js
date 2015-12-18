'use strict';

define([ 'require', 
         'angular', 
         './configuration', 
         './httpInterceptor', 
         './app',
         './directives', 
         './filters',
         '../modules/home/js/homeController', 
         '../modules/login/js/loginController',
         '../modules/registration/js/registrationController',
         '../modules/student/js/studentController'],
        function(require, ng) {
            require([ 'domReady' ], function(domReady) {
                domReady(function() {
                    ng.bootstrap(document, [ 'chalkAndDust' ]);
                });
            });
        });