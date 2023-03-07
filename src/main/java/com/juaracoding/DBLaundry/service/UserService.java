package com.juaracoding.DBLaundry.service;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 05/03/2023 2:27
@Last Modified 05/03/2023 2:27
Version 1.1
*/

import com.juaracoding.DBLaundry.Repo.UserRepo;
import com.juaracoding.DBLaundry.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    private String [] strExceptionArr = new String[2];

    @Autowired
    public UserService(UserRepo userRepo) {
        this.strExceptionArr[0] = "UserService";
        this.userRepo = userRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUser(Users user){
        userRepo.save(user);
    }

    public List<Users> findAllUsers(){
        return userRepo.findAll();
    }

}
