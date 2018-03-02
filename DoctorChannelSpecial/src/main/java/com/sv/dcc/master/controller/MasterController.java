/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.controller;


import com.sv.dcc.master.model.Appointment;
import com.sv.dcc.master.model.AppointmentAndClient;
import com.sv.dcc.master.model.AppointmentDetail;
import com.sv.dcc.master.model.Category;
import com.sv.dcc.master.model.Client;
import com.sv.dcc.master.model.Doctor;
import com.sv.dcc.master.model.Location;
import com.sv.dcc.master.model.MaxAppointmentAndRuningNo;
import com.sv.dcc.master.model.Verification;
import com.sv.dcc.master.model.pojo.DoctorDetails;
import com.sv.dcc.master.model.pojo.SkipAndNextAppointmentDetail;
import com.sv.dcc.master.service.AndroidAppService;
import com.sv.dcc.master.service.MasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Thilina Kalum
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/doctor-channel/mobile/master")
public class MasterController {
    
    @Autowired
    private MasterService masterService;
    
    @Autowired
    private AndroidAppService androidAppService;
    
    @RequestMapping(value = "/get-all-doctors" , method = RequestMethod.GET)
    public List<Doctor> getAllDoctor(){
        return masterService.getAllDoctor();
    }
    @RequestMapping(value = "/get-all-category" , method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        return masterService.getAllCategory();
    }
    @RequestMapping(value = "/get-all-location" , method = RequestMethod.GET)
    public List<Location> getAllLocation(){
        return masterService.getAllLocation();
    }
    @RequestMapping(value = "/get-all-client" , method = RequestMethod.GET)
    public List<Client> getAllClient(){
        return masterService.getAllClient();
    }
    @RequestMapping(value = "/get-client-by-mobile/{mobile}" , method = RequestMethod.GET)
    public Client getClientByMobile(@PathVariable("mobile") String mobile){
        return masterService.getClientByMobile(mobile);
    }
    @RequestMapping(value = "/get-appoinment-by-date-doctor-and-location/{date}/{doctor}/{location}" , method = RequestMethod.GET)
    public Appointment getAppoinmentByDateDoctorAndLocation(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor, @PathVariable ("location")Integer location){
        return masterService.getAppoinmentByDateDoctorAndLocation(date, doctor, location);
    }
    @RequestMapping(value = "/get-max-appointment-and-runing-no/{date}/{doctor}/{location}", method = RequestMethod.GET)
    public MaxAppointmentAndRuningNo getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(@PathVariable("date") String date, @PathVariable("doctor") Integer doctor, @PathVariable("location") Integer location) {
        return androidAppService.getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(date,doctor,location);
    }
    @RequestMapping(value = "/save-appointment" , method = RequestMethod.POST)
    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return masterService.saveAppointment(appointment);
    }
    @RequestMapping(value = "/save-appointment-detail" , method = RequestMethod.POST)
    public AppointmentDetail saveAppointmentDetail(@RequestBody AppointmentAndClient appointmentAndClient){
        return masterService.saveAppointmentDetail(appointmentAndClient);
    }
    @RequestMapping(value = "/send-sms-verification/{location}" , method = RequestMethod.POST)
    public Verification sendSMSClientConfirmation(@PathVariable("location") Integer location,@RequestBody Client client ){
        return masterService.sendSMSClientConfirmation(location,client);
    }
    @RequestMapping(value = "/get-run-appointment-detail-by-appointment/{appointmentIndex}" , method = RequestMethod.GET)
    public AppointmentDetail findRuningAppointmentDetailAppointment(@PathVariable ("appointmentIndex")Integer appointmentIndex){
        return masterService.findRuningAppointmentDetailAppointment(appointmentIndex);
    }
    @RequestMapping(value = "/next-and-done-channels/{appointmentIndex}" , method = RequestMethod.POST)
    public AppointmentDetail nextAndDoneChannels(@PathVariable ("appointmentIndex")Integer appointmentIndex,@RequestBody(required = false) AppointmentDetail appointmentDetail){
        return masterService.nextAndDoneChannels(appointmentIndex,appointmentDetail);
    }
    @RequestMapping(value = "/skip-and-next-channels/{appointmentIndex}" , method = RequestMethod.POST)
    public SkipAndNextAppointmentDetail skipAndNextChannel(@PathVariable ("appointmentIndex")Integer appointmentIndex,@RequestBody(required = false) AppointmentDetail appointmentDetail){
        return masterService.skipAndNextChannel(appointmentIndex,appointmentDetail);
    }
    @RequestMapping(value = "/cancel-and-next-channels/{appointmentIndex}" , method = RequestMethod.POST)
    public SkipAndNextAppointmentDetail cancelAndNextChannel(@PathVariable ("appointmentIndex")Integer appointmentIndex,@RequestBody(required = false) AppointmentDetail appointmentDetail){
        return masterService.cancelAndNextChannel(appointmentIndex,appointmentDetail);
    }
    @RequestMapping(value = "/find-all-skip-appointment/{date}/{doctor}/{location}" , method = RequestMethod.GET)
    public List<AppointmentDetail> findAllSkipAppointment(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor, @PathVariable ("location")Integer location){
        return masterService.findAllSkipAppointment(date,doctor,location);
    }
    @RequestMapping(value = "/find-all-cancel-appointment/{date}/{doctor}/{location}" , method = RequestMethod.GET)
    public List<AppointmentDetail> findAllCancelAppointment(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor, @PathVariable ("location")Integer location){
        return masterService.findAllCancelAppointment(date,doctor,location);
    }
    @RequestMapping(value = "/back-and-get-done-channels/{appointmentIndex}" , method = RequestMethod.POST)
    public AppointmentDetail backAndPendingChannels(@PathVariable("appointmentIndex")Integer appointmentIndex,@RequestBody(required = false) AppointmentDetail appointmentDetail){
        return masterService.backAndPendingChannels(appointmentIndex,appointmentDetail);
    }
    @RequestMapping(value = "/done-this-channels" , method = RequestMethod.POST)
    public void doneThisChannels(@RequestBody AppointmentDetail appointmentDetail){
        masterService.doneThisChannels(appointmentDetail);
    }
    @RequestMapping(value = "/appointment-count/{date}/{doctor}/{location}" , method = RequestMethod.GET)
    public Object appointmentCount(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor, @PathVariable ("location")Integer location){
        return masterService.appointmentCount(date, doctor, location);
    }
    @RequestMapping(value = "/appointment-detail-by-doctor-and-date/{date}/{doctor}" , method = RequestMethod.GET)
    public List<DoctorDetails> getAppointmentDetailsByDoctorAndDate(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor){
        return masterService.getAppointmentDetailsByDoctorAndDate(date, doctor);
    }
    @RequestMapping(value = "/get-not-allocate-list/{date}/{doctor}/{location}" , method = RequestMethod.GET)
    public List<AppointmentDetail> getNotAllocateList(@PathVariable ("date") String date , @PathVariable ("doctor")Integer doctor, @PathVariable ("location")Integer location){
        return masterService.getNotAllocateList(date, doctor, location);
    }
      
//    @RequestMapping(value = "/find-skip-appointment-order-by-frist-in/{appointmentIndex}" , method = RequestMethod.GET)
//    public AppointmentDetail findSkipAppointmentOrderByFristIn(@PathVariable ("appointmentIndex")Integer appointmentIndex){
//        return masterService.findSkipAppointmentOrderByFristIn(appointmentIndex);
//    }
      
}
