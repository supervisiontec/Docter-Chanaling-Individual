/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.model;

import java.io.Serializable;

/**
 *
 * @author Thilina Kalum
 */
public class DoctorAndLocation implements Serializable{
    
    private Integer LocationIndexNo;
    private String location;
    private Integer doctorIndexNo;
    private String doctor;

    public DoctorAndLocation() {
    }

    public Integer getLocationIndexNo() {
        return LocationIndexNo;
    }

    public void setLocationIndexNo(Integer LocationIndexNo) {
        this.LocationIndexNo = LocationIndexNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDoctorIndexNo() {
        return doctorIndexNo;
    }

    public void setDoctorIndexNo(Integer doctorIndexNo) {
        this.doctorIndexNo = doctorIndexNo;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
