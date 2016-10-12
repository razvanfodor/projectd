'use strict';

var controllers = angular.module("controllers", []);

controllers.controller("HomeController", function ($scope, $http, WebService) {
        $scope.debug = true;
        $scope.title = 'Hello ';
        $scope.userName = 'xx';
        $scope.getResults = function () {
            WebService.get("home")
                    .then(function (data) {
                        $scope.data = data;
                        $scope.title += $scope.data.message;
                    });
        }

        $scope.toggleDebug = function () {
            $scope.debug = !$scope.debug;
        };

        $scope.addUser = function () {

            $http.post('service/home/addUser', $scope.userName).success(function () {
                $scope.getResults();
            });
            $scope.text = '';
        }

        $scope.getResults();
    });
