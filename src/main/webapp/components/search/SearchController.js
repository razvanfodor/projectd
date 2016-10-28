app.controller('SearchController', function ($scope, $location, $sessionStorage) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.searchTerm = '';
        $scope.sessionStorage = $sessionStorage;
        $scope.search = search;        
    }

    function search() {
        $location.path('displaySearchResults').search("searchTerm="+$scope.searchTerm);
    }

    main();
});