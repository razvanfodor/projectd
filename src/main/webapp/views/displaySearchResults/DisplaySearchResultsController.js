/* global app */

app.controller("DisplaySearchResultsController", function ($scope, $stateParams, $state, WebService) {

        function main() {
            initScope();            
        }

        function initScope() {
            $scope.discounts = [];

            $scope.buy = buy;
            $scope.searchDiscounts = searchDiscounts;
        }
        
        function buy(discountId){
            $state.go('app.discountDetails', {did : discountId});
        }
        
        function searchDiscounts(tableState){
            var pagination = tableState.pagination;
            var startIndex = pagination.start || 0;     // This is NOT the page number, but the index of item in the list that you want to use to display the table.
            var numberEntriesPerPage = pagination.number || 10;
            var sortPredicate = tableState.sort.predicate;
            var sortReverse = tableState.sort.reverse;

            WebService.get("discount/search", {
               "searchValue" : $stateParams.searchTerm,
               "startIndex"  : startIndex,
               "numberEntriesPerPage" : numberEntriesPerPage,
               "sortPredicate" : sortPredicate,
               "sortReverse" : sortReverse
            })
                    .then(function(result){
                        $scope.discounts = result.data;
                        tableState.pagination.numberOfPages = Math.ceil(result.searchCount / numberEntriesPerPage);       
                    }); 
        }
        
        main();
});