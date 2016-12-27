app.controller('NavbarController', function ($scope, $sessionStorage, $state) {    
    function main() {
        initScope();
    }

    function initScope() {
      $scope.sessionStorage = $sessionStorage;
      $scope.logout = logout;
    }
    
    function logout(){
        delete $sessionStorage.authToken;
        $state.go('home');
    }
    
    main();    
});