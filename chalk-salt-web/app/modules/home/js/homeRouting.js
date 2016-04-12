'use strict';

define([ 'angular', 'text!../templates/home.tpl.html', 'text!../templates/contact_us.tpl.html', 'text!../templates/guest_user_login.tpl.html'], 
		function(angular, templateHome, templateContactUs, templateGuestUser) {
    
    var homeRouter = angular.module('Home.router', []);

    homeRouter.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.home', {
            url : '/home',
            template : templateHome,
            controller : 'HomeController as homeCtrl'
        }).state('chalkanddust.contactus', {
            url : '/contactus',
            template : templateContactUs,
            controller : 'HomeController as homeCtrl'
        }).state('chalkanddust.guestlogin', {
            url : '/user/guest',
            template : templateGuestUser,
            controller : 'HomeController as homeCtrl'
        });
    } ]);
});