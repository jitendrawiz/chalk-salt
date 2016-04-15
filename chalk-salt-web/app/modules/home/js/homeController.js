'use strict';

define(
		[ 'angular', './homeRouting', './homeService',
				'../../CandDModal/js/CandDModalService' ],
		function(angular) {

			var homeModule = angular.module('Home.controller', [ 'Home.router',
					'System.configuration', 'Home.service' ]);

			homeModule
					.controller(
							'HomeController',
							[
									'$window',
									'$scope',
									'$state',
									'$modal',
									'$resource',
									'$rootScope',
									'CHALKNDUST',
									'HomeService',
									'CandDModalService',
									'$log',
									function($window, $scope, $state, $modal,
											$resource, $rootScope, CHALKNDUST,
											HomeService, CandDModalService,
											$log) {
										$window.scrollTo(0, 0);
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
										this.backToTop = function() {
											$window.scrollTo(0, 0);
										};
										$scope.fullName = $window.localStorage
												.getItem(CHALKNDUST.USERFULLNAME);
										$scope.userName = $window.localStorage
												.getItem(CHALKNDUST.USERNAME);
										console.log($scope.userName);
										$scope.version = CHALKNDUST.VERSION;
										$scope.build = CHALKNDUST.BUILD;
										$scope.email = CHALKNDUST.EMAIL;
										$scope.releaseDate = CHALKNDUST.RELEASE_DATE;
										this.login = function() {
											$state.go('chalkanddust.home');
											console.log("i am in");
										};

										this.contactUs = function() {
											$state.go('chalkanddust.contactus');
											console.log("i am in");
										};

										this.sendMessage = function() {
											HomeService
													.save(
															{},
															$scope.enquiryDetails,
															function(response) {
																if (response) {
																	var modalOptions = {
																		header : 'Note',
																		body : 'Your Enquiry email has been sent to the Admin,We will respond you soon',
																		btn : 'OK'
																	};

																	CandDModalService
																			.showModal(
																					{},
																					modalOptions)
																			.then(
																					function(
																							result) {
																						$state
																								.reload();
																					});
																	console
																			.log(response);

																}

															},
															function(error) {
																showAlert(
																		'danger',
																		error.data.message);
															});
										};

										this.goToDiscussionRoom = function() {
											if ($scope.userName != null) {
												if ($scope.userName != "admin") {
													$state
															.go('chalkanddust.discussionroomsubjects');
												} else {
													var modalOptions = {
														header : 'Note',
														body : "Sorry,Your don't have permission for discussion room",
														btn : 'OK'
													};

													CandDModalService
															.showModal({},
																	modalOptions)
															.then(
																	function(
																			result) {
																		console
																				.log("inside admin page discussion room button click");
																	});
												}
											} else {
												/*
												 * var modalOptions = { header :
												 * 'Note', body : "Please Login
												 * to go to discussion room",
												 * btn : 'OK' };
												 * 
												 * CandDModalService.showModal({},
												 * modalOptions).then(function(result) {
												 * $state.go('chalkanddust.login');
												 * });
												 */
												$modal.open({
													templateUrl : 'modules/home/templates/guest_user_login.tpl.html',
												});
											}
										};
										
										
										this.close = function ($modal) {
											$modal.dismiss('cancel');
										};
										
										this.checkGuestUser=function(){
											console.log("Inside Check Guest User");
										};
									} ]);
		});