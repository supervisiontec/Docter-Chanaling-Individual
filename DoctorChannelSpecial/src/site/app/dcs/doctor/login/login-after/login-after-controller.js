(function () {
    angular.module("appModule")
            .controller("loginAfterController", function ($scope, searchFactory, Notification, $filter, $window, systemConfig) {
                $scope.ui = {};
                $scope.model = {};
                $scope.model.appointmentDetail = {};
                $scope.model.appointment = {};
                $scope.model.locationList = [];

                $scope.model.findAllLocation = function () {
                    searchFactory.findAllLocation(function (data) {
                        $scope.model.locationList = data;
                    });
                };
                $scope.ui.ChangeUI = function () {
                    //setDate
                    $scope.model.appointment.date = $filter('date')($scope.model.appointmentDetail.date, 'yyyy-MM-dd');
                    //set location name
                    angular.forEach($scope.model.locationList, function (value) {
                        if (value.indexNo === parseInt($scope.model.appointmentDetail.location)) {
                            $scope.model.appointment.location = value.name;
                        }
                    });
                    //view create ui
                    $scope.ui.mode = 'CREATE';
                };
                $scope.model.saveAppointment = function () {
                    $scope.ui.mode = 'CHANNEL';
                    $scope.model.appointmentDetail.doctor = 1;
                    $scope.model.appointmentDetail.availability = 'NOT AVALABLE';
                    $scope.model.appointmentDetail.activeStatus = "DEACTIVE";
                    searchFactory.saveAppointment($scope.model.appointmentDetail, function (data) {
                        Notification.success("Appointment Save Success");
                    });
                };
                $scope.ui.goToChannelUI = function () {
                    $window.location.href = systemConfig.apiUrl + "#/channel-view"
                            + "/" + $scope.model.appointmentDetail.date
                            + "/" + $scope.model.appointmentDetail.location;
                };

                $scope.ui.init = function () {
                    $scope.ui.mode = 'NON';
                    $scope.model.findAllLocation();
                    $scope.model.appointmentDetail.date = new Date();
                };
                $scope.ui.init();
            });
}());

