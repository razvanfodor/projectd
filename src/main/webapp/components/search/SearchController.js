app.controller('SearchController', function ($scope, WebService) {
    function main() {
        initScope();
    }

    function initScope() {
        $scope.searchTerm = '';
        $scope.loadHints = loadHints;
    }

    function loadHints(searchVal) {
        return WebService.get('search/getHints', {searchValue: searchVal})
                .then(function (results) {
                    return results;
                });
    }

    main();
});