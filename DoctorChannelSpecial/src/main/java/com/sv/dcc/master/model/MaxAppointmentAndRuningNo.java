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
public class MaxAppointmentAndRuningNo implements Serializable{
    
    private Integer maxAppointmentNo;
    private Integer runningNo;

    public MaxAppointmentAndRuningNo() {
    }

    public Integer getMaxAppointmentNo() {
        return maxAppointmentNo;
    }

    public void setMaxAppointmentNo(Integer maxAppointmentNo) {
        this.maxAppointmentNo = maxAppointmentNo;
    }

    public Integer getRunningNo() {
        return runningNo;
    }

    public void setRunningNo(Integer runningNo) {
        this.runningNo = runningNo;
    }
    
}
