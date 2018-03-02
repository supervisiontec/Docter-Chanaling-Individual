/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.repository;

import com.sv.dcc.master.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kalum
 */
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findByUserNameAndPassword(String userName, String password);

    @Query(value = "select \n"
            + "location.index_no as location_index,\n"
            + "location.name as location_name,\n"
            + "doctor.index_no as doctor_index,\n"
            + "doctor.name as doctor_name\n"
            + "from appointment\n"
            + "left join location on location.index_no = appointment.location\n"
            + "left join doctor on doctor.index_no=appointment.doctor\n"
            + "WHERE appointment.date = :curDate \n"
            + "and appointment.doctor = :doctor ", nativeQuery = true)
    public List<Object[]> findLocationListByDoctorAndDate(@Param("doctor") Integer doctor, @Param("curDate") String curDate);

}
