/* global app */

app.controller('NewDiscountController', function ($scope, $location, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.discountName = '';
        $scope.description = '';
        $scope.website = '';
        $scope.code = '';
        $scope.price = '';

        $scope.saveDiscount = saveDiscount;
    }

    function saveDiscount() {
        var discount = {
            discountName: $scope.discountName,
            description: $scope.description,
            website: $scope.website,
            code: $scope.code,
            price: $scope.price
        };
        WebService.post('discount/saveNew', discount)
                .then(function () {
                    $location.path('/viewMyDiscounts');
                });
    }

    main();

});