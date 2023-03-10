package com.juaracoding.DBLaundry.controller;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 09/03/2023 12:57
@Last Modified 09/03/2023 12:57
Version 1.1
*/

import com.juaracoding.DBLaundry.configuration.OtherConfig;
import com.juaracoding.DBLaundry.model.Pengeluaran;
import com.juaracoding.DBLaundry.service.PengeluaranService;
import com.juaracoding.DBLaundry.utils.MappingAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/")
public class PengeluaranController {

    private PengeluaranService pengeluaranService;

    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    @Autowired
    public PengeluaranController(PengeluaranService pengeluaranService) {
        this.pengeluaranService = pengeluaranService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/v1/pengeluaran")
    public String listPengeluaran(Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("pengeluaran", pengeluaranService.getAllPengeluaran());
        return "pengeluaran";
    }

    @GetMapping("/v1/pengeluaran/new")
    public String createStudentForm(Model model,WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        // create student object to hold student form data
        Pengeluaran pengeluaran = new Pengeluaran();
        model.addAttribute("pengeluaran", pengeluaran);
        return "create_pengeluaran";

    }

    @PostMapping("/v1/pengeluaran")
    public String saveStudent(@ModelAttribute("pengeluaran") Pengeluaran pengeluaran,Model model,WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        pengeluaranService.savePengeluaran(pengeluaran);
        return "redirect:/api/v1/pengeluaran";
    }

    @GetMapping("/v1/pengeluaran/edit/{id}")
    public String editPengeluaranForm(@PathVariable("id") Long Id, Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("student", pengeluaranService.getPengeluaranById(Id));
        return "edit_pengeluaran";
    }

    @PostMapping("/v1/pengeluaran/{id}")
    public String updatePengeluaran(@PathVariable("id") Long id,
                                    @ModelAttribute("Pengeluaran") Pengeluaran pengeluaran,
                                    Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        // get pengeluaran from database by id
        Pengeluaran existingPengeluaran = pengeluaranService.getPengeluaranById(id);
        existingPengeluaran.setIdPengeluaran(id);
        existingPengeluaran.setNamaPengeluaran(pengeluaran.getNamaPengeluaran());
        existingPengeluaran.setBiaya(pengeluaran.getBiaya());

        // save updated pengeluaran object
        pengeluaranService.updatePengeluaran(existingPengeluaran);
        return "redirect:/api/v1/pengeluaran";
    }

    // handler method to handle delete student request

    @GetMapping("/v1/pengeluaran/{id}")
    public String deletePengeluaran(@PathVariable Long id, Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        pengeluaranService.deletePengeluaranById(id);
        return "redirect:/api/v1/pengeluaran/";
    }

}