/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.repository;

import com.sv.dcc.master.model.Appointment;
import com.sv.dcc.master.model.pojo.DoctorDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kalum
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "select * \n"
            + "from appointment\n"
            + "where appointment.date = :dates \n"
            + "and appointment.doctor= :doctor \n"
            + "and appointment.location= :location", nativeQuery = true)
    public List<Appointment> getAppoinmentByDateDoctorAndLocation(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location);

    @Query(value = "select ifnull( min(appointment_detail.appointment_no),1) as max_appointment_no,\n"
            + "(select ifnull(max(appointment_detail.appointment_no),0)  running_no\n"
            + "	from appointment\n"
            + "		left join appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + " 	where appointment.date = :dates \n"
            + "		and appointment.doctor = :doctor \n"
            + "		and appointment.location = :location \n"
            + "		and appointment_detail.`status` = 'RUNNING'\n"
            + ") as  running_no\n"
            + "from appointment\n"
            + "left join appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + "where appointment.date = :dates \n"
            + "and appointment.doctor = :doctor \n"
            + "and appointment.location = :location \n"
            + "and appointment_detail.allocate = 'NOTALLOCATE' ", nativeQuery = true)
    public List<Object[]> getMaxAppoinmentNoAndRuningAppointmetNoByDateDoctorAndLocation(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location);

    @Query(value = "select doctor.name as doctor, \n"
            + "category.name as categoty \n"
//            + "location.name as location \n"
            + "from appointment \n"
            + "left join doctor on doctor.index_no = appointment.doctor \n"
            + "left join category on category.index_no = doctor.category \n"
//            + "left join location on location.index_no = appointment.location \n"
            + "where doctor.index_no = :doctor \n"
            + "and appointment.date = :dates ", nativeQuery = true)
    public List<Object[]> getAppointmentDetailsByDoctorAndDate(@Param("dates") String date,@Param("doctor") Integer doctor);

}
