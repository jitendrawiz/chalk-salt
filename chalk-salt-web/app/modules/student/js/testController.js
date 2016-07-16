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

  testModule.controller('TestDetailController', [
      '$window',
      '$scope',
      '$state',
      '$resource',
      '$http',
      '$location',
      '$rootScope',
      'CHALKNDUST',
      '$stateParams',
      'GetUserPhotoService',
      'GetTestQuestionsListService',
      'helperService',
      'saveAnswersService',
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService, GetTestQuestionsListService, helperService,
          saveAnswersService) {

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
              question.Answered = option.id;
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
              'questionId' : q.id,
              'answered' : q.Answered
            });
          });

          // Post your data to the server here. answers contains the questionId
          // and the users' answer.
          // $http.post('api/Quiz/Submit', answers).success(function (data,
          // status) {
          // alert(data);
          // });
          $scope.answers = answers;
          var answerObject = {
            studentId : $scope.securUuid,
            classId : $scope.classId,
            subjectId : $scope.subjectId,
            answers : $scope.answers,
            testTypeId : $scope.type
          };

          saveAnswersService.save({}, answerObject, function(response) {
            if (response) {
              $scope.mode = 'result';
            }
          }, function(error) {
            showAlert('danger', error.data.message);
          });

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

        $scope.closeWindow = function() {
          resetData();
          $state.go('chalkanddust.profile');
        }

        function resetData() {
          $window.localStorage.removeItem(CHALKNDUST.QUESTIONDATA);
          $window.localStorage.removeItem(CHALKNDUST.TESTTYPE);
          clearTimeout(timeout);
          $window.localStorage.removeItem(CHALKNDUST.TIMERTIME);
        }

        $scope.backToDashboard = function() {
          resetData();
          $state.go('chalkanddust.profile');
        }

        $scope.GoToHomePage = function() {
          resetData();
          $state.go('chalkanddust.home');

        }

        var localtime = $window.localStorage.getItem(CHALKNDUST.TIMERTIME);
        if (localtime != null && localtime != undefined) {
          CreateTimer("timer", localtime);
        } else {
          if ($scope.type == 'cf92f98c-4684-11e6-beb8-9e71128cae77') {
            CreateTimer("timer", 1200);
          } else if ($scope.type == 'cf92fe46-4684-11e6-beb8-9e71128cae77') {
            CreateTimer("timer", 2400);
          } else if ($scope.type == 'cf92fc16-4684-11e6-beb8-9e71128cae77') {
            CreateTimer("timer", 3600);
          } else {
            CreateTimer("timer", 1200);
          }
        }

        var Timer;
        var TotalSeconds;
        var timeout;
        function CreateTimer(TimerID, Time) {
          Timer = document.getElementById(TimerID);
          TotalSeconds = Time;

          UpdateTimer();
          timeout = setTimeout(Tick, 1000);
          $rootScope.timeoutvariable = timeout;
        }

        function Tick() {
          if (TotalSeconds <= 0) {
            alert("Your time's up, thus submitting the exam");
            $window.localStorage.removeItem(CHALKNDUST.TIMERTIME);
            $scope.onSubmit();
            return;

          }
          TotalSeconds -= 1;
          $window.localStorage.setItem(CHALKNDUST.TIMERTIME, TotalSeconds);
          UpdateTimer()
          timeout = setTimeout(Tick, 1000);
          $rootScope.timeoutvariable = timeout;
        }
        function UpdateTimer() {

          var Seconds = TotalSeconds;
          var Days = Math.floor(Seconds / 86400);
          Seconds -= Days * 86400;
          var Hours = Math.floor(Seconds / 3600);
          Seconds -= Hours * (3600);
          var Minutes = Math.floor(Seconds / 60);
          Seconds -= Minutes * (60);
          var TimeStr = ((Days > 0) ? Days + " days " : "") + LeadingZero(Hours) + ":" + LeadingZero(Minutes) + ":" + LeadingZero(Seconds)
          Timer.innerHTML = TimeStr;
        }

        function LeadingZero(Time) {
          return (Time < 10) ? "0" + Time : +Time;
        }

      } ]);
});