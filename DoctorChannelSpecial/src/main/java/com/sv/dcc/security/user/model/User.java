package com.sv.dcc.security.user.model;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sv.mobile.security.user.model;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// *
// * @author kalum
// */
//@Entity(name = "com.sv.mobile.security.user.model.User")
//@Table(name = "m_login_details")
//public class User implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "index_no")
//    private Integer indexNo;
//
//    @Basic(optional = false)
//    @Column(name = "lgno")
//    private int lgno;
//
//    @Basic(optional = false)
//    @Column(name = "name")
//    private String name;
//
//    @Basic(optional = false)
//    @Column(name = "user_name")
//    private String userName;
//
//    @Basic(optional = false)
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "active")
//    private String active;
//    
//    @Column(name = "type")
//    private String type;
//    
//    @Column(name = "all_branch")
//    private Integer allBranch;
//
//    @Column(name = "branch")
//    private Integer branch;
//
//    public User() {
//    }
//
//    public User(Integer indexNo) {
//        this.indexNo = indexNo;
//    }
//
//    public Integer getIndexNo() {
//        return indexNo;
//    }
//
//    public void setIndexNo(Integer indexNo) {
//        this.indexNo = indexNo;
//    }
//
//    public int getLgno() {
//        return lgno;
//    }
//
//    public void setLgno(int lgno) {
//        this.lgno = lgno;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getActive() {
//        return active;
//    }
//
//    public void setActive(String active) {
//        this.active = active;
//    }
//
//    public Integer getBranch() {
//        return branch;
//    }
//
//    public void setBranch(Integer branch) {
//        this.branch = branch;
//    }
//
//    public Integer getAllBranch() {
//        return allBranch;
//    }
//
//    public void setAllBranch(Integer allBranch) {
//        this.allBranch = allBranch;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//    
//    
//}
