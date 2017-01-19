/* global app */

app.controller("LoginController", function ($scope, $state, $sessionStorage, WebService) {

        function main() {
            initScope();
        }

        function initScope() {
            $scope.user = {};
            $scope.user.userName = '';
            $scope.user.password = '';
            $scope.login = login;
        }

        function login() {
            if ($scope.user.userName && $scope.user.password) {
                WebService.post('authentication/login', $scope.user)
                        .then(function(data){
                            $sessionStorage.authToken = data.authToken;
                            $state.go('app.viewMyDiscounts');
                        });
            }
        };
        
        main();
});