/* global app */

app.directive('search', function () {
    return {
        restrict: 'E',
        templateUrl: 'components/search/search.html',
        scope: true,
        controller: 'SearchController'
    };
});
