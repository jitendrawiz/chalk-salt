'use strict';
define([ 'angular', 'text!./templates/firstPage.tpl.html',
         'text!./templates/secondPage.tpl.html',
         'text!./templates/discussionroomcomments.tpl.html',
         'text!./templates/topicrequest.tpl.html'],
		function(angular, templatediscussionFirstPage,templatediscussionroomSecondPage,
				templatediscussionroomcomments,templatetopicrequest) {

    var module = angular.module('Student.discussionroom.routing', []);

    module.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.discussionroomfirstpage', {
            url : '/discussionroom/firstPage/:classId',            
            template : templatediscussionFirstPage,
            controller : 'DiscussionRoomFirstController as disCtrl'
        }).state('chalkanddust.discussionroomtopic', {
            url : '/discussionroom/topic/:topicId',
            template : templatediscussionroomSecondPage,
            controller : 'DiscussionRoomSecondController as secondCtrl'
        }).state('chalkanddust.topicrequest',{
        	url : '/discussionroom/topicsrequest',
            template : templatetopicrequest,
            controller : 'DiscussionRoomTopicRequestController as requestCtrl'        		
        });
    } ]);

});