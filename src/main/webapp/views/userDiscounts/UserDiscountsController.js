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
                    data.sort(function(a, b){
                        return b.creationDate - a.creationDate;
                    });
                    $scope.myDiscounts = data;
                });
        WebService.get("discount/getBought")
                .then(function (data) {
                    data.sort(function (a, b) {
                        return b.buyDate - a.buyDate;
                    });
                    $scope.boughtDiscounts = data;
                });
        $scope.goToDetails = goToDetails;
        $scope.editDiscount = editDiscount;
    }
    
    function goToDetails(discountId) {
        $state.go('app.discountDetails', {'did' : discountId});
    }
    
    function editDiscount(discountId) {
        $state.go('app.editDiscount', {'did': discountId});
    }

    main();
});