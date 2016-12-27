/* global app */

app.controller('DiscountEditController', function ($scope, $state, $stateParams, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.discount = {
            id: $stateParams.did,
            discountName: '',
            description: '',
            website: 'http://',
            code: '',
            price: ''
        };
        $scope.title = isEditMode() ? 'Edit Discount' : 'Create New Discount';
        $scope.saveText = isEditMode() ? 'Update' : 'Create';
        $scope.tags = [];
        $scope.errorMessage = null;
        $scope.minDate = new Date().setDate(new Date().getDate() + 1); //tomorrow
        $scope.sellType = 'multiple';

        if (isEditMode()) {
            refreshDiscount();
        }

        $scope.saveDiscount = saveDiscount;
        $scope.isEditMode = isEditMode;
    }
    
    function refreshDiscount(){
            WebService.get("discount/details", {"did": $stateParams.did})
                    .then(function (data) {
                        $scope.discount = data;
                        data.tags.forEach(function (tag) {
                            $scope.tags.push({"text": tag});
                        });
                        $scope.discount.expiryDate = new Date(data.expiryDate);
                        $scope.sellType = data.singleSell ? 'single' : 'multiple';
                    });
    }

    function saveDiscount() {
        $scope.errorMessage = null;
        if ($scope.form.$invalid) {
            return;
        }
        $scope.discount.singleSell = 'single' === $scope.sellType;
        $scope.discount.tags = [];
        $scope.tags.forEach(function (tag) {
            $scope.discount.tags.push(tag.text);
        });
        if (isEditMode()) {
            WebService.put('discount/update', $scope.discount)
                    .then(function () {
                        $state.go('viewMyDiscounts');
                    })
                    .catch(function (data) {
                        $scope.errorMessage = data.message;
                    });
        } else {
            WebService.post('discount/saveNew', $scope.discount)
                    .then(function () {
                        $state.go('viewMyDiscounts');
                    })
                    .catch(function (data) {
                        $scope.errorMessage = data.message;
                    });
        }
    }

    function isEditMode() {
        return typeof $stateParams.did !== 'undefined';
    }

    main();
});