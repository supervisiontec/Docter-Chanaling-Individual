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
public class AppointmentAndClient implements Serializable{
    
    private AppointmentDetail appointmentDetail;
    private Client client;

    public AppointmentDetail getAppointmentDetail() {
        return appointmentDetail;
    }

    public void setAppointmentDetail(AppointmentDetail appointmentDetail) {
        this.appointmentDetail = appointmentDetail;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "AppointmentAndClient{" + "appointmentDetail=" + appointmentDetail + ", client=" + client + '}';
    }

}
