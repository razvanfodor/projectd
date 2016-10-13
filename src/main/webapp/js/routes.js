
/* global app */

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'partials/home.html',
                    controller: 'HomeController'
                })
                .when('/registerUser', {
                    templateUrl: 'views/registerUser/registerUser.html',
                    controller: 'UserRegistrationController'
                })
                .when('/login', {
                    templateUrl: 'views/login/login.html',
                    controller: 'LoginController'
                })
                .otherwise({
                    redirectTo: '/'
                });
    }]);
