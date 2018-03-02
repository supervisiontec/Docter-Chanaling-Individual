(function () {
    //constants
    angular.module("appModule")
            .constant("systemConfig", {
                apiUrl:
                        location.hostname === 'localhost'
                        ? "http://localhost:8080"
//                        ? "http://localhost:8070"
                        : location.protocol + "//" + location.hostname + (location.port ? ':' + location.port : '')
            });

    angular.module("appModule")
            .config(function ($routeProvider) {
                $routeProvider

                        .when("/", {
                            templateUrl: "app/menu/menu.html",
                            controller: "menuController"
                        })
//                        .when("/", {
//                            templateUrl: "app/dcs/client/client.html",
//                            controller: "clientController"
//                        })
                        .when("/login", {
                            templateUrl: "app/system/login.html",
                            controller: "LoginController"
                        })

                        .when("/client-view", {
                            templateUrl: "app/dcs/client/client.html",
                            controller: "clientController"
                        })
                        .when("/client-appointment-view/:date/:location/:doctor/:availability/:indexNo/:maxAppointmentNo/:runningNo", {
                            templateUrl: "app/dcs/client/client-appointment.html",
                            controller: "clientAppointmentController"
                        })
                        .when("/channel-view/:date/:location", {
                            templateUrl: "app/dcs/doctor/channel/channel.html",
                            controller: "channelController"
                        })
                        .when("/create-appointment-view", {
                            templateUrl: "app/dcs/doctor/create/create-appointment.html",
                            controller: "createAppointmentController"
                        })
                        .when("/login-after-view", {
                            templateUrl: "app/dcs/doctor/login/login-after/login-after.html",
                            controller: "loginAfterController"
                        })
                        .when("/menu-view", {
                            templateUrl: "app/menu/menu.html",
                            controller: "menuController"
                        })

                        .otherwise({
                            redirectTo: "/"
                        });
            });

//    angular.module("appModule")
//            .run(function ($rootScope, $location, $cookieStore, $http) {
//                // keep user logged in after page refresh
//                $rootScope.globals = $cookieStore.get('globals') || {};
//                if ($rootScope.globals.currentUser) {
//                    $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//                }
//
//                $rootScope.$on('$locationChangeStart', function (event, next, current) {
//                    // redirect to login page if not logged in
//                    if ($location.path() !== '/' && !$rootScope.globals.currentUser) {
//                        $location.path('/');
//                    }
//                });
//            });
//
//    angular.module("appModule")
//            .controller("IndexController", function ($scope, $rootScope, $location,$cookieStore,$http) {
//                $rootScope.model = {};
//                $scope.ui = {};
//                $scope.model.user = {};
//                $rootScope.model.map = [];
//
//                $scope.ui.logout = function () {
//                    $rootScope.value = null;
//                    $rootScope.globals = {};
//                    $cookieStore.remove('globals');
//                    $http.defaults.headers.common.Authorization = 'Basic ';
//                    $location.path("/");
//                };
//
////                window.onbeforeunload = function (){
////                    return "do you want to refresh ?";
////                };
//
//                $scope.ui.setDepartmentLabel = function (userIndexNo) {
//                    var departmentName;
//                    angular.forEach($scope.model.userList, function (value) {
//                        var department;
//                        if (value.indexNo === parseInt(userIndexNo)) {
//                            department = value.department;
//                            angular.forEach($scope.model.departmentList, function (value) {
//                                if (value.indexNo === parseInt(department)) {
//                                    departmentName = value.name;
//                                    return;
//                                }
//                            });
//                        }
//                    });
//                    return departmentName;
//                };
//
//                $scope.ui.init = function () {
//
//                    if (angular.isUndefined($rootScope.globals.currentUser)) {
//                        console.log("Console undifind");
//                    } else {
//                        $scope.ui.setDepartmentLabel($rootScope.globals.currentUser);
//                    }
////                    Factory.findAll(findAllUserUrl, function (data) {
////                        $scope.model.userList = data;
////                    });
////                    Factory.findAll(findAllDepartmentUrl, function (data) {
////                        $scope.model.departmentList = data;
////                    });
//
//                };
//                $scope.ui.init();
//            });
}());