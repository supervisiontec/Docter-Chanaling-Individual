(function () {
    angular.module("appModule")
            .factory("menuFactory", function (systemConfig, $http) {
                var factory = {};

                factory.saveAppointment = function (data, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/save-appointment";
                    $http.post(url, data)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                return factory;
            });

    angular.module("appModule")
            .controller("createAppointmentController", function ($scope, menuFactory, searchFactory, Notification, $filter) {
                $scope.ui = {};
                $scope.model = {};
                $scope.model.appointmentDetail = {};
                

                $scope.model.saveAppointment = function () {
                    $scope.ui.mode = 'CHANNEL';

                    $scope.model.appointmentDetail.doctor = 1;
//                    $scope.model.appointmentDetail.startTime = $filter('date')($scope.model.appointmentDetail.startTime, 'hh:mm:ss');;
//                    $scope.model.appointmentDetail.date = $filter('date')($scope.model.appointmentDetail.date, 'yyyy-MM-dd');;
                    console.log($scope.model.appointmentDetail);
                    menuFactory.saveAppointment($scope.model.appointmentDetail, function (data) {
                        Notification.success("Appointment Save Success");
                        console.log(data);
                    });
                };
                $scope.model.findAllLocation = function () {
                    searchFactory.findAllLocation(function (data) {
                        $scope.model.locationList = data;
                    });
                };

                $scope.ui.init = function () {
                    $scope.model.findAllLocation();


                    $scope.model.appointmentDetail.date = new Date();
                };
                $scope.ui.init();
            });
}());

