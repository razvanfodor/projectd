/* global app */

app.controller('DiscountEditHeaderController', function ($scope, $state, $stateParams, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.title = "Edit Discount";
        $scope.subtitle = "Here are the details of your discount.";
        
        if ($state.current.name === 'app.newDiscount'){
            $scope.title = "Create New Discount";
            $scope.subtitle = "Please insert the details below.";
        }
    }
   
    main();
});