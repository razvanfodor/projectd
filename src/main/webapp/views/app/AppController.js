/* global app */

app.controller("AppController", function ($state) {

        function main() {
            initScope();
        }

        function initScope() {
            $state.go('app.home');
        }

        main();
});