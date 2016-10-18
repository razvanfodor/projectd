app.controller('SearchController', function ($scope, $location) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.searchTerm = '';
        $scope.search = search;
    }

    function search() {
        $location.path('displaySearchResults').search("searchTerm="+$scope.searchTerm);
    }

    main();
});