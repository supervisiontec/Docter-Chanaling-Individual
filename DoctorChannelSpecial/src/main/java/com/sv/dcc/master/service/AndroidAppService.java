/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.service;

import com.sv.dcc.master.model.Appointment;
import com.sv.dcc.master.model.AppointmentDetail;
import com.sv.dcc.master.model.Doctor;
import com.sv.dcc.master.model.DoctorAndLocation;
import com.sv.dcc.master.model.MaxAppointmentAndRuningNo;
import com.sv.dcc.master.repository.AppointmentDetailRepository;
import com.sv.dcc.master.repository.AppointmentRepository;
import com.sv.dcc.master.repository.ClientRepository;
import com.sv.dcc.master.repository.DoctorRepository;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kalum
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AndroidAppService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppointmentDetailRepository appointmentDetailRepository;

    public List<DoctorAndLocation> findByUserNameAndPassword(String userName, String password, String curDate) {
        List<DoctorAndLocation> doctorAndLocations = new ArrayList<>();

        Doctor doctor = doctorRepository.findByUserNameAndPassword(userName, password);
        if (doctor != null) {

            List<Object[]> list = doctorRepository.findLocationListByDoctorAndDate(doctor.getIndexNo(), curDate);
            for (int i = 0; i < list.size(); i++) {

                DoctorAndLocation model = new DoctorAndLocation();
                model.setLocationIndexNo((Integer) list.get(i)[0]);
                model.setLocation((String) list.get(i)[1]);
                model.setDoctorIndexNo((Integer) list.get(i)[2]);
                model.setDoctor((String) list.get(i)[3]);

                doctorAndLocations.add(model);
            }
        }
        return doctorAndLocations;
    }

    public MaxAppointmentAndRuningNo getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(String date, Integer doctor, Integer location) {
//        List<MaxAppointmentAndRuningNo> noList = new ArrayList<>();

        List<Object[]> list = appointmentRepository.getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(date, doctor, location);
        MaxAppointmentAndRuningNo model = new MaxAppointmentAndRuningNo();

        for (int i = 0; i < list.size(); i++) {

            model.setMaxAppointmentNo(((BigInteger) list.get(i)[0]).intValue());
            model.setRunningNo(((BigInteger) list.get(i)[1]).intValue());

//            noList.add(model);
        }

        return model;
    }

    @Transactional
    public Integer saveAppointmentDetailByDateDoctorAndLocation(String date, Integer doctor, Integer location) {

        //get appointment
        Appointment appointment = new Appointment();

        List<Appointment> appList = appointmentRepository.getAppoinmentByDateDoctorAndLocation(date, doctor, location);
        if (!appList.isEmpty()) {
            appointment = appList.get(0);
        }

        AppointmentDetail appointmentDetail = appointmentDetailRepository.findMinAppointmentDetailByNotAllocate(appointment.getIndexNo());
        
        appointmentDetail.setAppointment(appointment.getIndexNo());
        appointmentDetail.setStatus("PENDING");
        appointmentDetail.setType("ONFOOT");
        appointmentDetail.setClient(clientRepository.findByNameAndNic("ONFOOT", "Default").getIndexNo());
        appointmentDetail.setAllocate("ALLOCATE");
        
        
        Integer maxNo = appointmentDetailRepository.findMaxAppointmentNo(appointmentDetail.getAppointment());

        AppointmentDetail appointmentDetail1 = new AppointmentDetail();
        appointmentDetail1.setAppointmentNo(maxNo + 1);
        appointmentDetail1.setAppointment(appointmentDetail.getAppointment());
        appointmentDetail1.setClient(1);
        appointmentDetail1.setAllocate("NOTALLOCATE");
        
        appointmentDetailRepository.save(appointmentDetail1);
        
//        Integer maxNo = appointmentDetailRepository.findMaxAppointmentNo(appointment.getIndexNo());
//        System.out.println("+++++++++++++++++++++++");
//        System.out.println(maxNo);
//        System.out.println("+++++++++++++++++++++++");
//        if (maxNo == null) {
//            appointmentDetail.setAppointmentNo(1);
//        } else if (maxNo == 0) {
//            appointmentDetail.setAppointmentNo(1);
//        } else {
//            appointmentDetail.setAppointmentNo(maxNo + 1);
//        }

        return appointmentDetailRepository.save(appointmentDetail).getAppointmentNo();
    }

    public Integer onlineClientGetAppointmentChit(String date, Integer doctor, Integer location, String code) {
        return appointmentDetailRepository.onlineClientGetAppointmentChit(date, doctor, location, code);
    }
}
