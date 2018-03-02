/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.model.pojo;

import com.sv.dcc.master.model.AppointmentDetail;

/**
 *
 * @author Thilina Kalum
 */
public class SkipAndNextAppointmentDetail {
    
    private AppointmentDetail skipAppointmentDetail;
    private AppointmentDetail canselAppointmentDetail;
    private AppointmentDetail nextAppointmentDetail;
    private AppointmentDetail backAppointmentDetail;

    public AppointmentDetail getSkipAppointmentDetail() {
        return skipAppointmentDetail;
    }

    public void setSkipAppointmentDetail(AppointmentDetail skipAppointmentDetail) {
        this.skipAppointmentDetail = skipAppointmentDetail;
    }

    public AppointmentDetail getNextAppointmentDetail() {
        return nextAppointmentDetail;
    }

    public void setNextAppointmentDetail(AppointmentDetail nextAppointmentDetail) {
        this.nextAppointmentDetail = nextAppointmentDetail;
    }

    public AppointmentDetail getCanselAppointmentDetail() {
        return canselAppointmentDetail;
    }

    public void setCanselAppointmentDetail(AppointmentDetail canselAppointmentDetail) {
        this.canselAppointmentDetail = canselAppointmentDetail;
    }

    public AppointmentDetail getBackAppointmentDetail() {
        return backAppointmentDetail;
    }

    public void setBackAppointmentDetail(AppointmentDetail backAppointmentDetail) {
        this.backAppointmentDetail = backAppointmentDetail;
    }

}
