
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
                .when('/newDiscount', {
                    templateUrl: 'views/discountEdit/DiscountEdit.html',
                    controller: 'DiscountEditController'
                })
                .when('/editDiscount', {
                    templateUrl: 'views/discountEdit/DiscountEdit.html',
                    controller: 'DiscountEditController'
                })
                .when('/viewMyDiscounts', {
                    templateUrl: 'views/userDiscounts/UserDiscounts.html',
                    controller: 'UserDiscountsController'
                })
                .when('/displaySearchResults', {
                    templateUrl: 'views/displaySearchResults/DisplaySearchResults.html',
                    controller: 'DisplaySearchResultsController'
                })
                .when('/discountDetails', {
                    templateUrl: 'views/discountDetails/DiscountDetails.html',
                    controller: 'DiscountDetailsController'
                })
                .when('/userDetails', {
                    templateUrl: 'views/userDetails/UserDetails.html',
                    controller: 'UserDetailsController'
                })
                .otherwise({
                    redirectTo: '/'
                });
    }]);
