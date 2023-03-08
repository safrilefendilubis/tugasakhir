package com.juaracoding.DBLaundry.controller; /*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/7/2023 6:02 PM
@Last Modified 3/7/2023 6:02 PM
Version 1.1
*/

import cn.apiclub.captcha.Captcha;
import com.juaracoding.DBLaundry.model.Users;
import com.juaracoding.DBLaundry.utils.MappingAttribute;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("laundry")
public class CheckPageController {

    private Map<String, Object> objectMapper = new HashMap<String, Object>();
    private MappingAttribute mappingAttribute = new MappingAttribute();

    @GetMapping("/signin")
    public String pageOne(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/register")
    public String pageTwo(Model model)
    {

        Users user = new Users();
        model.addAttribute("user",user);
        return "login";
    }

    @GetMapping("/dashboard")
    public String pageThree(){
        return "dashboard";
    }
}