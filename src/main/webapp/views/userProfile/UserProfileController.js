/* global app */

app.controller("UserProfileController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        refreshUser();
    }

    function refreshUser() {
        WebService.get("user/details")
                .then(function (data) {
                    $scope.user = data;
                });
    }

    main();
});