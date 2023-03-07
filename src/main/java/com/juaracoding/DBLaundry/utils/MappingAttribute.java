package com.juaracoding.DBLaundry.utils;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 2/28/2023 8:16 PM
@Last Modified 2/28/2023 8:16 PM
Version 1.1
*/
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Map;

public class MappingAttribute {

    public void setAttribute(Model model,Map<String,Object> mapz)
    {
        model.addAttribute("message", mapz.get("message"));
        model.addAttribute("status", mapz.get("status"));
        model.addAttribute("data", mapz.get("responseObj")==null?"":mapz.get("responseObj"));
        model.addAttribute("timestamp", new Date());
        model.addAttribute("success",mapz.get("success"));
        if(mapz.get("errorCode") != null)
        {
            model.addAttribute("errorCode",mapz.get("errorCode"));
            model.addAttribute("path",mapz.get("path"));
        }
    }
}
