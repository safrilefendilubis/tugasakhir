package com.juaracoding.DBLaundry.controller;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/7/2023 3:39 PM
@Last Modified 3/7/2023 3:39 PM
Version 1.1
*/

import com.juaracoding.DBLaundry.dto.UserDTO;
import com.juaracoding.DBLaundry.handler.FormatValidation;
import com.juaracoding.DBLaundry.model.Users;
import com.juaracoding.DBLaundry.service.UserService;
import com.juaracoding.DBLaundry.utils.ConstantMessage;
import com.juaracoding.DBLaundry.utils.MappingAttribute;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/usc")
public class UserController {

    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private Map<String, Object> objectMapper = new HashMap<String, Object>();

    private List<Users> lsCPUpload = new ArrayList<Users>();

    private String[] strExceptionArr = new String[2];

    private MappingAttribute mappingAttribute = new MappingAttribute();

    @Autowired
    public UserController(ModelMapper modelMapper) {
        strExceptionArr[0] = "UserController";
        this.modelMapper = modelMapper;
    }

    @PostMapping("/v1/register")
    public String doRegis(@ModelAttribute("usr")
                          @Valid UserDTO users
                          , BindingResult bindingResult
                          , Model model
                          , WebRequest request
    ) {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("usr",users);
            return "usc_register";
        }
        Boolean isValid = true;
        if(!FormatValidation.phoneNumberFormatValidation(users.getNoHP(),null))
        {
            isValid = false;
            mappingAttribute.setErrorMessage(bindingResult, ConstantMessage.ERROR_PHONE_NUMBER_FORMAT_INVALID);
        }

        if(!FormatValidation.emailFormatValidation(users.getEmail(),null))
        {
            isValid = false;
            mappingAttribute.setErrorMessage(bindingResult, ConstantMessage.ERROR_EMAIL_FORMAT_INVALID);
        }
        if(!isValid)
        {
            model.addAttribute("users",users);
            return "usc_register";
        }

        Users userz = modelMapper.map(users, new TypeToken<Users>() {}.getType());
        objectMapper = userService.checkRegis(userz,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
//            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("verifyEmail",users.getEmail());
            model.addAttribute("users",new Users());

            return "usc_verifikasi";
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("users",users);
            return "usc_register";
        }
    }
}
