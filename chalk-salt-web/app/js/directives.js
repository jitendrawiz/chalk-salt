'use strict';
define(['./app.js', 'angular'], function(directives, angular) {

    /**
     * Directive to place header in html
     */
    directives.directive('header', function() {
        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'templates/header.html',
            controller: ['$scope', '$filter', function($scope, $filter) {
                $scope.test = 'test';
                $filter.test = 'test';
            }]
        };
    });

    /**
     * Directive to place footer in html
     */
    directives.directive('footer', function() {
        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'templates/footer.html',
            controller: ['$scope', '$filter', function($scope, $filter) {
                $scope.test = 'test';
                $filter.test = 'test';
            }]
        };
    });

    /**
     * Directive to match values of two elements (mainly useful for confirm password)
     */ 
    directives.directive('match', function() {
        return {
            require: 'ngModel',
            restrict: 'A',
            scope: {
                match: '='
            },
            link: function(scope, elem, attrs, ctrl) {
                scope.$watch(function() {
                    return (ctrl.$pristine && angular.isUndefined(ctrl.$modelValue)) || scope.match === ctrl.$modelValue;
                }, function(currentValue) {
                    ctrl.$setValidity('match', currentValue);
                });
            }
        };
    });

    /**
     * Directive to validate phone number
     */
    directives.directive('phoneNumber', function() {
        return {
            require: 'ngModel',
            restrict: 'A',
            link: function(scope, elem, attrs, ctrl) {
                scope.$watch(function() {
                    var phoneRegExp = new RegExp(/^[\d|\+|\(]+[\)|\d|\s|-]*[\d]$/);
                    
                    return (ctrl.$pristine && angular.isUndefined(ctrl.$modelValue)) 
                    || (ctrl.$dirty && angular.equals(ctrl.$modelValue, '')) 
                    || phoneRegExp.test(ctrl.$modelValue);

                }, function(currentValue) {
                    ctrl.$setValidity('phonenumber', currentValue);
                });
            }
        };
    });

    /**
     * Directive to bind the file data to modal attribute for  input[type="file"]
     */
    directives.directive('fileModel', [ '$parse', function($parse) {
        return {
            restrict : 'A',
            link : function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function() {
                    scope.$apply(function() {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    } ]);
    
    /**
     * Directive to validate password strength
     */
    directives.directive('passwordStrength', function() {
        return {
          require: 'ngModel',
          restrict: 'AE',
          template: "<div ng-class='{strong : passStrength == 3,medium : passStrength == 2,weak : passStrength == 1}'>{{strength}}</div>",
          scope:{
              passValue: '=ngModel'
          },
          link: function(scope, elm, attrs,ctrl) {
              scope.$watch('passValue', function(value) {
              var strongRegex = new RegExp("^((?=(?:.*[A-Z]){2})(?=(?:.*[a-z]){2})(?=(?:.*[0-9]){2})(?=.{8,}))");
              var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
             
                  if(strongRegex.test(value)) {
                     scope.strength= "Strong";
                     scope.passStrength = 3;
                     
                  } else if(mediumRegex.test(value)) {
                      scope.strength= "Medium";
                      scope.passStrength = 2;
                  } else {
                      scope.strength= "Weak";
                      scope.passStrength =1;
                  }
              
              });
              
            }
        };
      });
    
    
    /**
     * Directive to display default image if there is some error in source image
     */
    directives.directive('errSrc', function() {
        return {
          link: function(scope, element, attrs) {
            element.bind('error', function() {
              if (attrs.src != attrs.errSrc) {
                attrs.$set('src', attrs.errSrc);
              }
            });
            
            attrs.$observe('ngSrc', function(value) {
              if (!value && attrs.errSrc) {
                attrs.$set('src', attrs.errSrc);
              }
            });
          }
        }
      });
    
    directives.directive('ngEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if(event.which === 13) {
                    scope.$apply(function (){
                        scope.$eval(attrs.ngEnter);
                    });
     
                    event.preventDefault();
                }
            });
        };
    });
    

    return directives;
});