app.controller('UserController', function ($scope, $state) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.userDetails = goToUserDetails;        
    }

    function goToUserDetails(userId) {
        $state.go('userDetails', {uid : userId});
    }

    main();
});