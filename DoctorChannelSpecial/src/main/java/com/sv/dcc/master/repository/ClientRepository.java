/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dcc.master.repository;

import com.sv.dcc.master.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kalum
 */
public interface ClientRepository extends JpaRepository<Client, Integer>{

    public Client findByNameAndNic(String onfoot, String aDefault);

    public Client findByMobile(String mobile);
    
}
