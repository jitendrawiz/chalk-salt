'use strict';
define([ 'angular', 'text!./templates/discussionroomsubjects.tpl.html',
         'text!./templates/discussionroomtopics.tpl.html',
         'text!./templates/discussionroomcomments.tpl.html',
         'text!./templates/topicrequest.tpl.html'],
		function(angular, templatediscussionroomsubjects,templatediscussionroomtopics,
				templatediscussionroomcomments,templatetopicrequest) {

    var module = angular.module('Student.discussionroom.routing', []);

    module.config([ '$stateProvider', function($stateProvider) {
        $stateProvider.state('chalkanddust.discussionroomsubjects', {
            url : '/discussionroom/subjects',            
            template : templatediscussionroomsubjects,
            controller : 'DiscussionRoomSubjectsController as disCtrl'
        }).state('chalkanddust.discussionroomtopics', {
            url : '/discussionroom/topics/:subjectId',
            template : templatediscussionroomtopics,
            controller : 'DiscussionRoomTopicController as topicCtrl'
        }).state('chalkanddust.discussionroomcomments',{
        	url : '/discussionroom/comments/:topicId',
            template : templatediscussionroomcomments,
            controller : 'DiscussionRoomCommentsController as commCtrl'        		
        }).state('chalkanddust.topicrequest',{
        	url : '/discussionroom/topicsrequest',
            template : templatetopicrequest,
            controller : 'DiscussionRoomTopicRequestController as requestCtrl'        		
        });
    } ]);

});