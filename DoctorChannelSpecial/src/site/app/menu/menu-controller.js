(function () {
    angular.module("appModule")
            .factory("menuFactory", function (systemConfig, $http) {
                var factory = {};

//                factory.findByUser = function (user, callback, errorCallback) {
//                    var url = systemConfig.apiUrl + "/api/v1/green-leaves/mobile/m-master-details/get-user-permition-by-user/" + user;
//                    $http.get(url)
//                            .success(function (data, status, headers) {
//                                callback(data);
//                            })
//                            .error(function (data, status, headers) {
//                                if (errorCallback) {
//                                    errorCallback(data);
//                                }
//                            });
//                };
                return factory;
            });

    angular.module("appModule")
            .controller("menuController", function ($scope) {
                $scope.ui = {};
                $scope.model = {};

                $scope.ui.init = function () {
//                    $scope.model.appointment.dates = new Date();
                };
                $scope.ui.init();
            });
}());
