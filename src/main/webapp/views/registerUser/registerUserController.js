app.controller("UserRegistrationController", function ($scope, $http) {

        function main() {
            initScope();
        }

        function initScope() {
            $scope.user = {};
            $scope.user.firstName = '';
            $scope.user.lastName = '';
            $scope.user.userName = '';
            $scope.user.password = '';
            $scope.registerUser = registerUser;
        }

        function registerUser() {
            if ($scope.user.userName) {
                $http.post('service/user/register', $scope.user);
            }
        };
        
        main();
});