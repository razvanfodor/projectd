/* global app */

app.controller("UserRegistrationController", function ($scope, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        $scope.user.firstName = '';
        $scope.user.lastName = '';
        $scope.user.userName = '';
        $scope.user.email = '';
        $scope.user.password = '';
        $scope.registerUser = registerUser;
    }

    function registerUser() {
        if ($scope.user.userName) {
            WebService.post('user/register', $scope.user, {})
                    .then(function () {
                        $scope.user.firstName = '';
                        $scope.user.lastName = '';
                        $scope.user.userName = '';
                        $scope.user.email = '';
                        $scope.user.password = '';
                    });
        }
    }
    ;

    main();
});