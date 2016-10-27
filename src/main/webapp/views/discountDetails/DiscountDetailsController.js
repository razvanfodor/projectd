/* global app */

app.controller("DiscountDetailsController", function ($scope, $routeParams, $location, WebService) {

        function main() {
            initScope();            
        }

        function initScope() {
            $scope.discount = {};
            WebService.get("discount/details", {"did":$routeParams.did})
                    .then(function(data){
                       $scope.discount = data;
                    });
                    
            $scope.buy = buy;
        }
        
        function buy(discountId){
//            $location.path('discountDetails').search("did="+discountId);
        }
        
        main();
});