app.controller('NavbarController', function ($scope, $sessionStorage, $location) {    
    function main() {
        initScope();
    }

    function initScope() {
      $scope.sessionStorage = $sessionStorage;
      $scope.logout = logout;
    }
    
    function logout(){
        delete $sessionStorage.authToken;
        $location.path('/').search("");
    }
    
    main();    
});