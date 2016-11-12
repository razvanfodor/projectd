/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {};
        WebService.get("discount/details", {"did": $routeParams.did})
                .then(function (data) {
                    $scope.discount = data;
                });

        $scope.buyDiscount = buyDiscount;
    }

    function buyDiscount() {
//            $location.path('discountDetails').search("did="+discountId);
    }
    
    main();
});