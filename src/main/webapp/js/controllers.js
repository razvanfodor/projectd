'use strict';

var controllers = angular.module("controllers", []);

controllers.controller("HomeController", ['$scope', '$http', function ($scope, $http) {
        $scope.debug = true;
        $scope.title = 'Hello ';
        $scope.userName = 'xx';
        $scope.getResults =  function (){
            $http.get("service/home").success(function (data) {
                $scope.data = data;
                $scope.title += $scope.data.message;
            });
        }

        $scope.toggleDebug = function () {
            $scope.debug = !$scope.debug;
        };

        $scope.addUser = function () {
//            if ($scope.userName) {
                
                $http.post('service/home/addUser', $scope.userName).success(function () {
                    $scope.getResults();
                });
                $scope.text = '';
//            }
        }
        
        $scope.getResults();
    }]);