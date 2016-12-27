/* global app */

app.controller("UserDiscountsController", function ($scope, $state, WebService) {

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
        $scope.editDiscount = editDiscount;
    }
    
    function goToDetails(discountId) {
        $state.go('discountDetails', {'did' : discountId});
    }
    
    function editDiscount(discountId) {
        $state.go('editDiscount', {'did': discountId});
    }

    main();
});