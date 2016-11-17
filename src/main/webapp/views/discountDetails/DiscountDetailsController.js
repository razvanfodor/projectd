/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {};
        refreshDiscount();
        $scope.buyDiscount = buyDiscount;
    }
    
    function refreshDiscount(){
        WebService.get("discount/details", {"did": $routeParams.did})
                .then(function (data) {
                    $scope.discount = data;
                });
    }

    function buyDiscount() {
        WebService.put("discount/buy", $routeParams.did)
                .then(function () {
                    refreshDiscount();
                });
    }
    
    main();
});