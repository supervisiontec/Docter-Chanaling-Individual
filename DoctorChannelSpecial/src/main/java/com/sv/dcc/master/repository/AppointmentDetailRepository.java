/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.repository;

import com.sv.dcc.master.model.AppointmentDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kalum
 */
public interface AppointmentDetailRepository extends JpaRepository<AppointmentDetail, Integer> {

    @Query(value = "select ifnull(max(appointment_detail.appointment_no),0)as appointment\n"
            + "from appointment_detail \n"
            + "left JOIN appointment on appointment.index_no = appointment_detail.appointment\n"
            + "where appointment.index_no = :appointment \n"
            + "LIMIT 1", nativeQuery = true)
    public Integer findMaxAppointmentNo(@Param("appointment") Integer appointment);

    @Query(value = "SELECT appointment_detail.index_no, \n"
            + "appointment_detail.appointment_no as appointment_no, \n"
            + "appointment_detail.`status`, \n"
            + "appointment_detail.appointment_code, \n"
            + "appointment_detail.`type`, \n"
            + "appointment_detail.appointment,\n"
            + "appointment_detail.`client`, \n"
            + "appointment_detail.`allocate` \n"
            + "from appointment_detail \n"
            + "where appointment_detail.appointment = :appointmentIndex \n"
            + "and appointment_detail.`status`= 'RUNNING' \n"
            + "LIMIT 1 ", nativeQuery = true)
    public AppointmentDetail findStartAppointmentDetailAppointment(@Param("appointmentIndex") Integer appointmentIndex);

    @Query(value = "SELECT appointment_detail.index_no, \n"
            + "ifnull(min(appointment_detail.appointment_no),0) as appointment_no, \n"
            + "appointment_detail.`status`, \n"
            + "appointment_detail.appointment_code, \n"
            + "appointment_detail.`type`, \n"
            + "appointment_detail.appointment,\n"
            + "appointment_detail.`client`, \n"
            + "appointment_detail.allocate \n"
            + "from appointment_detail \n"
            + "where appointment_detail.appointment = :appointmentIndex \n"
            + "and appointment_detail.`status`= 'PENDING' \n"
            + "LIMIT 1 ", nativeQuery = true)
    public AppointmentDetail findRuningAppointmentDetailAppointment(@Param("appointmentIndex") Integer appointmentIndex);

    @Query(value = "SELECT appointment_detail.index_no, \n"
            + "ifnull(min(appointment_detail.appointment_no),0) as appointment_no, \n"
            + "appointment_detail.`status`, \n"
            + "appointment_detail.appointment_code, \n"
            + "appointment_detail.`type`, \n"
            + "appointment_detail.appointment,\n"
            + "appointment_detail.`client`, \n"
            + "appointment_detail.allocate \n"
            + "from appointment_detail \n"
            + "where appointment_detail.appointment = :appointmentIndex \n"
            + "and appointment_detail.`status`= 'PENDING' \n"
            + "LIMIT 1 ", nativeQuery = true)
    public AppointmentDetail minPendingAppointmentDetail(@Param("appointmentIndex") Integer appointmentIndex);

    @Query(value = "select appointment_detail.appointment_no\n"
            + "from appointment\n"
            + "left join\n"
            + "appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + "where\n"
            + "appointment.date = :dates \n"
            + "and appointment.doctor= :doctor \n"
            + "and appointment.location= :location \n"
            + "and appointment_detail.appointment_code = :code", nativeQuery = true)
    public Integer onlineClientGetAppointmentChit(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location, @Param("code") String code);

    @Query(value = "select appointment_detail.* \n"
            + "from appointment \n"
            + "left join appointment_detail on appointment_detail.appointment = appointment.index_no \n"
            + "where appointment.date = :dates \n"
            + "and appointment.doctor = :doctor \n"
            + "and appointment.location = :location \n"
            + "and appointment_detail.`status` = :status", nativeQuery = true)
    public List<AppointmentDetail> findAllSkipAppointment(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location, @Param("status") String status);

