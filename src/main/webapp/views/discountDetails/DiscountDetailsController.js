/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {};
        refreshDiscount();
        $scope.buyDiscount = buyDiscount;
    }
    
    function buyDiscount() {
        WebService.put("discount/buy", $routeParams.did)
                .then(function () {
                    refreshDiscount();
                });
    }
    
    function refreshDiscount() {
        WebService.get("discount/details", {"did": $routeParams.did})
                .then(function (data) {
                    $scope.discount = data;
                });
    }

    main();
});