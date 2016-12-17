/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {};
        $scope.tags = [];
        $scope.sellType = '';

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
                    data.tags.forEach(function (tag) {
                        $scope.tags.push({"text": tag});
                    });
                    $scope.sellType = data.singleSell ? 'Single Time' : 'Multiple Times';
                });
    }

    main();
});