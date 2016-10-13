
/* global app */

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'views/welcome/welcome.html',
                    controller: 'WelcomeController'
                })
                .when('/welcome', {
                    templateUrl: 'views/welcome/welcome.html',
                    controller: 'WelcomeController'
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
