/* global app */

app.controller("UserRegistrationController", function ($scope, $state, WebService) {

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
        $scope.errorMessage = null;
        $scope.registerUser = registerUser;
    }

    function registerUser() {
        $scope.errorMessage = null;
        if ($scope.form.$invalid) {
            return;
        }
        WebService.post('user/register', $scope.user, {})
                .then(function () {
                    $state.go('login');
                })
                .catch(function (data) {
                    $scope.errorMessage = data.message;
                });
    }

    main();
});