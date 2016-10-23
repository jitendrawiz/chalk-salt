'use strict';

define([ 'angular', './homeRouting', './homeService', '../../CandDModal/js/CandDModalService' ], function(angular) {

  var homeModule = angular.module('Home.controller', [ 'Home.router', 'System.configuration', 'Home.service' ]);

  homeModule.controller('HomeController', [
      '$window',
      '$scope',
      '$state',
      '$resource',
      '$rootScope',
      'CHALKNDUST',
      'HomeService',
      'CandDModalService',
      '$log',
      'HomeGuestService',
      'userClassLookUpService',
      'userNotesLookupService',
      'StudentAchievementLookupService',
      '$timeout',
      '$location',
      '$anchorScroll',
      function($window, $scope, $state, $resource, $rootScope, CHALKNDUST, HomeService, CandDModalService, $log, HomeGuestService, userClassLookUpService, userNotesLookupService,
          StudentAchievementLookupService, $timeout, $location, $anchorScroll) {
        $window.scrollTo(0, 0);

        $scope.scrollTo = function(selector) {
          $location.hash(selector);

          // call $anchorScroll()
          $anchorScroll();
        };

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
        $scope.fullName = $window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
        $scope.userName = $window.localStorage.getItem(CHALKNDUST.USERNAME);
        $rootScope.contact_number1 = CHALKNDUST.CONTACT_NUMBER1;
        $rootScope.contact_number2 = CHALKNDUST.CONTACT_NUMBER2;
        $rootScope.contact_email = CHALKNDUST.CONTACT_EMAIL;
        $rootScope.copy_right = CHALKNDUST.COPY_RIGHT;
        $scope.version = CHALKNDUST.VERSION;
        $scope.build = CHALKNDUST.BUILD;
        $scope.email = CHALKNDUST.EMAIL;
        $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
        this.login = function() {
          $state.go('chalkanddust.home');
        };

        this.contactUs = function() {
          $state.go('chalkanddust.contactus');
        };

        this.sendMessage = function() {
          HomeService.save({}, $scope.enquiryDetails, function(response) {
            if (response) {
              var modalOptions = {
                header : 'Note',
                body : 'Your Enquiry email has been sent to the Admin,We will respond you soon',
                btn : 'OK'
              };

              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
              console.log(response);

            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        this.goToDiscussionRoom = function() {
          if ($scope.userName != null) {
            if ($scope.userName != "admin") {
              $state.go('chalkanddust.discussionroomfirstpage');
            } else {
              var modalOptions = {
                header : 'Note',
                body : "Sorry,Your don't have permission for discussion room",
                btn : 'OK'
              };

              CandDModalService.showModal({}, modalOptions).then(function(result) {
                console.log("inside admin page discussion room button click");
              });
            }
          } else {

            userClassLookUpService.query(function(response) {
              if (response) {
                $scope.classes = response;
                var modalOptions = $scope.classes;
                CandDModalService.showGuestForm({}, modalOptions).then(function(result) {
                  HomeGuestService.save({}, result, function(res) {
                    if (res) {
                      var modalOptions = {
                        header : 'Note',
                        body : 'Hurray! You can proceed now.',
                        btn : 'Proceed'
                      };

                      CandDModalService.showModal({}, modalOptions).then(function(result) {
                        $rootScope.username = res.userName;
                        $window.localStorage.setItem(CHALKNDUST.SECURUUID, res.securUuid);
                        $window.localStorage.setItem(CHALKNDUST.USERFULLNAME, res.userName);
                        $window.localStorage.setItem(CHALKNDUST.EDITFLAG, false);
                        $window.localStorage.setItem(CHALKNDUST.USERNAME, res.userName);
                        $state.go('chalkanddust.discussionroomfirstpage', {
                          'classId' : res.classId
                        });
                      });

                    }
                  }, function(error) {
                    showAlert('danger', error.data.message);
                  });
                });

              }
            }, function(error) {
              showAlert('danger', error.data.message);
            });

          }
        };
        $scope.showNotes = function(classId) {
          userNotesLookupService.query({
            classId : classId
          }, function(response) {
            $scope.modalOptions = [];
            if (response) {
              $scope.modalOptions = response;
              var notesLength = $scope.modalOptions.length;
              if (notesLength > 0) {
                CandDModalService.showNotes({}, $scope.modalOptions).then(function(result) {

                });
              } else {
                var modalOptions = {
                  header : 'Note',
                  body : 'Sorry! No notes found.',
                  btn : 'Ok'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {

                });
              }
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };
        $scope.isFirstStudentPresent = false;
        $scope.isSecondStudentPresent = false;
        $scope.isThirdStudentPresent = false;
        $scope.isFourthStudentPresent = false;
        getStudentAchievementList();
        var studentAchievementlist = {};
        function getStudentAchievementList() {
          StudentAchievementLookupService.get({}, function(response) {
            $timeout(function() {

            }, 5000)
            $scope.achievementData = response;
            if ($scope.achievementData.length > 0) {
              for (var i = 0; i < $scope.achievementData.length; i++) {
                studentAchievementlist[i] = $scope.achievementData[i];
              }
            }

            if (angular.isDefined(studentAchievementlist[0])) {
              $scope.firstStudent = studentAchievementlist[0];
              $scope.isFirstStudentPresent = true;
            }
            if (angular.isDefined(studentAchievementlist[1])) {
              $scope.secondStudent = studentAchievementlist[1];
              $scope.isSecondStudentPresent = true;
            }
            if (angular.isDefined(studentAchievementlist[2])) {
              $scope.thirdStudent = studentAchievementlist[2];
              $scope.isThirdStudentPresent = true;
            }
            if (angular.isDefined(studentAchievementlist[3])) {
              $scope.fourthStudent = studentAchievementlist[3];
              $scope.isFourthStudentPresent = true;
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });

        }

        $(".home-slider").owlCarousel({
          items : 1,
          loop : true,
          nav : true,
          autoplay : true,
          smartSpeed : 800,
          autoplayHoverPause : true,
        });

      } ]);
});