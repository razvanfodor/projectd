/* global app */

app.controller("UserDiscountsController", function ($scope, WebService) {

        function main() {
            initScope();
        }

        function initScope() {
            $scope.discounts = [];
            WebService.get("discount/getAll")
                    .then(function(data){
                       $scope.discounts = data;
                    });
        }
        
        main();
});