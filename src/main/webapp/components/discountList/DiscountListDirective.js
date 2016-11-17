/* global app */

app.directive('discountList', function () {
    return {
        restrict: 'E',
        templateUrl: 'components/discountList/discountList.html',
        scope: {
            discounts : "="
        },
        controller: 'DiscountListController'
    };
});
