/* global app */

app.controller('NewDiscountController', function ($scope, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.discountName = '';
        $scope.website = '';
        $scope.code = '';
        $scope.price = '';
        
        $scope.saveDiscount = saveDiscount;
    }

    function saveDiscount() {
        var discount = {
            discountName: $scope.discountName,
            website: $scope.website,
            code: $scope.code,
            price: $scope.price
        };
        WebService.post('discount/saveNew', discount)
                .then(function () {
                    $scope.discountName = '';
                    $scope.website = '';
                    $scope.code = '';
                    $scope.price = '';
                });
    }

    main();

});