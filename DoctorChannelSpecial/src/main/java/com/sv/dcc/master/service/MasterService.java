/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.service;

import com.sv.dcc.master.model.Appointment;
import com.sv.dcc.master.model.AppointmentAndClient;
import com.sv.dcc.master.model.AppointmentDetail;
import com.sv.dcc.master.model.Category;
import com.sv.dcc.master.model.Client;
import com.sv.dcc.master.model.Doctor;
import com.sv.dcc.master.model.Location;
import com.sv.dcc.master.model.Verification;
import com.sv.dcc.master.model.pojo.DoctorDetails;
import com.sv.dcc.master.model.pojo.SkipAndNextAppointmentDetail;
import com.sv.dcc.master.repository.AppointmentDetailRepository;
import com.sv.dcc.master.repository.AppointmentRepository;
import com.sv.dcc.master.repository.CategoryRepository;
import com.sv.dcc.master.repository.ClientRepository;
import com.sv.dcc.master.repository.DoctorRepository;
import com.sv.dcc.master.repository.LocationRepository;
import com.sv.dcc.master.repository.VerificationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kalum
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MasterService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentDetailRepository appointmentDetailRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private VerificationRepository verificationRepository;

    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Appointment getAppoinmentByDateDoctorAndLocation(String date, Integer doctor, Integer location) {
        List<Appointment> appList = appointmentRepository.getAppoinmentByDateDoctorAndLocation(date, doctor, location);
        if (!appList.isEmpty()) {
            return appList.get(0);
        }
        return null;
    }
    
    @Transactional
    public Appointment saveAppointment(Appointment appointment) {
        
        if (appointment.getActiveStatus().equalsIgnoreCase("ACTIVE")) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Integer maxNo = appointmentDetailRepository.findMaxAppointmentNo(appointment.getIndexNo());

            

            for (int i = 1; i < 11; i++) {
                AppointmentDetail appointmentDetail = new AppointmentDetail();
                appointmentDetail.setAppointmentNo(maxNo + i);
                appointmentDetail.setAppointment(appointment.getIndexNo());
                appointmentDetail.setClient(1);
                appointmentDetail.setAllocate("NOTALLOCATE");
                appointmentDetailRepository.save(appointmentDetail);
                
            }
        }
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public AppointmentDetail saveAppointmentDetail(AppointmentAndClient appointmentAndClient) {
        AppointmentDetail appointmentDetail = appointmentAndClient.getAppointmentDetail();
        Client client = appointmentAndClient.getClient();

        Integer maxNo = appointmentDetailRepository.findMaxAppointmentNo(appointmentDetail.getAppointment());

        AppointmentDetail appointmentDetail1 = new AppointmentDetail();
        appointmentDetail1.setAppointmentNo(maxNo + 1);
        appointmentDetail1.setAppointment(appointmentDetail.getAppointment());
        appointmentDetail1.setClient(1);
        appointmentDetail1.setAllocate("NOTALLOCATE");

        appointmentDetailRepository.save(appointmentDetail1);

        if (appointmentAndClient.getAppointmentDetail().getIndexNo() == null) {

            if (client.getIndexNo() == null) {
                Client client1 = clientRepository.save(client);
                appointmentDetail.setClient(client1.getIndexNo());
            } else {
                appointmentDetail.setClient(client.getIndexNo());
            }
            AppointmentDetail model = appointmentDetailRepository.findMinAppointmentDetailByNotAllocate(appointmentDetail.getAppointment());

            model.setAppointmentCode(appointmentDetail.getAppointmentCode());
            model.setStatus(appointmentDetail.getStatus());
            model.setType(appointmentDetail.getType());
            model.setClient(appointmentDetail.getClient());
            model.setAllocate("ALLOCATE");

            return appointmentDetailRepository.save(model);

        } else {

            if (client.getIndexNo() == null) {
                Client client1 = clientRepository.save(client);
                appointmentDetail.setClient(client1.getIndexNo());
            } else {
                appointmentDetail.setClient(client.getIndexNo());
            }
            appointmentDetail.setAllocate("ALLOCATE");
            return appointmentDetailRepository.save(appointmentDetail);
        }

//        if (maxNo == null) {
//            maxNo = 0;
//        }
//        appointmentDetail.setAppointmentNo(maxNo + 1);
    }

    @Transactional
    public Verification sendSMSClientConfirmation(Integer location, Client client) {

        Location locationModel = locationRepository.findByIndexNo(location);

        String message = "";

        Random random = new Random();
        String randomNo = String.format("%04d", random.nextInt(10000));

        message = "Mr/Mrs " + client.getName() + "\n"
                + "Enter " + randomNo + " to confirm your mobile number " + "\n"
                + "For any clarification please contact us on " + locationModel.getMobile() + "";

        final String uri = "http://smsserver.svisiontec.com/send_sms.php?api_key=5494031051&number=" + client.getMobile() + "&message=" + message;
        RestTemplate restTemplate = new RestTemplate();
        String result = "";

        if (client.getMobile() == null) {
            System.out.println("CLIENT CONTACT NUMBER NOT FOUND!");
        } else {
            result = restTemplate.getForObject(uri, String.class);
        }
        if ("0".equals(result)) {
            System.out.println("MOBILE NO : " + client.getMobile() + " CONFIRMATION SMS SEND!");
            Verification verification = new Verification();

            if (client.getIndexNo() == null) {
                Client client1 = clientRepository.save(client);
                verification.setClient(client1.getIndexNo());
            } else {
                verification.setClient(client.getIndexNo());
            }

            verification.setCode(randomNo);
            return verificationRepository.save(verification);
        } else {
            System.out.println("SMS NOT SEND");
            return null;
        }
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Client getClientByMobile(String mobile) {
        return clientRepository.findByMobile(mobile);
    }

    public AppointmentDetail findRuningAppointmentDetailAppointment(Integer appointmentIndex) {
        return appointmentDetailRepository.findStartAppointmentDetailAppointment(appointmentIndex);
    }

    @Transactional
    public SkipAndNextAppointmentDetail skipAndNextChannel(Integer appointmentIndex, AppointmentDetail appointmentDetail) {
        SkipAndNextAppointmentDetail skipAndNextAppointmentDetail = new SkipAndNextAppointmentDetail();

        appointmentDetail.setStatus("SKIP");
        skipAndNextAppointmentDetail.setSkipAppointmentDetail(appointmentDetailRepository.save(appointmentDetail));

        AppointmentDetail appointmentDetailModel = appointmentDetailRepository.findRuningAppointmentDetailAppointment(appointmentIndex);
        appointmentDetailModel.setStatus("RUNNING");
        skipAndNextAppointmentDetail.setNextAppointmentDetail(appointmentDetailRepository.save(appointmentDetailModel));

        return skipAndNextAppointmentDetail;
    }

    @Transactional
    public SkipAndNextAppointmentDetail cancelAndNextChannel(Integer appointmentIndex, AppointmentDetail appointmentDetail) {
        SkipAndNextAppointmentDetail skipAndNextAppointmentDetail = new SkipAndNextAppointmentDetail();

        appointmentDetail.setStatus("CANCEL");
        skipAndNextAppointmentDetail.setCanselAppointmentDetail(appointmentDetailRepository.save(appointmentDetail));

        AppointmentDetail appointmentDetailModel = appointmentDetailRepository.findRuningAppointmentDetailAppointment(appointmentIndex);
        appointmentDetailModel.setStatus("RUNNING");
        skipAndNextAppointmentDetail.setNextAppointmentDetail(appointmentDetailRepository.save(appointmentDetailModel));

        return skipAndNextAppointmentDetail;
    }

    @Transactional
    public AppointmentDetail nextAndDoneChannels(Integer appointmentIndex, AppointmentDetail appointmentDetail) {
        if (appointmentDetail.getIndexNo() == null) {
            AppointmentDetail appointmentDetailModel = appointmentDetailRepository.minPendingAppointmentDetail(appointmentIndex);
            appointmentDetailModel.setStatus("RUNNING");
//            if (appointmentDetailModel.getAppointmentCode() == null) {
//                appointmentDetailModel.setAppointmentCode("0000");
//            }
            return appointmentDetailRepository.save(appointmentDetailModel);
        } else {
            appointmentDetail.setStatus("DONE");
            appointmentDetailRepository.save(appointmentDetail);
            AppointmentDetail appointmentDetailModel = appointmentDetailRepository.findRuningAppointmentDetailAppointment(appointmentIndex);
            if (appointmentDetailModel == null) {
                appointmentDetail.setStatus("RUNNING");
                return appointmentDetailRepository.save(appointmentDetail);

            }
            appointmentDetailModel.setStatus("RUNNING");
            return appointmentDetailRepository.save(appointmentDetailModel);
        }
    }

    public List<AppointmentDetail> findAllSkipAppointment(String date, Integer doctor, Integer location) {
        String status = "SKIP";
        return appointmentDetailRepository.findAllSkipAppointment(date, doctor, location, status);
    }

    public List<AppointmentDetail> findAllCancelAppointment(String date, Integer doctor, Integer location) {
        String status = "CANCEL";
        return appointmentDetailRepository.findAllCancelAppointment(date, doctor, location, status);
    }

//    public AppointmentDetail findSkipAppointmentOrderByFristIn(Integer appointmentIndex) {
//        String status = "SKIP";
//        return appointmentDetailRepository.findSkipAppointmentOrderByFristIn(appointmentIndex,status);
//    }
    @Transactional
    public AppointmentDetail backAndPendingChannels(Integer appointmentIndex, AppointmentDetail appointmentDetail) {
        appointmentDetail.setStatus("PENDING");
        appointmentDetailRepository.save(appointmentDetail);
        AppointmentDetail appointmentDetailModel = appointmentDetailRepository.findMaxDoneAppointmentDetail(appointmentIndex);
        appointmentDetailModel.setStatus("RUNNING");
        System.out.println(appointmentDetailModel);
        return appointmentDetailRepository.save(appointmentDetailModel);
    }

    public void doneThisChannels(AppointmentDetail appointmentDetail) {
        System.out.println(appointmentDetail);
        appointmentDetail.setStatus("DONE");
        appointmentDetailRepository.save(appointmentDetail);
    }

    public Object appointmentCount(String date, Integer doctor, Integer location) {
        return appointmentDetailRepository.appointmentCount(date, doctor, location);
    }

    public List<DoctorDetails> getAppointmentDetailsByDoctorAndDate(String date, Integer doctor) {
        List<Object[]> list = appointmentRepository.getAppointmentDetailsByDoctorAndDate(date, doctor);

        List<DoctorDetails> detailList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DoctorDetails detail = new DoctorDetails();
            detail.setName((String) list.get(i)[0]);
            detail.setCategory((String) list.get(i)[1]);
//            detail.setLocation((String) list.get(i)[2]);
            detailList.add(detail);
        }
        return detailList;
    }

    public List<AppointmentDetail> getNotAllocateList(String date, Integer doctor, Integer location) {
        return appointmentDetailRepository.getNotAllocateList(date, doctor, location);
    }

}
