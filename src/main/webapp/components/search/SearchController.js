app.controller('SearchController', function ($scope, $state, $sessionStorage) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.searchTerm = '';
        $scope.sessionStorage = $sessionStorage;
        $scope.search = search;        
    }

    function search() {
        $state.go('displaySearchResults', {searchTerm : $scope.searchTerm});
    }

    main();
});