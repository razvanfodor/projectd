app.controller("LoginController", function ($scope, $http) {

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
                $http.post('service/authentication/login', $scope.user);
            }
        };
        
        main();
});