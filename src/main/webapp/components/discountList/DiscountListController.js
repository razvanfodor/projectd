app.controller('DiscountListController', function ($scope, $location) {
    function main() {
        initScope();
        $scope.goToDetails = goToDetails;
    }

    function initScope() {
    }

    function goToDetails(discountId) {
        $location.path('discountDetails').search("did=" + discountId);
    }

    main();
});