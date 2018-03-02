(function () {
    angular.module("appModule")
            .controller("clientController", function ($scope, searchFactory, $filter, $window, systemConfig, Notification) {
                $scope.ui = {};
                $scope.model = {};
                $scope.ui.mode = "NONE";
                $scope.model.appointmentDetail = {};
                $scope.model.appointmentNumbers = {};
                $scope.ui.backButtonMode = "IDEAL";
                $scope.model.locationList = [];
//                $scope.model.doctorsList = [];
//                $scope.model.categoryList = [];

                $scope.model.setCurrentDate = function () {
                    $scope.model.appointmentDetail.date = new Date();
                };
//                $scope.model.findAllDoctors = function () {
//                    searchFactory.findAllDoctors(function (data) {
//                        $scope.model.doctorsList = data;
//                    });
//                };
//                $scope.model.findAllCategory = function () {
//                    searchFactory.findAllCategory(function (data) {
//                        $scope.model.categoryList = data;
//                    });
//                };
                $scope.model.findAllLocation = function () {
                    searchFactory.findAllLocation(function (data) {
                        $scope.model.locationList = data;
                    });
                };
                $scope.ui.back = function () {
                    $window.location.href = systemConfig.apiUrl + "#/menu-view";
                };
                $scope.ui.searchAppoinment = function () {
                    if ($scope.ui.validator()) {
//                        $scope.ui.backButtonMode = "IDEAL";
                        searchFactory.findAppointmentByDateDoctorAndLocation(
                                $filter('date')(new Date($scope.model.appointmentDetail.date), 'yyyy-MM-dd'),
                                1,
                                $scope.model.appointmentDetail.location,
                                function (data) {
                                    $scope.model.appointmentDetail.indexNo = data.indexNo;
                                    $scope.model.appointmentDetail.startTime = data.startTime;
                                    $scope.model.appointmentDetail.availability = data.availability;
                                    if (data) {
                                        if (data.activeStatus === "ACTIVE") {
                                            $scope.ui.mode = "AVALABLE";
                                        } else {
                                            $scope.ui.mode = "NONACTIVE";
                                        }
                                    } else {
                                        $scope.ui.mode = "NOTAVALABLE";
                                    }
                                });
                        searchFactory.getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(
                                $filter('date')(new Date($scope.model.appointmentDetail.date), 'yyyy-MM-dd'),
                                1,
                                $scope.model.appointmentDetail.location,
                                function (data) {
                                    $scope.model.appointmentNumbers = data;
                                });
                    }
                };
                $scope.ui.getAppointment = function () {
                    $window.location.href = systemConfig.apiUrl + "#/client-appointment-view"
                            + "/" + $scope.model.appointmentDetail.date
                            + "/" + $scope.model.appointmentDetail.location
//                            + "/" + $scope.model.appointmentDetail.doctor
                            + "/" + 1
                            + "/" + $scope.model.appointmentDetail.availability
                            + "/" + $scope.model.appointmentDetail.indexNo
                            + "/" + $scope.model.appointmentNumbers.maxAppointmentNo
                            + "/" + $scope.model.appointmentNumbers.runningNo;

                };
                $scope.ui.validator = function () {
                    if (angular.isUndefined($scope.model.appointmentDetail.date)) {
                        Notification.error("Please select date ");
                    } else if (angular.isUndefined($scope.model.appointmentDetail.category)) {
                        Notification.error("Please select category ");
                    } else if (angular.isUndefined($scope.model.appointmentDetail.doctor)) {
                        Notification.error("Please select doctor ");
                    } else if (angular.isUndefined($scope.model.appointmentDetail.location)) {
                        Notification.error("Please select location ");
                    } else if (!angular.isUndefined($scope.model.appointmentDetail.date)
                            && !angular.isUndefined($scope.model.appointmentDetail.category)
                            && !angular.isUndefined($scope.model.appointmentDetail.doctor)
                            && !angular.isUndefined($scope.model.appointmentDetail.location)) {
                        return true;
                    }
                };
                $scope.model.findAppointmentDetails = function () {
                    var date = $filter('date')(new Date(), 'yyyy-MM-dd');
                    searchFactory.findAppointmentDetails(date, 1,
                            function (data) {
                                console.log(data);
                                $scope.model.appointmentDetail.category = data[0].category;
                                $scope.model.appointmentDetail.doctor = data[0].name;
//                                angular.forEach(function (){
//                                    $scope.model.locationList.push(data[0].location);
//                                });
//                                for (var i = 0; i < data.length; i++) {
//                                    $scope.model.locationList.push(data[i].location);
//                                }

//                                console.log($scope.model.locationList);
                            });
                };
                $scope.ui.init = function () {
                    $scope.model.findAppointmentDetails();
                    $scope.model.findAllLocation();
//                    $scope.model.findAllCategory();
//                    $scope.model.findAllDoctors();
                    $scope.model.setCurrentDate();
                };
                $scope.ui.init();
            });
}());

