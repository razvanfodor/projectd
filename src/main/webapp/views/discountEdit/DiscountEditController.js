/* global app */

app.controller('DiscountEditController', function ($scope, $location, $routeParams, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {
            id: $routeParams.did,
            discountName: '',
            description: '',
            website: '',
            code: '',
            price: ''
        };
        $scope.title = isEditMode() ? 'Edit Discount' : 'Create New Discount';
        $scope.saveText = isEditMode() ? 'Update' : 'Create';

        if (isEditMode()) {
            WebService.get("discount/details", {"did": $routeParams.did})
                    .then(function (data) {
                        $scope.discount = data;
                    });
        }

        $scope.saveDiscount = saveDiscount;
        $scope.isEditMode = isEditMode;
    }

    function saveDiscount() {
        if (isEditMode()) {
            WebService.put('discount/update', $scope.discount)
                    .then(function () {
                        $location.path('/viewMyDiscounts');
                    });
        }
        else {
            WebService.post('discount/saveNew', $scope.discount)
                    .then(function () {
                        $location.path('/viewMyDiscounts');
                    });
        }
    }

    function isEditMode() {
        return typeof $routeParams.did !== 'undefined';
    }

    main();
});