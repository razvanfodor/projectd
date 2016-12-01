/* global app */

app.controller("UserDetailsController", function ($scope, $routeParams, $location, WebService) {

    function main() {
        initScope();
    }

    function initScope() {
        $scope.maxRatingPoints = 5;
        $scope.user = {};
        $scope.discounts = {};
        $scope.addComment = addComment;
        $scope.newComment = {
            userId : $routeParams.uid,
            rating : 1,
            summary : '',
                text : ''           
        };
        $scope.buy = buy;
        
        getUserDetails();
        getUserDiscounts();
    }

    function getUserDetails() {
        WebService.get("user/details", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.user = data;
                });
    }

    function getUserDiscounts() {
        WebService.get("discount/getCreatedBy", {"uid": $routeParams.uid})
                .then(function (data) {
                    $scope.discounts = data;
                });
    }

    function addComment(discountId) {
        WebService.put("user/addComment", $scope.newComment)
                .then(function () {
                    getUserDetails();
                });
    }
    
    function buy(discountId) {
        $location.path('discountDetails').search("did=" + discountId);
    }

    main();
});