app.controller('NavbarController', function ($scope, $sessionStorage) {    
    function main() {
        initScope();
    }

    function initScope() {
      $scope.sessionStorage = $sessionStorage;
      $scope.logout = logout;
    }
    
    function logout(){
        delete $sessionStorage.authToken;
    }
    
    main();    
});