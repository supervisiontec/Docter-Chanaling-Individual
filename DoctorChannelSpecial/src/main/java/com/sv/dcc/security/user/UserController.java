/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.security.user;

import com.sv.dcc.security.user.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Thilina Kalum
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/doctor-channel/mobile/security")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login/{userName}", method = RequestMethod.GET)
    public Doctor getUser(@PathVariable String userName) {
        return userService.findByName(userName);
    }

}
