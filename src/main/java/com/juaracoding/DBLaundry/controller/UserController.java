package com.juaracoding.DBLaundry.controller;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 05/03/2023 2:22
@Last Modified 05/03/2023 2:22
Version 1.1
*/

import com.juaracoding.DBLaundry.handler.ResponseHandler;
import com.juaracoding.DBLaundry.model.Users;
import com.juaracoding.DBLaundry.service.UserService;
import com.juaracoding.DBLaundry.utils.ConstantMessage;
import io.swagger.annotations.Authorization;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/usr/")
public class UserController {

    private UserService userService;
    private String [] strException = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService) {
        strException[0] = "UserController";
        this.userService = userService;
    }

    @PostMapping("v1/s")
    public ResponseEntity<Object> saveUser(@Valid
                                           @RequestBody Users user){
        userService.saveUser(user);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.OK,
                null,
                null,
                null);
    }

    @GetMapping("v1/f")
    public ResponseEntity<Object> findAll() {
        List<Users> ls = userService.findAllUsers();

        if (ls.size() == 0) {
            return new ResponseHandler().generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                    HttpStatus.NOT_FOUND,
                    null,
                    null,
                    null);
        }

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND,
                HttpStatus.OK,
                ls,
                null,
                null);
    }
}
