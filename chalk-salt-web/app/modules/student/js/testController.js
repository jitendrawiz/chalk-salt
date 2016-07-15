'use strict';

define([ 'angular', '../../CandDModal/js/CandDModalService' ], function(angular) {

  var testModule = angular.module('Student.testcontroller', [ 'System.configuration', 'CandDModal' ]);

  testModule.controller('TestController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', '$stateParams', 'GetUserPhotoService',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService) {

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

        $window.localStorage.setItem(CHALKNDUST.TESTTYPE, $stateParams.type);

        $scope.goBackToDashBoard = function() {
          $state.go('chalkanddust.profile');
        };

        $scope.startTest = function() {
          $state.go('chalkanddust.testscreen');
        };

        GetUserPhotoService.get({
          securUuid : $scope.securUuid
        }, function(result) {
          $scope.userProfilePhoto = result.photolink;
        }, function(error) {
          showAlert('danger', error.data.message);
        });
      } ]);

  testModule.controller('TestDetailController', [ '$window', '$scope', '$state', '$resource', '$http', '$location', '$rootScope', 'CHALKNDUST', '$stateParams',
      'GetUserPhotoService', 'GetTestQuestionsListService', 'helperService',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService, GetTestQuestionsListService, helperService) {

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
        $scope.subjectId = $window.localStorage.getItem(CHALKNDUST.SUBJECTID);
        $scope.type = $window.localStorage.getItem(CHALKNDUST.TESTTYPE);

        /* Get User Profile Photo */
        GetUserPhotoService.get({
          securUuid : $scope.securUuid
        }, function(result) {
          $scope.userProfilePhoto = result.photolink;
        }, function(error) {
          showAlert('danger', error.data.message);
        });

        // $scope.quizName = './modules/student/js/testing.js';
        $scope.defaultConfig = {
          'allowBack' : true,
          'allowReview' : true,
          'autoMove' : true, // if true, it will move to next question
                              // automatically when answered.
          'duration' : 0, // indicates the time in which quiz needs to be
                          // completed. post that, quiz will be automatically
                          // submitted. 0 means unlimited.
          'pageSize' : 1,
          'requiredAll' : true, // indicates if you must answer all the
                                // questions before submitting.
          'richText' : false,
          'shuffleQuestions' : false,
          'shuffleOptions' : false,
          'showClock' : false,
          'showPager' : true,
          'theme' : 'none'
        }

        $scope.goTo = function(index) {
          if (index > 0 && index <= $scope.totalItems) {
            $scope.currentPage = index;
            $scope.mode = 'quiz';
          }
        }

        $scope.onSelect = function(question, option) {
         
            question.options.forEach(function(element, index, array) {
              if (element.id != option.id) {
                element.Selected = false;
                question.Answered = element.id;
              }
            });
         
          $window.localStorage.setItem(CHALKNDUST.QUESTIONDATA, JSON.stringify($scope.questions));
          if ($scope.config.autoMove == true && $scope.currentPage < $scope.totalItems)
            $scope.currentPage++;
        }

        $scope.onSubmit = function() {
          var answers = [];
          $scope.questions.forEach(function(q, index) {
            answers.push({
              //'QuizId' : $scope.quiz.id,
              'QuestionId' : q.id,
              'Answered' : q.Answered
            });
          });

          var isUnanswered = false;
          answers.forEach(function(q, index) {
            if (q.Answered == undefined) {
              isUnanswered = true;
            } else {
              isUnanswered = false;
            }
          });

          // Post your data to the server here. answers contains the questionId
          // and the users' answer.
          // $http.post('api/Quiz/Submit', answers).success(function (data,
          // status) {
          // alert(data);
          // });
          console.log($scope.questions);
          console.log(isUnanswered);
          if (!isUnanswered) {
            $scope.mode = 'result';
          } else {
            alert("Please answer all the questions")
          }
        }

        $scope.pageCount = function() {
          return Math.ceil($scope.questions.length / $scope.itemsPerPage);
        };

        $scope.loadQuiz = function() {

          GetTestQuestionsListService.query({
            classId : $scope.classId,
            subjectId : $scope.subjectId,
            type : $scope.type
          }, function(res) {
            var questiondata = window.localStorage.getItem(CHALKNDUST.QUESTIONDATA);
            $scope.config = $scope.defaultConfig;
            if (questiondata != null && questiondata != undefined) {
              $scope.questions = [];
              var obj = JSON.parse(questiondata);
              $scope.questions = obj;
            } else {
              $window.localStorage.setItem(CHALKNDUST.QUESTIONDATA, JSON.stringify(res));
              $scope.questions = $scope.config.shuffleQuestions ? helperService.shuffle(res) : res;
            }
            $scope.totalItems = $scope.questions.length;
            $scope.itemsPerPage = $scope.config.pageSize;
            $scope.currentPage = 1;
            $scope.mode = 'quiz';
            if ($scope.config.shuffleOptions)
              $scope.shuffleOptions();

            $scope.$watch('currentPage + itemsPerPage', function() {
              var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin + $scope.itemsPerPage;

              $scope.filteredQuestions = $scope.questions.slice(begin, end);
            });
          }, function(error) {
            showAlert('danger', error.data.message);
          });
        }

        $scope.shuffleOptions = function() {
          $scope.questions.forEach(function(question) {
            question.options = helperService.shuffle(question.options);
          });
        }

        $scope.loadQuiz();

        $scope.isAnswered = function(index) {
          var answered = 'Not Answered';
          $scope.questions[index].options.forEach(function(element, index, array) {
            if (element.Selected == true) {
              answered = 'Answered';
              return false;
            }
          });
          return answered;
        };

        $scope.isCorrect = function(question) {
          var result = 'correct';
          question.options.forEach(function(option, index, array) {
            if (helperService.toBool(option.Selected) != option.isAnswer) {
              result = 'wrong';
              return false;
            }
          });
          return result;
        };

      } ]);
});