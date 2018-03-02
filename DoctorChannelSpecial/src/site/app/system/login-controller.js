(function () {
    'use strict';

    //-----------http controller---------
    angular.module("appModule")
            .controller("LoginController", function ($scope, $rootScope, AuthenticationService, $cookieStore,$location) {
                //ui models
                $scope.ui = {};
                
                // reset login status
                AuthenticationService.ClearCredentials();

                $scope.login = function () {
                    AuthenticationService.Login($scope.userName, $scope.password, function (response) {
                        if (response) {
                            console.log(response);
                            AuthenticationService.SetCredentials($scope.userName, $scope.password, response.indexNo);
                            $rootScope.user = response;
                            $cookieStore.put('globals', $rootScope.globals);
                            $location.path('/login-after-view');
                        } else {
                            $rootScope.error = 'Username or password is incorrect';
                        }
                    });
                };

            });
}());
