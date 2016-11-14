/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, $location, WebService) {

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
        WebService.put("discount/buy", $routeParams.did)
                .then(function () {
                    $location.path('discountDetails').search("did=" + $routeParams.did);
                });
    }
    
    main();
});