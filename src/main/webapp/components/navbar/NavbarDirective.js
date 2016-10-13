/* global app */

app.directive('navbar', function () {
    return {
        restrict: 'E',
        templateUrl: 'components/navbar/navbar.html',
        scope: true,

        controller: 'NavbarController'
    };
});
