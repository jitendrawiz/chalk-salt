'use strict';

define([ 'angular', './studentRouting', './studentService', '../../CandDModal/js/CandDModalService', './testController', './testService' ], function(angular) {

  var homeModule = angular.module('Student.controller', [ 'Student.router', 'System.configuration', 'Student.service', 'Student.discussionroom.controller', 'CandDModal',
      'Student.testcontroller', 'Student.testservice' ]);

  homeModule.controller('StudentController', [
      '$window',
      '$scope',
      '$state',
      '$resource',
      '$http',
      '$location',
      '$rootScope',
      'CHALKNDUST',
      'GetUserDetailsService',
      'StudentProfileUpdateService',
      'ChangePasswordService',
      'UpdateProfilePhotoService',
      'GetUserPhotoService',
      'DeletePhotoService',
      'CandDModalService',
      'GetDashboardDataBySubject',
      '$stateParams',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, GetUserDetailsService, StudentProfileUpdateService, ChangePasswordService,
          UpdateProfilePhotoService, GetUserPhotoService, DeletePhotoService, CandDModalService, GetDashboardDataBySubject, $stateParams) {
        $scope.uploadedlogo = {};
        $scope.showVideoDiv = false;

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
        $scope.securUuid = $window.localStorage.getItem(CHALKNDUST.SECURUUID);
        $scope.fullName = $window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
        if ($window.localStorage.getItem(CHALKNDUST.EDITFLAG) == "false") {
          $scope.editFlag = false;
        } else if ($window.localStorage.getItem(CHALKNDUST.EDITFLAG) == "true") {
          $scope.editFlag = true;
        }
        $scope.backToDashBoard = function() {
          $state.go('chalkanddust.profile');
        };
        $scope.version = CHALKNDUST.VERSION;
        $scope.build = CHALKNDUST.BUILD;
        $scope.email = CHALKNDUST.EMAIL;
        $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
        GetUserDetailsService.get({
          securUuid : $scope.securUuid
        }, function(response) {
          if (response) {
            $scope.userInfo = response;
            $scope.subjects = $scope.userInfo.subjects;

            $scope.academicInfo = $scope.userInfo.academicInfo;
            $window.localStorage.setItem(CHALKNDUST.CLASSID, $scope.academicInfo.studentClassId);
            $scope.parentsInfo = $scope.userInfo.parentsInfo;
            $scope.studentSubjects = "";
            if ($scope.subjects != null) {
              for (var i = 0; i < $scope.subjects.length; i++) {
                if ($scope.studentSubjects == "") {
                  $scope.studentSubjects = $scope.subjects[i].subjectName;
                } else {
                  $scope.studentSubjects = $scope.studentSubjects + ", " + $scope.subjects[i].subjectName;
                }

              }
            }

            GetUserPhotoService.get({
              securUuid : $scope.securUuid
            }, function(result) {
              $scope.userProfilePhoto = result.photolink;
            }, function(error) {
              showAlert('danger', error.data.message);
            });
          }
        }, function(error) {
          showAlert('danger', error.data.message);
        });

        this.updateProfile = function() {
          $scope.userInfo.academicInfo = $scope.academicInfo;
          $scope.userInfo.parentsInfo = $scope.parentsInfo;
          StudentProfileUpdateService.save({}, $scope.userInfo, function(response) {
            if (response) {
              console.log(response);
              $window.localStorage.setItem(CHALKNDUST.EDITFLAG, false);
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        $scope.editProfile = function() {
          $window.localStorage.setItem(CHALKNDUST.EDITFLAG, true);
          $state.reload();
        };

        this.changePassword = function() {
          ChangePasswordService.save({}, $scope.userInfo, function(response) {
            if (response) {
              console.log(response);
              $window.localStorage.setItem(CHALKNDUST.EDITFLAG, false);
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /**
         * Function to upload profile photo
         */
        $scope.updateProfilePhoto = function(fileData) {
          var file = fileData;
          var formData = new FormData();
          formData.append('file', file);
          formData.append('name', file.name);
          formData.append('documentType', file.type);
          UpdateProfilePhotoService.upload(formData, $scope.securUuid, function(response) {
            showAlert("success", "Profile Photo updated successfully.");
          }, onRequestFailure);
        };

        function onRequestFailure(error) {
          showAlert('danger', error.data.message);
        }
        ;
        /**
         * Function to enable/disable upload profile photo button
         */
        $scope.disableUploadPhotoButton = function() {
          var disable = true;
          var inputVal = document.getElementById("inputUploadProfilePhoto").value;

          if (inputVal) {
            disable = false;
          }

          return disable;
        };

        $scope.isUndefined = function(thing) {
          return (typeof thing === "undefined");
        };

        /**
         * Function to delete uploaded profile photo
         */
        this.deleteProfilePhoto = function() {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete profile photo?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            DeletePhotoService.remove({
              securUuid : $scope.securUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : response.message,
                  btn : 'OK'
                };
                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  showAlert('success', response.message);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })
          });
        };

        if ($window.localStorage.getItem(CHALKNDUST.TABNUMBER) != null) {
          $scope.tab = $window.localStorage.getItem(CHALKNDUST.TABNUMBER);
          var item = {};
          item.subjectId = $window.localStorage.getItem(CHALKNDUST.SUBJECTID);
          item.subjectName = $window.localStorage.getItem(CHALKNDUST.SUBJECTNAME);
          updatePageDetailsOnClick(item);
        } else {
          $scope.tab = 1;
        }

        $scope.setTab = function(newTab) {
          $scope.tab = newTab;
          $window.localStorage.setItem(CHALKNDUST.TABNUMBER, newTab);
          $scope.subjectName = null;
        };

        $scope.isSet = function(tabNum) {
          return $scope.tab === tabNum;
        };

        $scope.setTabSubject = function(newtab, item) {
          $scope.tab = newtab;
          $window.localStorage.setItem(CHALKNDUST.TABNUMBER, newtab);
          updatePageDetailsOnClick(item);
        }

        function updatePageDetailsOnClick(item) {
          $window.localStorage.setItem(CHALKNDUST.SUBJECTID, item.subjectId);
          $window.localStorage.setItem(CHALKNDUST.SUBJECTNAME, item.subjectName);
          $scope.subjectName = "/ " + item.subjectName;
          $scope.showVideoDiv = false;
          $scope.showNotesDiv = false;
          $scope.videoObject = [];
          $scope.notesObject = [];
          $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
          GetDashboardDataBySubject.get({
            classId : $scope.classId,
            subjectId : item.subjectId
          }, function(response) {
            if (response) {
              // Video data
              $scope.videoObject = response.videos;
              if ($scope.videoObject.length === 0) {
                $scope.showVideoDiv = true;
              }
              angular.forEach($scope.videoObject, function(value, key) {
                value.videoEmbedLink = value.videoEmbedLink.replace("watch?v=", "embed/");
              });
              // notes data
              $scope.notesObject = response.notes;
              if ($scope.notesObject.length === 0) {
                $scope.showNotesDiv = true;
              }
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          });
        }

        /* Code to display pdf files in website */

        if ($stateParams.pdfName != null && $stateParams.pdfName != undefined) {
          $scope.pdfName = $stateParams.pdfName;
        } else {
          $scope.pdfName = 'Relativity: The Special and General Theory by Albert Einstein';
        }
        if ($stateParams.pdfUrl != null && $stateParams.pdfUrl != undefined) {
          $scope.pdfUrl = $stateParams.pdfUrl;
        } else {
          $scope.pdfUrl = 'https://s3-us-west-2.amazonaws.com/s.cdpn.io/149125/relativity.pdf';
        }
        $scope.scroll = 0;
        $scope.loading = 'loading';

        $scope.getNavStyle = function(scroll) {
          if (scroll > 100)
            return 'pdf-controls fixed';
          else
            return 'pdf-controls';
        }

        $scope.onError = function(error) {
          console.log(error);
        }

        $scope.onLoad = function() {
          $scope.loading = '';
        }

        $scope.onProgress = function(progress) {
          console.log(progress);
        }

        /* pdf code ends here */

      } ]);

  // * Admin Controller

  homeModule.controller('AdminController', [
      '$stateParams',
      '$window',
      '$scope',
      '$filter',
      '$state',
      '$resource',
      '$location',
      '$rootScope',
      'CHALKNDUST',
      '$log',
      'GetUserDetailsService',
      'StudentProfileUpdateService',
      'ChangePasswordService',
      'userClassLookUpService',
      'GetSubjectsList',
      'createNewTopic',
      'GetTopicsList',
      'GetTopicDetailsService',
      'deleteTopicDetailsService',
      'updateTopicDetailsService',
      'GetCommentsList',
      'deleteCommentDetailsService',
      'GetStudentListService',
      'CandDModalService',
      'deleteStudentDetailsService',
      'filterFilter',
      'GetTopicRequestList',
      'approveTopicRequestService',
      'UpdateTopicImageService',
      'GetTopicImageService',
      'RegistrationService',
      'SaveQuestionDetailsService',
      'GetQuestionList',
      'updateQuestionDetailsService',
      'deleteQuestionService',
      'UpdateQuestionImageService',
      'ResetPasswordService',
      'saveVideoMasterData',
      'GetVideoContentList',
      'GetVideoDetailsService',
      'updateVideoDetailsService',
      'deleteVideoDetailsService',
      'createNotesContentService',
      'SaveNotesFileService',
      'UpdateNotesFileService',
      'GetNotesContentList',
      'GetNotesDetailsService',
      'updateNotesDetailsService',
      'deleteNotesDetailsService',
      'getTestTypeService',
      'saveScheduleTestMasterData',
      'GetScheduleTestContentList',
      'GetScheduleTestDetailsService',
      'updateScheduleDetailsService',
      'deleteScheduleTestDetailsService',
      function($stateParams, $window, $scope, $filter, $state, $resource, $location, $rootScope, CHALKNDUST, $log, GetUserDetailsService, StudentProfileUpdateService,
          ChangePasswordService, userClassLookUpService, GetSubjectsList, createNewTopic, GetTopicsList, GetTopicDetailsService, deleteTopicDetailsService,
          updateTopicDetailsService, GetCommentsList, deleteCommentDetailsService, GetStudentListService, CandDModalService, deleteStudentDetailsService, filterFilter,
          GetTopicRequestList, approveTopicRequestService, UpdateTopicImageService, GetTopicImageService, RegistrationService, SaveQuestionDetailsService, GetQuestionList,
          updateQuestionDetailsService, deleteQuestionService, UpdateQuestionImageService, ResetPasswordService, saveVideoMasterData, GetVideoContentList, GetVideoDetailsService,
          updateVideoDetailsService, deleteVideoDetailsService, createNotesContentService, SaveNotesFileService, UpdateNotesFileService, GetNotesContentList,
          GetNotesDetailsService, updateNotesDetailsService, deleteNotesDetailsService, getTestTypeService, saveScheduleTestMasterData, GetScheduleTestContentList,
          GetScheduleTestDetailsService, updateScheduleDetailsService, deleteScheduleTestDetailsService) {

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
        if ($window.localStorage.getItem(CHALKNDUST.TABNUMBER) != null) {
          $scope.tab = $window.localStorage.getItem(CHALKNDUST.TABNUMBER);
        } else {
          $scope.tab = 1;
        }
        $scope.setTab = function(newTab) {
          $scope.tab = newTab;
          $window.localStorage.setItem(CHALKNDUST.TABNUMBER, newTab);
          $scope.topicsList = [];
          $scope.commentsList = [];
        };

        $scope.isSet = function(tabNum) {
          return $scope.tab === tabNum;
        };

        $scope.securUuid = $window.localStorage.getItem(CHALKNDUST.SECURUUID);
        $scope.fullName = $window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
        if ($window.localStorage.getItem(CHALKNDUST.EDITFLAG) == "false") {
          $scope.editFlag = false;
        } else if ($window.localStorage.getItem(CHALKNDUST.EDITFLAG) == "true") {
          $scope.editFlag = true;
        }
        $scope.backToAdminDashBoard = function() {
          $state.go('chalkanddust.adminhome');
        };
        $scope.version = CHALKNDUST.VERSION;
        $scope.build = CHALKNDUST.BUILD;
        $scope.email = CHALKNDUST.EMAIL;
        $scope.releaseDate = CHALKNDUST.RELEASE_DATE;
        GetUserDetailsService.get({
          securUuid : $scope.securUuid
        }, function(response) {
          if (response) {
            $scope.userInfo = response;
            $scope.subjects = $scope.userInfo.subjects;

            $scope.academicInfo = $scope.userInfo.academicInfo;

            $scope.parentsInfo = $scope.userInfo.parentsInfo;
            $scope.studentSubjects = "";
            if ($scope.subjects != null) {
              for (var i = 0; i < $scope.subjects.length; i++) {
                if ($scope.studentSubjects == "") {
                  $scope.studentSubjects = $scope.subjects[i].subjectName;
                } else {
                  $scope.studentSubjects = $scope.studentSubjects + ", " + $scope.subjects[i].subjectName;
                }

              }
            }
          }
        }, function(error) {
          showAlert('danger', error.data.message);
        });

        this.updateProfile = function() {
          $scope.userInfo.academicInfo = $scope.academicInfo;
          $scope.userInfo.parentsInfo = $scope.parentsInfo;
          StudentProfileUpdateService.save({}, $scope.userInfo, function(response) {
            if (response) {
              console.log(response);
              $window.localStorage.setItem(CHALKNDUST.EDITFLAG, false);
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        $scope.editProfile = function() {
          $window.localStorage.setItem(CHALKNDUST.EDITFLAG, true);
          $state.reload();
        };

        this.changePassword = function() {
          ChangePasswordService.save({}, $scope.userInfo, function(response) {
            if (response) {
              console.log(response);
              $window.localStorage.setItem(CHALKNDUST.EDITFLAG, false);
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        userClassLookUpService.query(function(classes) {
          $scope.classes = classes;
          // console.log(classes);
        }, onRequestFailure);

        function onRequestFailure(error) {
          showAlert('danger', error.data.message);
        }
        ;
        $scope.topicDetails = [];
        $scope.commentDetails = [];
        $scope.studentList = [];
        $scope.topicRequestListDetails = [];
        $scope.videoDetails = [];
        $scope.notesDetails = [];
        $scope.scheduleTestDetails = [];

        var resetOptions = function() {
          $scope.topicDetails.subjectId = "";
          $scope.topicDetails.listsubjectId = "";
        };

        var resetOptionsComments = function() {
          $scope.commentDetails.subjectId = "";
          $scope.commentDetails.listsubjectId = "";
          $scope.commentDetails.topicId = "";
          $scope.commentDetails.listTopicId = "";
        }

        $scope.showSubjectsList = function(classId) {
          if (!classId) {
            return;
          }
          GetSubjectsList.query({
            classId : classId
          }, function(response) {
            resetOptions();
            resetOptionsComments();
            $scope.topicsList = [];
            $scope.commentsList = [];
            $scope.questionList = [];
            $scope.studentList = [];
            $scope.videoListDetails = [];
            $scope.NotesListDetails = [];
            $scope.ScheduleListDetails = [];
            console.log(classId);
            $scope.subjectsList = response;
          }, onRequestFailure);

        };

        $scope.showTopicDetails = function(classId, subjectId) {
          console.log(classId + "--------------------------" + classId);
          console.log(subjectId + "--------------------------" + subjectId);
          if (!classId) {
            resetOptions();
            if (!subjectId) {
              return;
            }
          }

          GetTopicsList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            $scope.commentsList = [];
            $scope.commentDetails.topicId = "";
            $scope.commentDetails.listTopicId = "";

            if (response) {
              $scope.topicsListDetails = response;
              $scope.totalItemstopicsList = $scope.topicsListDetails.length;
              $scope.currentPagetopicsList = 1;
              $scope.itemsPerPagetopicsList = 5;
              $scope.maxSizetopicsList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.topicsList = filterFilter($scope.topicsListDetails, newVal);
                $scope.totalItemstopicsList = $scope.topicsList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPagetopicsList = 1;
              }, true);
              $scope.getTopicsData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPagetopicsList);
                var itemsPerPagetopicsList = self.itemsPerPagetopicsList;
                var offset = (self.currentPagetopicsList - 1) * itemsPerPagetopicsList;
                $scope.topicsList = $scope.topicsListDetails.slice(offset, offset + itemsPerPagetopicsList)

              };
              $scope.getTopicsData();
            }
          }, onRequestFailure);

        };

        $scope.showTopicDetailsForCommentsPage = function(classId, subjectId) {
          console.log(classId + "--------------------------" + classId);
          console.log(subjectId + "--------------------------" + subjectId);
          if (!classId) {
            resetOptions();
            if (!subjectId) {
              return;
            }
          }

          GetTopicsList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            $scope.commentsList = [];
            $scope.commentDetails.topicId = "";
            $scope.commentDetails.listTopicId = "";
            if (response) {
              $scope.topicsList = response;
            }
          }, onRequestFailure);

        };

        $scope.showCommentDetails = function(classId, subjectId, topicId) {
          console.log("classId--------------------------" + classId);
          console.log("subjectId--------------------------" + subjectId);
          console.log("TopicId--------------------------" + topicId);
          if (!topicId) {
            resetOptions();
            if (!subjectId) {
              if (!topicId) {
                return;
              }
            }
          }

          GetCommentsList.query({
            classId : classId,
            subjectId : subjectId,
            topicId : topicId,
            isGuest : true
          }, function(response) {
            if (response) {
              $scope.commentsListDetails = response;
              $scope.totalItemscommentsList = $scope.commentsListDetails.length;
              $scope.currentPagecommentsList = 1;
              $scope.itemsPerPagecommentsList = 5;
              $scope.maxSizecommentsList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.commentsList = filterFilter($scope.commentsListDetails, newVal);
                $scope.totalItemscommentsList = $scope.commentsList.length;
                // $scope.maxSizecommentsList =
                // Math.ceil($scope.totalItemscommentsList /
                // $scope.itemsPerPagecommentsList);
                $scope.currentPagecommentsList = 1;
              }, true);

              $scope.getCommentsData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPagecommentsList);
                var itemsPerPagecommentsList = self.itemsPerPagecommentsList;
                var offset = (self.currentPagecommentsList - 1) * itemsPerPagecommentsList;
                $scope.commentsList = $scope.commentsListDetails.slice(offset, offset + itemsPerPagecommentsList)

              };
              $scope.getCommentsData();
            }
          }, onRequestFailure);

        };

        function showStudentList() {
          GetStudentListService.query({}, function(response) {
            if (response) {
              $scope.studentListDetails = response;
              $scope.totalItemsstudentList = $scope.studentListDetails.length;
              $scope.currentPageStudentList = 1;
              $scope.itemsPerPageStudentList = 8;
              $scope.maxSizeStudentList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.studentList = filterFilter($scope.studentListDetails, newVal);
                $scope.totalItemsstudentList = $scope.studentList.length;
                // $scope.maxSizeStudentList =
                // Math.ceil($scope.totalItemsstudentList /
                // $scope.itemsPerPageStudentList);
                $scope.currentPageStudentList = 1;
              }, true);

              $scope.getData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageStudentList);
                var itemsPerPageStudentList = self.itemsPerPageStudentList;
                var offset = (self.currentPageStudentList - 1) * itemsPerPageStudentList;
                $scope.studentList = $scope.studentListDetails.slice(offset, offset + itemsPerPageStudentList)

              };
              $scope.getData();
            }
          }, onRequestFailure);
        }
        ;

        /** *****Reset Student Login Password ******* */
        this.resetPassword = function(securUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to reset student login password?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            ResetPasswordService.get({
              securUuid : securUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Password has been reset successfully and new Password has been sent to the student via email.',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.message);
            })
          });
        };

        /** ***** Save Question******* */
        $scope.questionDetails = {};
        $scope.saveQuestion = function(fileData) {
          SaveQuestionDetailsService.save({}, $scope.questionDetails, function(response) {
            if (response) {
              var modalOptions = {
                header : 'Note',
                body : 'Question has been saved.',
                btn : 'OK'
              };

              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $log.info(result);
              });
              console.log(response);
              if (!angular.isUndefined(fileData)) {
                updateQuestionPhoto(fileData, response.questionSecuruuid);
              }
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /** ***** Show Question List ******* */
        $scope.showQuestionDetails = function(classId, subjectId, classes, subjectsList) {
          console.log("Fetching questions detailed list " + classId + "-" + classId + " & " + subjectId + "-" + subjectId);

          $scope.classes = classes;
          $scope.subjectsList = subjectsList;

          if (!classId) {
            resetOptions();
            if (!subjectId) {
              return;
            }
          }
          GetQuestionList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            if (response) {
              $scope.questionListDetails = response;
              $scope.totalItemsQuestionList = $scope.questionListDetails.length;
              $scope.currentPageQuestionList = 1;
              $scope.itemsPerPageQuestionList = 5;
              $scope.maxSizeQuestionList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.questionList = filterFilter($scope.questionListDetails, newVal);
                $scope.totalItemsQuestionList = $scope.questionList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPageQuestionList = 1;
              }, true);
              $scope.getQuestionData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageQuestionList);
                var itemsPerPageQuestionList = self.itemsPerPageQuestionList;
                var offset = (self.currentPageQuestionList - 1) * itemsPerPageQuestionList;
                $scope.questionList = $scope.questionListDetails.slice(offset, offset + itemsPerPageQuestionList)
              };
              $scope.getQuestionData();
            }
          }, onRequestFailure);

        };

        /** ***** Edit Question ******* */
        $scope.editQuestion = function(quesSecurUuid) {
          $scope.questionDetails = {};
          var found = $filter('filter')($scope.questionListDetails, {
            questionSecuruuid : quesSecurUuid
          }, true);
          if (found.length) {
            $scope.questionDetails = found[0];
            $scope.setTab(10);
          } else {
            console.log("question data not found");
            $state.go('chalkanddust.questionlist');
          }
        };

        /** ***** Update Question ******* */
        $scope.updateQuestion = function(fileData, classes, subjectsList) {
          console.log("updating question :" + $scope.questionDetails.questionSecuruuid);
          $scope.classes = classes;
          $scope.subjectsList = subjectsList;

          updateQuestionDetailsService.save({}, $scope.questionDetails, function(response) {
            if (response) {
              console.log(response);
              if (!angular.isUndefined(fileData)) {
                updateQuestionPhoto(fileData, $scope.questionDetails.questionSecuruuid);
              }
              var modalOptions = {
                header : 'Note',
                body : 'Question is updated successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }
          }, function(error) {
            showAlert('danger', error.message);
          });
        };

        /** ***** Update Question Photo ******* */
        var updateQuestionPhoto = function(fileData, securUuid) {
          var file = fileData;
          var formData = new FormData();
          formData.append('file', file);
          formData.append('name', file.name);
          formData.append('documentType', file.type);
          UpdateQuestionImageService.upload(formData, securUuid, function(response) {
            showAlert("success", "Question Image updated successfully.");
          }, onRequestFailure);
        };

        /** ***** Delete Question ******* */
        $scope.deleteQuestion = function(questionSecuruuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete the question?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteQuestionService.get({
              questionSecuruuid : questionSecuruuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Question deleted successfully',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.message);
            })
          });
        };

        $scope.isEmpty = function(obj) {
          for ( var prop in obj) {
            if (obj.hasOwnProperty(prop))
              return false;
          }
          return true;
        };

        $scope.topicDetailsToSave = {};
        this.createTopic = function(fileData) {
          angular.extend($scope.topicDetailsToSave, $scope.topicDetails);
          createNewTopic.save({}, $scope.topicDetailsToSave, function(response) {
            if (response) {
              console.log(response);

              if (!angular.isUndefined(fileData)) {
                updateTopicPhoto(fileData, response.securUuid);
              }
              var modalOptions = {
                header : 'Note',
                body : 'New Topic is Created',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });

            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        this.editTopic = function(securUuid) {
          GetTopicDetailsService.get({
            securUuid : securUuid
          }, function(response) {
            if (response) {
              $scope.topicDetails = response;
              console.log($scope.topicDetails);
              if ($scope.topicDetails.topicImage != null) {
                GetTopicImageService.get({
                  securUuid : $scope.topicDetails.securUuid
                }, function(result) {
                  $scope.topicImageLink = result.topicImageLink;
                }, function(error) {
                  showAlert('danger', error.data.message);
                });
              }
              $scope.setTab(4);
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          })
        };

        // delete topic
        this.deleteTopic = function(securUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Deleting this topic will also delete associated comments.Do you want to continue?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteTopicDetailsService.get({
              securUuid : securUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Topic deleted successfully',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })
          });
        };
        // update Topic

        this.updateTopic = function(fileData) {
          updateTopicDetailsService.save({}, $scope.topicDetails, function(response) {
            if (response) {
              console.log(response);
              if (!angular.isUndefined(fileData)) {
                updateTopicPhoto(fileData, $scope.topicDetails.securUuid);
              }

              var modalOptions = {
                header : 'Note',
                body : 'Your Topic is updated successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        // delete comment
        this.deleteComment = function(commentUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete this Comment ?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteCommentDetailsService.get({
              commentUuid : commentUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Comment deleted successfully',
                  btn : 'OK'
                };
                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })

          });
        };

        /** **** delete student ******* */
        this.deleteStudent = function(securUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to delete this Student ?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteStudentDetailsService.get({
              securUuid : securUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Student deleted successfully',
                  btn : 'OK'
                };
                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })

          });
        };

        // show topic request list
        function showTopicRequestList() {

          GetTopicRequestList.query({}, function(response) {

            if (response) {
              $scope.topicRequestListDetails = response;
              $scope.totalItemsTopicRequestList = $scope.topicRequestListDetails.length;
              $scope.currentPageTopicRequestList = 1;
              $scope.itemsPerPageTopicRequestList = 5;
              $scope.maxSizeTopicRequestList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.topicRequestList = filterFilter($scope.topicRequestListDetails, newVal);
                $scope.totalItemsTopicRequestList = $scope.topicRequestList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPageTopicRequestList = 1;
              }, true);
              $scope.getTopicRequestData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageTopicRequestList);
                var itemsPerPageTopicRequestList = self.itemsPerPageTopicRequestList;
                var offset = (self.currentPageTopicRequestList - 1) * itemsPerPageTopicRequestList;
                $scope.topicRequestList = $scope.topicRequestListDetails.slice(offset, offset + itemsPerPageTopicRequestList)

              };
              $scope.getTopicRequestData();
            }
          }, onRequestFailure);
        }
        ;

        // approve topic request
        this.approveTopicRequest = function(requestSecurUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Do you want to approve this Topic Request?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            approveTopicRequestService.get({
              requestSecurUuid : requestSecurUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Topic request approved successfully',
                  btn : 'OK'
                };
                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })

          });
        };

        showStudentList();
        showTopicRequestList();

        this.goBackToAdminHomePage = function() {
          $state.go('chalkanddust.adminhome');
        };

        $scope.isUndefined = function(thing) {
          return (typeof thing === "undefined");
        };

        /**
         * Function to upload Topic photo
         */
        var updateTopicPhoto = function(fileData, securUuid) {
          var file = fileData;
          var formData = new FormData();
          formData.append('file', file);
          formData.append('name', file.name);
          formData.append('documentType', file.type);
          UpdateTopicImageService.upload(formData, securUuid, function(response) {
            showAlert("success", "Topic Image updated successfully.");
          }, onRequestFailure);
        };

        /*
         * 
         * 
         * Registration code will start from here.
         */

        $scope.userDetails = {};

        $scope.inputType = 'password';
        $scope.hideShowPassword = function() {
          if ($scope.inputType === 'password') {
            $scope.inputType = 'text';
          } else {
            $scope.inputType = 'password';
          }
        };

        this.register = function() {

          RegistrationService.save({}, $scope.userDetails, function(response) {
            if (response) {
              var modalOptions = {
                header : 'Note',
                body : 'New user has been created and an email has been sent to their registered email address.',
                btn : 'OK'
              };

              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $log.info(result);
              });
              console.log(response);
              $state.reload();
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /*
         * 
         * 
         * Registration Code ends here
         */

        /*
         * 
         * Video Master Code starts here
         * 
         */

        /** ******** Create Video data************ */
        $scope.videoDetailsToSave = {};
        this.createVideoData = function() {
          angular.extend($scope.videoDetailsToSave, $scope.videoDetails);
          saveVideoMasterData.save({}, $scope.videoDetailsToSave, function(response) {
            if (response) {
              console.log(response);
              var modalOptions = {
                header : 'Note',
                body : 'Your Video data is saved successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /** ***** Show video List ******* */
        $scope.showVideoDetails = function(classId, subjectId, classes, subjectsList) {
          console.log("Fetching video detailed list " + classId + "-" + classId + " & " + subjectId + "-" + subjectId);

          $scope.classes = classes;
          $scope.subjectsList = subjectsList;

          if (!classId) {
            resetOptions();
            if (!subjectId) {
              return;
            }
          }
          GetVideoContentList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            if (response) {
              $scope.videoListDetails = response;
              $scope.totalItemsVideoList = $scope.videoListDetails.length;
              $scope.currentPageVideoList = 1;
              $scope.itemsPerPageVideoList = 5;
              $scope.maxSizeVideoList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.videoList = filterFilter($scope.videoListDetails, newVal);
                $scope.totalItemsVideoList = $scope.videoList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPageVideoList = 1;
              }, true);
              $scope.getVideoContentData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageVideoList);
                var itemsPerPageVideoList = self.itemsPerPageVideoList;
                var offset = (self.currentPageVideoList - 1) * itemsPerPageVideoList;
                $scope.videoList = $scope.videoListDetails.slice(offset, offset + itemsPerPageVideoList)
              };
              $scope.getVideoContentData();
            }
          }, onRequestFailure);

        };

        /** ******Edit video data*********** */

        $scope.editVideoContent = function(videoUuid) {
          GetVideoDetailsService.get({
            videoUuid : videoUuid
          }, function(response) {
            if (response) {
              $scope.videoDetails = response;
              console.log($scope.videoDetails);
            }
            $scope.setTab(12);
          }, function(error) {
            showAlert('danger', error.data.message);
          })
        };

        /** ***********Update Video data*************** */
        this.updatevideoData = function() {
          updateVideoDetailsService.save({}, $scope.videoDetails, function(response) {
            if (response) {
              console.log(response);
              var modalOptions = {
                header : 'Note',
                body : 'Your Video data is updated successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /** ***********Delete Video data*************** */
        $scope.deleteVideoContent = function(videoUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Deleting Video data,Do you want to continue?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteVideoDetailsService.get({
              videoUuid : videoUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Video data deleted successfully',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })
          });
        };

        /*
         * 
         * Video Master Code ends here
         * 
         */

        /*
         * 
         * Notes Master Code starts here
         * 
         */

        /** ***************Save Notes********************** */
        $scope.notesDetailsToSave = {};
        this.createNotesData = function(fileData) {

          angular.extend($scope.notesDetailsToSave, $scope.notesDetails);
          var fileName = fileData.name;
          var i = fileName.lastIndexOf('.');
          var extension = "";
          if (i > 0) {
            extension = fileName.substring(i + 1);
          }
          if (extension != "pdf" && extension != "Pdf" && extension != "PDF") {
            var modalOptions = {
              header : 'Note',
              body : 'Please upload notes in pdf format only',
              btn : 'OK'
            };
            CandDModalService.showModal({}, modalOptions).then(function(result) {
              return false;
            });

          } else {
            $scope.notesDetailsToSave.notesFileName = fileName;
            createNotesContentService.save({}, $scope.notesDetailsToSave, function(response) {
              if (response) {
                console.log(response);

                if (!angular.isUndefined(fileData)) {
                  saveNotesDataFile(fileData, response.notesUuid);
                }
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            });
          }
        };

        /**
         * Function to upload Notes File
         */
        var saveNotesDataFile = function(fileData, notesUuid) {
          var file = fileData;
          var formData = new FormData();
          formData.append('file', file);
          formData.append('name', file.name);
          formData.append('documentType', file.type);
          SaveNotesFileService.upload(formData, notesUuid, function(response) {
            var modalOptions = {
              header : 'Note',
              body : 'Notes saved successfully',
              btn : 'OK'
            };
            CandDModalService.showModal({}, modalOptions).then(function(result) {
              $state.reload();
            });
          }, onRequestFailure);
        };

        /** ***** Show Notes List ******* */
        $scope.showNotesDetails = function(classId, subjectId, classes, subjectsList) {
          console.log("Fetching notes detailed list " + classId + "-" + classId + " & " + subjectId + "-" + subjectId);

          $scope.classes = classes;
          $scope.subjectsList = subjectsList;

          if (!classId) {
            notesDetails.listsubjectId = "";
            $scope.NotesListDetails = null;
            $scope.NotesList = null;
            if (!subjectId) {
              return;
            }
          }
          GetNotesContentList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            if (response) {
              $scope.NotesListDetails = response;
              $scope.totalItemsNotesList = $scope.NotesListDetails.length;
              $scope.currentPageNotesList = 1;
              $scope.itemsPerPageNotesList = 5;
              $scope.maxSizeNotesList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.NotesList = filterFilter($scope.NotesListDetails, newVal);
                $scope.totalItemsNotesList = $scope.NotesList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPageNotesList = 1;
              }, true);
              $scope.getNotesContentData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageNotesList);
                var itemsPerPageNotesList = self.itemsPerPageNotesList;
                var offset = (self.currentPageNotesList - 1) * itemsPerPageNotesList;
                $scope.NotesList = $scope.NotesListDetails.slice(offset, offset + itemsPerPageNotesList)
              };
              $scope.getNotesContentData();
            }
          }, onRequestFailure);

        };

        /** ******Edit notes data*********** */

        $scope.editNotesContent = function(notesUuid) {
          GetNotesDetailsService.get({
            notesUuid : notesUuid
          }, function(response) {
            if (response) {
              $scope.notesDetails = response;
              console.log($scope.notesDetails);
            }
            $scope.setTab(14);
          }, function(error) {
            showAlert('danger', error.data.message);
          })
        };

        /** ***********Update Notes data*************** */
        $scope.notesDetailsToUpdate = {};
        this.updateNotesData = function(fileData) {
          angular.extend($scope.notesDetailsToUpdate, $scope.notesDetails);
          var fileName = fileData.name;
          var i = fileName.lastIndexOf('.');
          var extension = "";
          if (i > 0) {
            extension = fileName.substring(i + 1);
          }
          if (extension != "pdf" && extension != "Pdf" && extension != "PDF") {
            var modalOptions = {
              header : 'Note',
              body : 'Please upload notes in pdf format only',
              btn : 'OK'
            };
            CandDModalService.showModal({}, modalOptions).then(function(result) {
              return false;
            });

          } else {
            $scope.notesDetailsToUpdate.notesFileName = fileName;
            updateNotesDetailsService.save({}, $scope.notesDetailsToUpdate, function(response) {
              if (response) {
                console.log(response);
                if (!angular.isUndefined(fileData)) {
                  updateNotesDataFile(fileData, response.notesUuid);
                }
              }

            }, function(error) {
              showAlert('danger', error.data.message);
            });
          }
        };

        /**
         * Function to update Notes File
         */
        var updateNotesDataFile = function(fileData, notesUuid) {
          var file = fileData;
          var formData = new FormData();
          formData.append('file', file);
          formData.append('name', file.name);
          formData.append('documentType', file.type);
          UpdateNotesFileService.upload(formData, notesUuid, function(response) {
            var modalOptions = {
              header : 'Note',
              body : 'Notes updated successfully',
              btn : 'OK'
            };
            CandDModalService.showModal({}, modalOptions).then(function(result) {
              $state.reload();
            });
          }, onRequestFailure);
        };

        /** ***********Delete Notes data*************** */
        $scope.deleteNotesContent = function(notesUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Deleting Notes data,Do you want to continue?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteNotesDetailsService.get({
              notesUuid : notesUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Notes data deleted successfully',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })
          });
        };

        /*
         * 
         * Notes Master Code ends here
         * 
         */

        /*
         * 
         * Get test type details
         * 
         * 
         */
        getTestTypeService.query(function(testType) {
          $scope.testTypeList = testType;
          // console.log(classes);
        }, onRequestFailure);

        /*
         * 
         * ends here
         * 
         */

        function testSchedularDetails(scheduleTestData) {
          var testDate = scheduleTestData.testDate;
          var testTime = scheduleTestData.testTime;
          if (!testDate.contains("-")) {
            showAlert('danger', "Please input a valid date");
            return false;
          }

          var testTimeSplit = testDate.split("-");
          var year = testTimeSplit[0];
          var month = testTimeSplit[1];
          var day = testTimeSplit[2];

          if (year.length != 4) {
            showAlert('danger', "Please input a valid year in date");
            return false;
          }
          if (month.length != 2) {
            showAlert('danger', "Please input valid month in date");
            return false;
          }
          if (day.length != 2) {
            showAlert('danger', "Please input valid day in date");
            return false;
          }
          if (year.length == 4) {
            if (Number(year) > 3000) {
              showAlert('danger', "Please input a valid year in date");
              return false;
            }
          }
          year = Number(testTimeSplit[0]);
          month = Number(testTimeSplit[1]);
          day = Number(testTimeSplit[2]);

          if (month > 12) {
            showAlert('danger', "Please input valid month in date");
            return false;
          }
          if (year % 4 == 0) {
            if (month == 1 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 2 && day > 29) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 3 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 4 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 5 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 6 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 7 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 8 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 9 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 10 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 11 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 12 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            }
          } else {
            if (month == 1 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 2 && day > 28) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 3 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 4 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 5 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 6 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 7 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 8 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 9 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 10 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 11 && day > 30) {
              showAlert('danger', "Please input valid day in date");
              return false;
            } else if (month == 12 && day > 31) {
              showAlert('danger', "Please input valid day in date");
              return false;
            }
          }
          var currentDate = new Date();
          var inputDate = new Date(testDate);
          if (inputDate.getTime() < currentDate.getTime()) {
            showAlert('danger', "Please input valid date greater than currentdate");
            return false;
          }

          if (!testTime.contains(":")) {
            showAlert('danger', "Please input a valid time");
            return false;
          }

          var testTimeDetail = testTime.split(":");
          var hour = Number(testTimeDetail[0]);
          var minutes = Number(testTimeDetail[1]);
          var seconds = Number(testTimeDetail[2]);

          if (hour < 0 || hour > 23) {
            showAlert('danger', "Please input valid hours");
            return false;
          }
          if (minutes < 0 || minutes > 59) {
            showAlert('danger', "Please input valid minutes");
            return false;
          }
          if (seconds < 0 || seconds > 59) {
            showAlert('danger', "Please input valid seconds");
            return false;
          } else {
            return true;
          }
        }

        String.prototype.contains = function(it) {
          return this.indexOf(it) != -1;
        }

        /** ******Schedule Test Methods starts from here*********** */
        $scope.scheduleTestDetailsToSave = {};
        this.createScheduleTestData = function(scheduleTestDetails) {
          if (!testSchedularDetails($scope.scheduleTestDetails)) {
            return false;
          }
          var schdeuleTestDetails = $scope.scheduleTestDetails;
          angular.extend($scope.scheduleTestDetailsToSave, $scope.scheduleTestDetails);
          saveScheduleTestMasterData.save({}, $scope.scheduleTestDetailsToSave, function(response) {
            if (response) {
              console.log(response);
              var modalOptions = {
                header : 'Note',
                body : 'Test has been scheduled successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        $scope.showScheduleTestDetails = function(classId, subjectId, classes, subjectsList) {
          console.log("Fetching Schedule test detailed list " + classId + "-" + classId + " & " + subjectId + "-" + subjectId);

          $scope.classes = classes;
          $scope.subjectsList = subjectsList;

          if (!classId) {
            resetOptions();
            if (!subjectId) {
              return;
            }
          }
          GetScheduleTestContentList.query({
            classId : classId,
            subjectId : subjectId
          }, function(response) {
            if (response) {
              $scope.ScheduleListDetails = response;
              $scope.totalItemsScheduleList = $scope.ScheduleListDetails.length;
              $scope.currentPageScheduleList = 1;
              $scope.itemsPerPageScheduleList = 5;
              $scope.maxSizeScheduleList = 5;

              $scope.$watch('search', function(newVal, oldVal) {
                $scope.ScheduleList = filterFilter($scope.ScheduleListDetails, newVal);
                $scope.totalItemsScheduleList = $scope.ScheduleList.length;
                // $scope.maxSizetopicsList =
                // Math.ceil($scope.totalItemstopicsList /
                // $scope.itemsPerPagetopicsList);
                $scope.currentPageScheduleList = 1;
              }, true);
              $scope.getScheduleContentData = function() {
                // keep a reference to the current instance "this" as the
                // context is changing
                var self = this;
                console.log(self.currentPageScheduleList);
                var itemsPerPageScheduleList = self.itemsPerPageScheduleList;
                var offset = (self.currentPageScheduleList - 1) * itemsPerPageScheduleList;
                $scope.ScheduleList = $scope.ScheduleListDetails.slice(offset, offset + itemsPerPageScheduleList)
              };
              $scope.getScheduleContentData();
            }
          }, onRequestFailure);

        };

        /** ******Edit Schedule data*********** */

        $scope.editScheduleTestContent = function(scheduleTestUuid) {
          GetScheduleTestDetailsService.get({
            scheduleTestUuid : scheduleTestUuid
          }, function(response) {
            if (response) {
              $scope.scheduleTestDetails = response;
              console.log($scope.scheduleTestDetails);
            }
            $scope.setTab(16);
          }, function(error) {
            showAlert('danger', error.data.message);
          })
        };

        /** ***********Update Schedule data*************** */
        this.updateScheduleTestData = function() {
          if (!testSchedularDetails($scope.scheduleTestDetails)) {
            return false;
          }
          updateScheduleDetailsService.save({}, $scope.scheduleTestDetails, function(response) {
            if (response) {
              console.log(response);
              var modalOptions = {
                header : 'Note',
                body : 'Schedule Test details is updated successfully',
                btn : 'OK'
              };
              CandDModalService.showModal({}, modalOptions).then(function(result) {
                $state.reload();
              });
            }

          }, function(error) {
            showAlert('danger', error.data.message);
          });
        };

        /** ***********Delete Schedule data*************** */
        $scope.deleteScheduleTestContent = function(scheduleTestUuid) {
          var modalOptionsConfirm = {
            header : 'Note',
            body : 'Deleting Schedule Test data,Do you want to continue?',
            btn : 'OK'
          };
          CandDModalService.showConfirm({}, modalOptionsConfirm).then(function(result) {
            deleteScheduleTestDetailsService.remove({
              scheduleTestUuid : scheduleTestUuid
            }, function(response) {
              if (response) {
                console.log(response);
                var modalOptions = {
                  header : 'Note',
                  body : 'Schedule test data deleted successfully',
                  btn : 'OK'
                };

                CandDModalService.showModal({}, modalOptions).then(function(result) {
                  $log.info(result);
                });
                $state.reload();
              }
            }, function(error) {
              showAlert('danger', error.data.message);
            })
          });
        };

        /** ******Schedule Test Methods ends from here*********** */
        var Id = $stateParams.id;

        // Show Student's Details

        if (!Id) {
          return;
        }
        GetUserDetailsService.get({
          securUuid : Id
        }, function(response) {
          if (response) {
            $scope.userInfoToShow = response;
            $scope.subjectsToShow = $scope.userInfoToShow.subjects;

            $scope.academicInfoToShow = $scope.userInfoToShow.academicInfo;

            $scope.parentsInfoToShow = $scope.userInfoToShow.parentsInfo;
            $scope.studentSubjectsToShow = "";
            if ($scope.subjectsToShow != null) {
              for (var i = 0; i < $scope.subjectsToShow.length; i++) {
                if ($scope.studentSubjectsToShow == "") {
                  $scope.studentSubjectsToShow = $scope.subjectsToShow[i].subjectName;
                } else {
                  $scope.studentSubjectsToShow = $scope.studentSubjectsToShow + ", " + $scope.subjectsToShow[i].subjectName;
                }

              }
            }

          }
        }, function(error) {
          showAlert('danger', error.data.message);
        });

      } ]);

  homeModule.controller('NotificationController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', 'CandDModalService', '$stateParams','$interval','GetNotificationList',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, CandDModalService, $stateParams, $interval,GetNotificationList) {
       
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
        $scope.securUuid = $window.localStorage.getItem(CHALKNDUST.SECURUUID);
        $scope.fullName = $window.localStorage.getItem(CHALKNDUST.USERFULLNAME);
        $scope.classId = $window.localStorage.getItem(CHALKNDUST.CLASSID);
        $interval(function(){
        
        GetNotificationList.query({
          classId : $scope.classId,
          studentId : $scope.securUuid
        }, function(response) {
          if (response) {
            $rootScope.notificationList=response;
          }
        }, function(error) {
          showAlert('danger', error.data.message);
        });
        },120000);
        
      } ]);
});
