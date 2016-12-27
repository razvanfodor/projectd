
/* global app */
app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider
                .state('home', {
                    url: '/',
                    views: {
                        'content': {
                            templateUrl: 'views/welcome/welcome.html',
                            controller: 'WelcomeController'
                        }
                    }
                })
                .state('registerUser', {
                    url: '/registerUser',
                    views: {
                        'content': {
                            templateUrl: 'views/registerUser/registerUser.html',
                            controller: 'UserRegistrationController'
                        }
                    }
                })
                .state('login', {
                    url: '/login',
                    views: {
                        'content': {
                            templateUrl: 'views/login/login.html',
                            controller: 'LoginController'
                        }
                    }
                })
                .state('newDiscount', {
                    url: '/newDiscount',
                    views: {
                        'content': {
                            templateUrl: 'views/discountEdit/DiscountEdit.html',
                            controller: 'DiscountEditController'
                        }
                    }
                })
                .state('editDiscount', {
                    url : '/editDiscount/:did',
                    views: {
                        'content': {
                            templateUrl: 'views/discountEdit/DiscountEdit.html',
                            controller: 'DiscountEditController'
                        }
                    }
                })
                .state('viewMyDiscounts', {
                    url: '/viewMyDiscounts',
                    views: {
                        'content': {
                            templateUrl: 'views/userDiscounts/UserDiscounts.html',
                            controller: 'UserDiscountsController'
                        }
                    }
                })
                .state('displaySearchResults', {
                    url: '/displaySearchResults/:searchTerm',
                    views: {
                        'content': {
                            templateUrl: 'views/displaySearchResults/DisplaySearchResults.html',
                            controller: 'DisplaySearchResultsController'
                        }
                    }
                })
                .state('discountDetails', {
                    url: '/discountDetails/:did',
                    views: {
                        'content': {
                            templateUrl: 'views/discountDetails/DiscountDetails.html',
                            controller: 'DiscountDetailsController'
                        }
                    }
                })
                .state('userDetails', {
                    url: '/userDetails/:uid',
                    views: {
                        'content': {
                            templateUrl: 'views/userDetails/UserDetails.html',
                            controller: 'UserDetailsController'
                        }
                    }
                })
                .state('userProfile', {
                    url: '/userProfile',
                    views: {
                       'content': {
                            templateUrl: 'views/userProfile/UserProfile.html',
                            controller: 'UserProfileController'
                        }
                    }
                })
    }]);
