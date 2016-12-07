/* global app */

app.controller("UserProfileController", function ($scope, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        $scope.updateProfile = updateProfile;
        $scope.updatedProfileClicked = false;
        
        refreshUser();
    }

    function refreshUser() {
        WebService.get("user/profile")
                .then(function (data) {
                    $scope.user = data;
                });
    }
    
    function updateProfile(){
        if ($scope.form.$invalid){
            $scope.updatedProfileClicked = true;
            return;
        }
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