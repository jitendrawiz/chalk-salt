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
      function($window, $scope, $state, $resource, $http, $location, $rootScope, CHALKNDUST, $stateParams, GetUserPhotoService, GetTestQuestionsListService,helperService) {

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
        /* Get Test Questions List */
        /* GetTestQuestionsListService.get({
           classId : $scope.classId,
           subjectId : $scope.subjectId,
           type : $scope.type
         }, function(result) {
           $scope.questionListDetails = result;
           $scope.buttonList = buttonList;
           $scope.item = $scope.questionListDetails[Object.keys($scope.questionListDetails)[0]];
           
           $scope.btnList=getButtonData($scope.questionListDetails);
           // $scope.totalItems = $scope.questionListDetails.length;
           // $scope.currentPage = 1;
           // $scope.itemsPerPage = 1;
           // $scope.maxSize = $scope.totalItems;
           //
           // $scope.setPage = function(pageNo) {
           // $scope.currentPage = pageNo;
           // };
           //
           // $scope.pageCount = function() {
           // return Math.ceil($scope.questionListDetails.length /
           // $scope.itemsPerPage);
           // };
           //
           // $scope.$watch('currentPage + itemsPerPage', function() {
           // var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end =
           // begin + $scope.itemsPerPage;
           // $scope.questionList = $scope.questionListDetails.slice(begin, end);
           // });

         }, function(error) {
           showAlert('danger', error.data.message);
         });

         $scope.submitAnswer = function(index, selectedOption) {
           $scope.item=$scope.questionListDetails[index];
           $scope.item.selectedOption=selectedOption;
           console.log($scope.item);
           console.log($scope.item.selectedOption);
         };
         
         
         $scope.changeQuestion=function(index){
           $scope.item=$scope.questionListDetails[index];
           $scope.SelectedOption={};
         }*/
        /*Get count of questions in list*/

        /*    var count = 1;
            var buttons = {};
            var buttonList = [];
            function getButtonData(obj){
              for ( var property in obj) {
                if (Object.prototype.hasOwnProperty.call(obj, property)) {
                  if (property != '$promise' && property != "$resolved"){
                    buttons.index=count;
                    buttons.id=property;
                    buttonList.push(buttons);
                    buttons={};
                    
                    count++;
                  }
                }
              }
              console.log(buttonList);
              return buttonList;
             
            }*/

        $scope.quizName = './modules/student/js/testing.js';

        //Note: Only those configs are functional which is documented at: http://www.codeproject.com/Articles/860024/Quiz-Application-in-AngularJs
        // Others are work in progress.
        $scope.defaultConfig = {
          'allowBack' : true,
          'allowReview' : true,
          'autoMove' : true, // if true, it will move to next question automatically when answered.
          'duration' : 0, // indicates the time in which quiz needs to be completed. post that, quiz will be automatically submitted. 0 means unlimited.
          'pageSize' : 1,
          'requiredAll' : true, // indicates if you must answer all the questions before submitting.
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
          if (question.QuestionTypeId == 1) {
            question.Options.forEach(function(element, index, array) {
              if (element.Id != option.Id) {
                element.Selected = false;
                question.Answered = element.Id;
              }
            });
          }
          $window.localStorage.setItem(CHALKNDUST.QUESTIONDATA,JSON.stringify($scope.questions));
          if ($scope.config.autoMove == true && $scope.currentPage < $scope.totalItems)
            $scope.currentPage++;
        }

        $scope.onSubmit = function() {
          var answers = [];
          $scope.questions.forEach(function(q, index) {
            answers.push({
              'QuizId' : $scope.quiz.Id,
              'QuestionId' : q.Id,
              'Answered' : q.Answered
            });
          });
          
          var isUnanswered=false;
          answers.forEach(function(q, index) {
            if(q.Answered == undefined){
              isUnanswered=true;
            }else{
              isUnanswered=false;
            }
          });
         
          // Post your data to the server here. answers contains the questionId and the users' answer.
          //$http.post('api/Quiz/Submit', answers).success(function (data, status) {
          //    alert(data);
          //});
          console.log($scope.questions);
          console.log(isUnanswered);
          if(!isUnanswered){
          $scope.mode = 'result';
          }else{
            alert("Please answer all the questions")
          }
        }
        
        $scope.pageCount = function() {
          return Math.ceil($scope.questions.length / $scope.itemsPerPage);
        };

        //If you wish, you may create a separate factory or service to call loadQuiz. To keep things simple, i have kept it within controller.
        $scope.loadQuiz = function(file) {
          $http.get(file).then(function(res) {
            var quizdata=window.localStorage.getItem(CHALKNDUST.QUIZDATA);
            var questiondata=window.localStorage.getItem(CHALKNDUST.QUESTIONDATA);
            if(quizdata!=null && quizdata!=undefined){
              $scope.quiz = JSON.parse(quizdata);
            }else{
              $window.localStorage.setItem(CHALKNDUST.QUIZDATA,JSON.stringify(res.data.quiz));
              $scope.quiz = res.data.quiz;
            }
            $scope.config = $scope.defaultConfig;
            if(questiondata!=null && questiondata!=undefined){
              $scope.questions=[];
              var obj = JSON.parse(questiondata);
              $scope.questions = obj;
            }else{
              $window.localStorage.setItem(CHALKNDUST.QUESTIONDATA,JSON.stringify(res.data.questions));
              $scope.questions = $scope.config.shuffleQuestions ? helperService.shuffle(res.data.questions) : res.data.questions;
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
          });
        }

        $scope.shuffleOptions = function() {
          $scope.questions.forEach(function(question) {
            question.Options = helperService.shuffle(question.Options);
          });
        }

        $scope.loadQuiz($scope.quizName);

        $scope.isAnswered = function(index) {
          var answered = 'Not Answered';
          $scope.questions[index].Options.forEach(function(element, index, array) {
            if (element.Selected == true) {
              answered = 'Answered';
              return false;
            }
          });
          return answered;
        };

        $scope.isCorrect = function(question) {
          var result = 'correct';
          question.Options.forEach(function(option, index, array) {
            if (helperService.toBool(option.Selected) != option.IsAnswer) {
              result = 'wrong';
              return false;
            }
          });
          return result;
        };

      } ]);
});