
/* global app */
app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider
                .state('app', {
                    url: '/',
                    views : {
                        'header' : {
                            templateUrl : 'views/app/appHeader.html'
                        },
                        'content' : {
                            templateUrl : 'views/app/appContent.html',
                            controller: 'AppController'
                        }
                    }
                })
                .state('app.home', {
                    url: 'home',
                    views: {
                        'header@' : {
                            templateUrl: 'views/welcome/welcomeHeader.html'
                        },
                        'content@': {
                            templateUrl: 'views/welcome/welcomeContent.html',
                            controller: 'WelcomeController'
                        }
                    }
                })
                .state('app.registerUser', {
                    url: 'registerUser',
                    views: {
                        'content@': {
                            templateUrl: 'views/registerUser/registerUser.html',
                            controller: 'UserRegistrationController'
                        }
                    }
                })
                .state('app.login', {
                    url: 'login',
                    views: {
                        'content@': {
                            templateUrl: 'views/login/login.html',
                            controller: 'LoginController'
                        }
                    }
                })
                .state('app.newDiscount', {
                    url: 'newDiscount',
                    views: {
                        'header@': {
                            templateUrl: 'views/discountEdit/DiscountEditHeader.html',
                            controller: 'DiscountEditHeaderController'
                        },
                        'content@': {
                            templateUrl: 'views/discountEdit/DiscountEdit.html',
                            controller: 'DiscountEditController'
                        }
                    }
                })
                .state('app.editDiscount', {
                    url : 'editDiscount/:did',
                    views: {
                        'header@': {
                            templateUrl: 'views/discountEdit/DiscountEditHeader.html',
                            controller: 'DiscountEditHeaderController'
                        },
                        'content@': {
                            templateUrl: 'views/discountEdit/DiscountEdit.html',
                            controller: 'DiscountEditController'
                        }
                    }
                })
                .state('app.viewMyDiscounts', {
                    url: 'viewMyDiscounts',
                    views: {
                        'header@': {
                            templateUrl: 'views/userDiscounts/UserDiscountsHeader.html',
                        },
                        'content@': {
                            templateUrl: 'views/userDiscounts/UserDiscounts.html',
                            controller: 'UserDiscountsController'
                        }
                    }
                })
                .state('app.displaySearchResults', {
                    url: 'displaySearchResults/:searchTerm',
                    views: {
                        'content@': {
                            templateUrl: 'views/displaySearchResults/DisplaySearchResults.html',
                            controller: 'DisplaySearchResultsController'
                        }
                    }
                })
                .state('app.discountDetails', {
                    url: 'discountDetails/:did',
                    views: {
                        'content@': {
                            templateUrl: 'views/discountDetails/DiscountDetails.html',
                            controller: 'DiscountDetailsController'
                        }
                    }
                })
                .state('app.userDetails', {
                    url: 'userDetails/:uid',
                    views: {
                        'content@': {
                            templateUrl: 'views/userDetails/UserDetails.html',
                            controller: 'UserDetailsController'
                        }
                    }
                })
                .state('app.userProfile', {
                    url: 'userProfile',
                    views: {
                       'content@': {
                            templateUrl: 'views/userProfile/UserProfile.html',
                            controller: 'UserProfileController'
                        }
                    }
                })
    }]);
