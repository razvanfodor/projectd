/* global app */

app.controller("UserDetailsController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        getUser();
    }
    
    function getUser() {
        WebService.get("user/details", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.user = data;
                });
    }

    main();
});