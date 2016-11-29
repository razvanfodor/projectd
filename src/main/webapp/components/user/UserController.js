app.controller('UserController', function ($scope, $location) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.userDetails = goToUserDetails;        
    }

    function goToUserDetails(userId) {
        $location.path('userDetails').search("uid=" + userId);
    }

    main();
});