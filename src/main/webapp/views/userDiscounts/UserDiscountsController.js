/* global app */

app.controller("UserDiscountsController", function ($scope, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.myDiscounts = [];
        $scope.boughtDiscounts = [];
        WebService.get("discount/getMy")
                .then(function (data) {
                    $scope.myDiscounts = data;
                });
        WebService.get("discount/getBought")
                .then(function (data) {
                    $scope.boughtDiscounts = data;
                });
        $scope.goToDetails = goToDetails;
    }
    
    function goToDetails(discountId) {
        $location.path('discountDetails').search("did=" + discountId);
    }

    main();
});