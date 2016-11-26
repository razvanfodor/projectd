/* global app */

app.controller("UserDetailsController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.user = {};
        $scope.discounts = {};
        $scope.buy = buy;

        getUserDetails();
        getUserDiscounts();
    }
    
    function getUserDetails() {
        WebService.get("user/details", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.user = data;
                });
    }

    function getUserDiscounts() {
        WebService.get("discount/getCreatedBy", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.discounts = data;
                });
    }
    
    function buy(discountId) {
        $location.path('discountDetails').search("did=" + discountId);
    }


    main();
});