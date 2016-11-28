/* global app */

app.controller("UserDetailsController", function ($scope, $routeParams, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.maxRatingPoints = 5;
        $scope.user = {};
        $scope.discounts = {};
        $scope.buy = buy;

        getUserDetails();
        getUserDiscounts();
    }

    function getUserDetails() {
        WebService.get("user/details", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.user = data;
                    $scope.user.ratingPoints = $scope.user.ratingPoints / 2;
                    $scope.user.comments.forEach(function (comment) {
                        comment.rating = comment.rating / 2; 
                    }
                    );
                });
    }

    function getUserDiscounts() {
        WebService.get("discount/getCreatedBy", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.discounts = data;
                });
    }

    function buy(discountId) {
        $location.path('discountDetails').search("did=" + discountId);
    }


    main();
});