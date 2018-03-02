(function () {
    angular.module("appModule")
            .factory("searchFactory", function ($http, systemConfig) {
                var factory = {};

                factory.findAllDoctors = function (callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-all-doctors";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAllClient = function (callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-all-client";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.getClientByMobile = function (mobile, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-client-by-mobile/" + mobile;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAllCategory = function (callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-all-category";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAllLocation = function (callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-all-location";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAppointmentByDateDoctorAndLocation = function (date, doctor, location, callback, errorCallback) {
                    console.log(date, doctor, location);
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-appoinment-by-date-doctor-and-location/" + date + "/" + doctor + "/" + location;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation = function (date, doctor, location, callback, errorCallback) {
                    console.log(date, doctor, location);
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-max-appointment-and-runing-no/" + date + "/" + doctor + "/" + location;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
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
                factory.saveAppointmentDetail = function (details, callback, errorCallback) {
                    console.log(details);
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/save-appointment-detail";
                    $http.post(url, details)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.sendSMSClientConfirmation = function (location, client, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/send-sms-verification/";
                    $http.post(url + location, client)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findRuningAppointmentDetailbyAppointment = function (appointmentIndex, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-run-appointment-detail-by-appointment/" + appointmentIndex;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findRuningAppointmentDetailbyAppointment = function (appointmentIndex, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-run-appointment-detail-by-appointment/" + appointmentIndex;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.nextAndDoneChannels = function (appointmentIndex, appointmentDetail, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/next-and-done-channels/";
                    $http.post(url + appointmentIndex, appointmentDetail)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.skipAndNextChannel = function (appointmentIndex, appointmentDetail, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/skip-and-next-channels/";
                    $http.post(url + appointmentIndex, appointmentDetail)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.canselAndNextChannel = function (appointmentIndex, appointmentDetail, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/cancel-and-next-channels/";
                    $http.post(url + appointmentIndex, appointmentDetail)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAllSkipAppointment = function (date, doctor, location,callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/find-all-skip-appointment/"+ date + "/"+doctor + "/"+location;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAllCancelAppointment = function (date, doctor, location,callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/find-all-cancel-appointment/"+ date + "/"+doctor + "/"+location;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.backAndPendingChannels = function (appointmentIndex, appointmentDetail, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/back-and-get-done-channels/" ;
                    $http.post(url + appointmentIndex, appointmentDetail)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.doneThisChannels = function (appointmentDetail, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/done-this-channels" ;
                    $http.post(url , appointmentDetail)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.appointmentCount = function (date, doctor, location, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/appointment-count/"+ date + "/"+doctor + "/"+location;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.findAppointmentDetails = function (date, doctor, callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/appointment-detail-by-doctor-and-date/"+ date + "/"+doctor;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {
                                if (errorCallback) {
                                    errorCallback(data);
                                }
                            });
                };
                factory.getNotAllocateList = function (date,doctor,location,callback, errorCallback) {
                    var url = systemConfig.apiUrl + "/api/v1/doctor-channel/mobile/master/get-not-allocate-list/"+ date + "/"+doctor + "/"+location;
                    $http.get(url)
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
}());


