/* global app */

app.controller("DisplaySearchResultsController", function ($scope, $routeParams, $location, WebService) {

        function main() {
            initScope();            
        }

        function initScope() {
            $scope.discounts = [];
            WebService.get("discount/search", {"searchValue":$routeParams.searchTerm})
                    .then(function(data){
                       $scope.discounts = data;
                    });
                    
            $scope.buy = buy;
        }
        
        function buy(discountId){
            $location.path('discountDetails').search("did="+discountId);
        }
        
        main();
});