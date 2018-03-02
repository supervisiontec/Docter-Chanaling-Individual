/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.model.pojo;

/**
 *
 * @author Thilina Kalum
 */
public class DoctorDetails {
    
    private String name;
    private String category;
    private String location;

    public DoctorDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "DoctorDetails{" + "name=" + name + ", category=" + category + ", location=" + location + '}';
    }
    
}
