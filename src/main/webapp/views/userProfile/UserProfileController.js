/* global app */

app.controller("UserProfileController", function ($scope, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        $scope.updateProfile = updateProfile;
        
        refreshUser();
    }

    function refreshUser() {
        WebService.get("user/profile")
                .then(function (data) {
                    $scope.user = data;
                });
    }
    
    function updateProfile(){
        var requestObj = {
            firstName : $scope.user.firstName,
            lastName : $scope.user.lastName,
            email : $scope.user.email
        };
        WebService.put("user/profile", requestObj)
                .then(function (){
                    $location.path('/home');   
                });
            
    }

    main();
});