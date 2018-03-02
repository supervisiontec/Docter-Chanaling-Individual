package com.sv.dcc.security.user;

import com.sv.dcc.security.user.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thilina Kalum
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Doctor findByName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
