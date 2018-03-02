(function () {
    angular.module("appModule")
            .controller("clientAppointmentController", function ($scope, searchFactory, $filter, $routeParams, Notification, $window, systemConfig) {
                $scope.ui = {};
                $scope.model = {};
                $scope.ui.mode = null;
                $scope.ui.modee = null;
                $scope.ui.backButtonMode = "IDEAL";
                $scope.model.appointment = {};
                $scope.model.appointmentDetail = {};
                $scope.model.appointmentNumbers = {};
                $scope.model.client = {};
                $scope.model.verificationDetail = {};
                $scope.model.doctorsList = [];
                $scope.model.locationList = [];
                $scope.model.clientList = [];
                $scope.model.notAllocateList = [];
                $scope.model.details = {
                    appointmentDetail: {},
                    client: {}
                };

                $scope.ui.backToClientMain = function () {
                    $window.location.href = systemConfig.apiUrl + "#/client-view";
                };
                $scope.ui.backToAppointmentSelect = function () {
                    $window.location.href = systemConfig.apiUrl + "#/client-appointment-view/:date/:location/:doctor/:availability/:indexNo/:maxAppointmentNo/:runningNo";
                };

                $scope.ui.varificationCode = function () {
                    if ($scope.ui.validator()) {
                        $scope.ui.mode = 'MAIN&VARIFICATION';
                        var location = $routeParams.location;
                        searchFactory.sendSMSClientConfirmation(location, $scope.model.client,
                                function (data) {
                                    $scope.model.verificationDetail = data;
                                    $scope.model.client.indexNo = data.client;
                                    Notification.success("SMS SEND SUCCESS !!!");
                                });
                    }
                };
                $scope.ui.getSelectNotAllocateNo = function (model,index){
                    $scope.model.appointmentDetail = model;
                    $scope.model.appointmentNumbers.maxAppointmentNo = model.appointmentNo;
                    $scope.model.notAllocateList.splice(index, 1);
                };

                $scope.ui.saveAppointmentDetail = function () {

                    if ($scope.model.verificationDetail.code === $scope.model.appointmentDetail.verificationCode) {

                        $scope.model.appointmentDetail.status = 'PENDING';
                        $scope.model.appointmentDetail.type = 'ONLINE';
                        $scope.model.appointmentDetail.appointment = $routeParams.indexNo;
                        $scope.model.appointmentDetail.appointmentCode = $scope.model.verificationDetail.code;

                        $scope.model.details.appointmentDetail = $scope.model.appointmentDetail;
                        $scope.model.details.client = $scope.model.client;
                        searchFactory.saveAppointmentDetail($scope.model.details,
                                function (data) {
                                    $scope.model.appointmentNumbers.maxAppointmentNo = data.appointmentNo;
                                    $scope.ui.modee = 'SUCCESS';
                                    Notification.success(data.appointmentNo + "Appointment Save success !!");
                                });
                    } else {
                        Notification.error("WRONG VERIFICATION CODE");
                    }
                };
                $scope.ui.getSelectedClientDetails = function (clientMobileNo) {
                    searchFactory.getClientByMobile(clientMobileNo, function (data) {
                        $scope.model.client = data;
                    });
                };

                $scope.ui.done = function () {
                    $window.location.href = systemConfig.apiUrl + "#/client-view";
                };
                $scope.ui.validator = function () {
                    if (angular.isUndefined($scope.model.client.name)) {
                        Notification.error("Please input name ");
                    } else if (angular.isUndefined($scope.model.client.address)) {
                        Notification.error("Please input city ");
                    } else if (angular.isUndefined($scope.model.client.mobile)) {
                        Notification.error("Please input mobile number ");
                    } else if (!angular.isUndefined($scope.model.client.name)
                            && !angular.isUndefined($scope.model.client.address)
                            && !angular.isUndefined($scope.model.client.mobile)) {
                        if (!angular.isNumber(parseInt($scope.model.client.mobile))) {
                            Notification.error("Please input numbers for mobile");
                        } else {
                            if (/^\d{10}$/.test($scope.model.client.mobile)) {
                                return true;
                            } else {
                                Notification.error("Mobile number hasnt 10 digit");
                            }
                        }
                    }
                };

                $scope.ui.init = function () {
                    $scope.ui.modee = 'MAIN';
                    $scope.ui.mode = 'MAIN&CLINTDETAIL';
//                    $scope.ui.mode = 'SUCCESS';
                    searchFactory.findAllClient(function (data) {
                        $scope.model.clientList = data;
                    });
                    searchFactory.getNotAllocateList(
                            $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd'),
                            $routeParams.doctor,
                            $routeParams.location,
                            function (data) {
                                console.log(data);
                                $scope.model.notAllocateList = data;
                            });
                    searchFactory.findAllDoctors(function (data) {
                        $scope.model.doctorsList = data;
                        angular.forEach($scope.model.doctorsList, function (value) {
                            if (value.indexNo === parseInt($routeParams.doctor)) {
                                $scope.model.appointment.doctor = value.name;
                                return;
                            }
                        });
                    });
                    searchFactory.findAllLocation(function (data) {
                        $scope.model.locationList = data;
                        angular.forEach($scope.model.locationList, function (value) {
                            if (value.indexNo === parseInt($routeParams.location)) {
                                $scope.model.appointment.location = value.name;
                                return;
                            }
                        });
                    });
                    $scope.model.appointment.date = $filter('date')(new Date($routeParams.date), 'yyyy-MM-dd');
                    $scope.model.appointment.availability = $routeParams.availability;
                    $scope.model.appointmentNumbers.maxAppointmentNo = $routeParams.maxAppointmentNo;
                    $scope.model.appointmentNumbers.runningNo = $routeParams.runningNo;

                };
                $scope.ui.init();
            });
}());

