package com.sv.dcc.security.user;

import com.sv.dcc.security.user.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Thilina Kalum
 */
public interface UserRepository extends JpaRepository<Doctor, Integer>{

//    public User findByUserNameAndPassword(String userName, String password);
    public Doctor findByUserName(String userName);
    
}
