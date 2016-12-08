/* global app */

app.controller("UserProfileController", function ($scope, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        $scope.updateProfile = updateProfile;
        $scope.errorMessage = null;
        
        refreshUser();
    }

    function refreshUser() {
        WebService.get("user/profile")
                .then(function (data) {
                    $scope.user = data;
                });
    }
    
    function updateProfile(){
        $scope.errorMessage = null;
        if ($scope.form.$invalid){
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
                })
                .catch(function (data){
                    $scope.errorMessage = data.message;
                });
            
    }

    main();
});