    @Query(value = "select appointment_detail.* \n"
            + "from appointment \n"
            + "left join appointment_detail on appointment_detail.appointment = appointment.index_no \n"
            + "where appointment.date = :dates \n"
            + "and appointment.doctor = :doctor \n"
            + "and appointment.location = :location \n"
            + "and appointment_detail.`status` = :status", nativeQuery = true)
    public List<AppointmentDetail> findAllCancelAppointment(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location, @Param("status") String status);

//    @Query(value = "select \n"
//            + "appointment_detail.index_no,\n"
//            + "min(appointment_detail.appointment_no) as appointment_no,\n"
//            + "appointment_detail.appointment_code,\n"
//            + "appointment_detail.`status`,\n"
//            + "appointment_detail.`type`,\n"
//            + "appointment_detail.appointment,\n"
//            + "appointment_detail.`client` \n"
//            + "from appointment_detail\n"
//            + "where appointment_detail.`status` = :status \n"
//            + "and appointment_detail.appointment = :appointmentIndex \n"
//            + "LIMIT 1", nativeQuery = true)
//    public AppointmentDetail findSkipAppointmentOrderByFristIn(@Param("appointmentIndex") Integer appointmentIndex, @Param("status") String status);
    @Query(value = "SELECT appointment_detail.index_no, \n"
            + "appointment_detail.appointment_no as appointment_no, \n"
            + "appointment_detail.`status`, \n"
            + "appointment_detail.appointment_code, \n"
            + "appointment_detail.`type`, \n"
            + "appointment_detail.appointment,\n"
            + "appointment_detail.`client`, \n"
            + "appointment_detail.allocate \n"
            + "from appointment_detail \n"
            + "where appointment_detail.appointment = :appointmentIndex \n"
            + "and appointment_detail.`status`= 'DONE' \n"
            + "order by appointment_detail.appointment_no desc\n"
            + "LIMIT 1", nativeQuery = true)
    public AppointmentDetail findMaxDoneAppointmentDetail(@Param("appointmentIndex") Integer appointmentIndex);

    @Query(value = "select count(appointment_detail.index_no) as appCount, \n"
            + "	(select count(appointment_detail.index_no) \n"
            + "		from\n"
            + "		appointment\n"
            + "		left join appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + "		where appointment.date = :dates \n"
            + "		and appointment.location = :location \n"
            + "		and appointment.doctor = :doctor \n"
            + "		and appointment_detail.`status`= 'PENDING' \n"
            + "	)as pendingCount \n"
            + "from\n"
            + "appointment\n"
            + "left join appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + "where appointment.date = :dates \n"
            + "and appointment.location = :location \n"
            + "and appointment.doctor = :doctor \n"
            + "and appointment_detail.allocate = 'ALLOCATE' ", nativeQuery = true)
    public Object appointmentCount(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location);

    @Query(value = "select appointment_detail.* \n"
            + "from appointment \n"
            + "left join appointment_detail on appointment_detail.appointment = appointment.index_no\n"
            + "where appointment.date = :dates \n"
            + "and appointment.doctor = :doctor \n"
            + "and appointment.location = :location \n"
            + "and appointment_detail.allocate = 'NOTALLOCATE' ", nativeQuery = true)
    public List<AppointmentDetail> getNotAllocateList(@Param("dates") String date, @Param("doctor") Integer doctor, @Param("location") Integer location);

    @Query(value = "select appointment_detail.index_no,\n"
            + "min(appointment_detail.appointment_no) as appointment_no,\n"
            + "appointment_detail.appointment_code,\n"
            + "appointment_detail.`status`,\n"
            + "appointment_detail.`type`,\n"
            + "appointment_detail.appointment,\n"
            + "appointment_detail.`client`,\n"
            + "appointment_detail.allocate \n"
            + "from appointment_detail \n"
            + "left JOIN appointment on appointment.index_no = appointment_detail.appointment\n"
            + "where appointment.index_no = :appointment \n"
            + "and appointment_detail.allocate = 'NOTALLOCATE' ", nativeQuery = true)
    public AppointmentDetail findMinAppointmentDetailByNotAllocate(@Param("appointment") Integer appointment);

}
