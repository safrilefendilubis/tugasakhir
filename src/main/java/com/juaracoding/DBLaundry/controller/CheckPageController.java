package com.juaracoding.DBLaundry.controller;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 07/03/2023 13:37
@Last Modified 07/03/2023 13:37
Version 1.1
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("laundry")
public class CheckPageController {

    @GetMapping("/login")
    public String pageOne(){
        return "login";
    }
}
