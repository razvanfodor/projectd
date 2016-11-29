/* global app */

app.directive('user', function () {
    return {
        restrict: 'E',
        templateUrl: 'components/user/user.html',
        scope: {
            id : "=",
            name : "="
        },
        controller: 'UserController'
    };
});
