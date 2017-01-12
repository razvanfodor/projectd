app.controller('NavbarController', function ($scope, $sessionStorage, $state) {    
    function main() {
        initScope();
    }

    function initScope() {
      $scope.sessionStorage = $sessionStorage;
      $scope.logout = logout;
      $scope.state = $state;
    }
    
    function logout(){
        delete $sessionStorage.authToken;
        $state.go('app.home');
    }
    
    main();    
});