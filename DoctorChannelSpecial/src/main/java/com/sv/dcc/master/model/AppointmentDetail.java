/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Thilina Kalum
 */
@Entity
@Table(name = "appointment_detail")
public class AppointmentDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "appointment_no")
    private Integer appointmentNo;

    @Column(name = "appointment_code")
    private String appointmentCode;

    @Size(max = 45)
    @Column(name = "status")
    private String status;

    @Size(max = 45)
    @Column(name = "type")
    private String type;

    @Column(name = "`allocate`")
    private String allocate;

    @Column(name = "appointment")
    private Integer appointment;

    @Column(name = "client")
    private Integer client;

    public AppointmentDetail() {
    }

    public AppointmentDetail(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAppointment() {
        return appointment;
    }

    public void setAppointment(Integer appointment) {
        this.appointment = appointment;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(Integer appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
    }

    public String getAllocate() {
        return allocate;
    }

    public void setAllocate(String allocate) {
        this.allocate = allocate;
    }
    
    
//    @Override
//    public String toString() {
//        return "AppointmentDetail{" + "indexNo=" + indexNo + ", appointmentNo=" + appointmentNo + ", appointmentCode=" + appointmentCode + ", status=" + status + ", type=" + type + ", appointment=" + appointment + ", client=" + client + '}';
//    }

}
