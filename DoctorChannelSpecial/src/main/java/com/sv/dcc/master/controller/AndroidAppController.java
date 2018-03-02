/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.controller;

import com.sv.dcc.master.model.DoctorAndLocation;
import com.sv.dcc.master.model.MaxAppointmentAndRuningNo;
import com.sv.dcc.master.service.AndroidAppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Thilina Kalum
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/doctor-channel/mobile/android")
public class AndroidAppController {

    @Autowired
    private AndroidAppService androidAppService;

    @RequestMapping(value = "/get-all-doctors-location/{userName}/{password}/{curDate}", method = RequestMethod.GET)
    public List<DoctorAndLocation> findByUserNameAndPassword(@PathVariable("userName") String userName, @PathVariable("password") String password, @PathVariable("curDate") String curDate) {
        return androidAppService.findByUserNameAndPassword(userName, password, curDate);
    }
    @RequestMapping(value = "/get-max-appointment-and-runing-no/{date}/{doctor}/{location}", method = RequestMethod.GET)
    public MaxAppointmentAndRuningNo getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(@PathVariable("date") String date, @PathVariable("doctor") Integer doctor, @PathVariable("location") Integer location) {
        return androidAppService.getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(date,doctor,location);
    }
    @RequestMapping(value = "/save-appointment-detail-onfoot-client/{date}/{doctor}/{location}", method = RequestMethod.GET)
    public Integer saveAppointmentDetailByDateDoctorAndLocation(@PathVariable("date") String date, @PathVariable("doctor") Integer doctor, @PathVariable("location") Integer location) {
        return androidAppService.saveAppointmentDetailByDateDoctorAndLocation(date,doctor,location);
    }
    @RequestMapping(value = "/online-client-get-appointment-chit/{date}/{doctor}/{location}/{code}" , method = RequestMethod.GET)
    public Integer onlineClientGetAppointmentChit(@PathVariable("date") String date,@PathVariable("doctor") Integer doctor,@PathVariable("location")Integer location,@PathVariable("code")String code){
        return androidAppService.onlineClientGetAppointmentChit(date,doctor,location,code);
    }

}
