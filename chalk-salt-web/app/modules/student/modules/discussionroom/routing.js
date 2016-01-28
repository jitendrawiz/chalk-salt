'use strict';
define([ 'angular', 'text!./templates/discussionroomsubjects.tpl.html',
         'text!./templates/discussionroomtopics.tpl.html',
         'text!./templates/discussionroomcomments.tpl.html'],
		function(angular, templatediscussionroomsubjects,templatediscussionroomtopics,
				templatediscussionroomcomments) {

    var module = angular.module('Student.discussionroom.routing', []);

    module.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.discussionroomsubjects', {
            url : '/discussionroom/subjects',            
            template : templatediscussionroomsubjects,
            controller : 'DiscussionRoomSubjectsController as disCtrl'
        }).state('chalkanddust.topics', {
            url : '/discussionroom/topics',
            template : templatediscussionroomtopics,
            controller : 'DiscussionRoomTopicController as topicCtrl'
        }).state('chalkanddust.comments',{
        	url : '/discussionroom/comments',
            template : templatediscussionroomcomments,
            controller : 'DiscussionRoomCommentsController as commCtrl'        		
        });
    } ]);

});