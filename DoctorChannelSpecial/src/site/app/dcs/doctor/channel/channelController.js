(function () {
    angular.module("appModule")
            .controller("channelController", function ($scope, searchFactory, Notification, $routeParams, $filter, $window, systemConfig) {
                $scope.ui = {};
                $scope.model = {};
                $scope.model.appointment = {};
                $scope.model.appointmentDetail = {};
                $scope.model.appointmentCount = {};
                $scope.model.skipAppointmentList = [];
                $scope.model.cancelAppointmentList = [];
                $scope.model.locationList = [];
                $scope.ui.mode = 'CHANNEL';
                $scope.radioModel = 'Channel';
                $scope.ui.backButtonMode = "IDEAL";

                $scope.ui.changeView = function (mode) {
                    $scope.ui.mode = mode;
                };
                $scope.ui.back = function () {
                    $window.location.href = systemConfig.apiUrl + "#/menu-view";
                };

                $scope.model.saveAppointment = function () {
                    $scope.ui.mode = 'CHANNEL';
                    $scope.model.appointment.startTime =
                            $scope.model.appointment.doctor = 1;
                    $scope.model.appointment.location = $routeParams.location;
                    searchFactory.saveAppointment($scope.model.appointment, function (data) {
                        Notification.success("Appointment Save Success");
                    });
                };

                $scope.ui.nextAndDoneChannels = function () {
                    searchFactory.nextAndDoneChannels(
                            $scope.model.appointment.indexNo,
                            $scope.model.appointmentDetail,
                            function (data) {
                                $scope.model.appointmentDetail = {};
                                $scope.model.appointmentDetail = data;
                                $scope.model.getAppointmentCount();
                            });
                };
                $scope.ui.skipAndNextChannel = function () {
                    searchFactory.skipAndNextChannel(
                            $scope.model.appointment.indexNo,
                            $scope.model.appointmentDetail,
                            function (data) {
                                $scope.model.appointmentDetail = {};
                                $scope.model.appointmentDetail = data.nextAppointmentDetail;
                                $scope.model.skipAppointmentList.push(data.skipAppointmentDetail);
                                $scope.model.getAppointmentCount();
                            });
                };
                $scope.ui.canselAndNextChannel = function () {
                    searchFactory.canselAndNextChannel(
                            $scope.model.appointment.indexNo,
                            $scope.model.appointmentDetail,
                            function (data) {
                                $scope.model.appointmentDetail = {};
                                $scope.model.appointmentDetail = data.nextAppointmentDetail;
                                $scope.model.cancelAppointmentList.push(data.canselAppointmentDetail);
                                $scope.model.getAppointmentCount();
                            });

                };
                $scope.ui.backAndPendingChannels = function () {
                    searchFactory.backAndPendingChannels(
                            $scope.model.appointment.indexNo,
                            $scope.model.appointmentDetail,
                            function (data) {
                                $scope.model.appointmentDetail = {};
                                $scope.model.appointmentDetail = data;
                                $scope.model.getAppointmentCount();
                            });

                };
                $scope.ui.getSelectSkipNo = function (model, index) {
                    $scope.model.skipAppointmentList.splice(index, 1);
                    searchFactory.doneThisChannels(
                            $scope.model.appointmentDetail,
                            function (data) {
                                $scope.model.appointmentDetail = {};
                                $scope.model.appointmentDetail = model;
                                $scope.model.getAppointmentCount();
                            });
                };

                $scope.model.getAppointmentCount = function () {
                    searchFactory.appointmentCount(
                            $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd'),
                            1, //doctor
                            $routeParams.location, function (data) {
                                $scope.model.appointmentCount.total = data[0];
                                $scope.model.appointmentCount.pending = data[1];
                            });
                };
                $scope.model.convertTime = function(timeString) {
                    var timeTokens = timeString.split(':');
                    return new Date(1970, 0, 1, timeTokens[0], timeTokens[1], timeTokens[2]);
                };

                $scope.ui.init = function () {

//                    searchFactory.findAllLocation(function (data) {
//                        $scope.model.locationList = data;
//                        angular.forEach($scope.model.locationList, function (value) {
//                            if (value.indexNo === parseInt($routeParams.location)) {
//                                $scope.model.appointment.location = value.name;
//                                return;
//                            }
//                        });
//                    });
                    $scope.model.appointment.date = $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd');

                    searchFactory.findAppointmentByDateDoctorAndLocation(
                            $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd'),
                            1, //doctor
                            $routeParams.location,
                            function (data) {

                                searchFactory.findAllLocation(function (data) {
                                    $scope.model.locationList = data;
                                    angular.forEach($scope.model.locationList, function (value) {
                                        if (value.indexNo === parseInt($routeParams.location)) {
                                            $scope.model.appointment.location = value.name;
                                            return;
                                        }
                                    });
                                });
                                searchFactory.findRuningAppointmentDetailbyAppointment(
                                        data.indexNo,
                                        function (data) {
                                            if (!data.indexNo) {
                                                $scope.model.appointmentDetail.appointmentNo = '0';
                                            } else {
                                                $scope.model.appointmentDetail = data;
                                            }
                                        });
                                $scope.model.appointment = data;
                                $scope.model.appointment.startTime = $scope.model.convertTime(data.startTime);
                                $scope.model.appointment.availability = data.availability;
                            });
                    searchFactory.findAllSkipAppointment(
                            $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd'),
                            1, //doctor
                            $routeParams.location,
                            function (data) {
                                $scope.model.skipAppointmentList = data;
                            });
                    searchFactory.findAllCancelAppointment(
                            $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd'),
                            1, //doctor
                            $routeParams.location,
                            function (data) {
                                $scope.model.cancelAppointmentList = data;
                            });
                    $scope.model.getAppointmentCount();

                };
                $scope.ui.init();
            });
}());

