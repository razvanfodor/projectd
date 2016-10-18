/* global app */

app.controller("DisplaySearchResultsController", function ($scope, $routeParams, WebService) {

        function main() {
            initScope();
        }

        function initScope() {
            $scope.discounts = [];
            WebService.get("discount/search", {"searchValue":$routeParams.searchTerm})
                    .then(function(data){
                       $scope.discounts = data;
                    });
        }
        
        main();
});