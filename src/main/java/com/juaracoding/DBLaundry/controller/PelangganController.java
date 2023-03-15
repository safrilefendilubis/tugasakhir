package com.juaracoding.DBLaundry.controller;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 12/03/2023 16:08
@Last Modified 12/03/2023 16:08
Version 1.0
*/

import com.juaracoding.DBLaundry.configuration.OtherConfig;
import com.juaracoding.DBLaundry.dto.PelangganDTO;
import com.juaracoding.DBLaundry.model.Pelanggan;
import com.juaracoding.DBLaundry.service.PelangganService;
import com.juaracoding.DBLaundry.utils.ConstantMessage;
import com.juaracoding.DBLaundry.utils.ManipulationMap;
import com.juaracoding.DBLaundry.utils.MappingAttribute;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/mgmnt")
public class PelangganController {

    private PelangganService pelangganService;
    @Autowired
    private ModelMapper modelMapper;
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private Map<String,String> mapSorting = new HashMap<String,String>();
    private List<Pelanggan> lsCPUpload = new ArrayList<Pelanggan>();
    private String [] strExceptionArr = new String[2];
    private MappingAttribute mappingAttribute = new MappingAttribute();

    @Autowired
    public PelangganController(PelangganService pelangganService) {
        strExceptionArr[0] = "PelangganController";
        mapSorting();
        this.pelangganService = pelangganService;
    }

    private void mapSorting()
    {
        mapSorting.put("id","ID PELANGGAN");
        mapSorting.put("nama","NAMA PELANGGAN");
        mapSorting.put("alamat","ALAMAT LENGKAP");
        mapSorting.put("no","NO HANDPHONE");
    }

    @GetMapping("/v1/pelanggan/new" )
    public String createPelanggan(Model model, WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("pelanggan", new PelangganDTO());
        return "pelanggan/create_pelanggan";
    }

    @GetMapping("/v1/pelanggan/edit/{id}")
    public String editPelanggan(Model model, WebRequest request, @PathVariable("id") Long id)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        objectMapper = pelangganService.findById(id,request);
        PelangganDTO pelangganDTO = (objectMapper.get("data")==null?null:(PelangganDTO) objectMapper.get("data"));
        if((Boolean) objectMapper.get("success"))
        {
            PelangganDTO pelangganDTOForSelect = (PelangganDTO) objectMapper.get("data");
            model.addAttribute("pelanggan", pelangganDTO);
            return "pelanggan/edit_pelanggan";
        }
        else
        {
            model.addAttribute("pelanggan", new PelangganDTO());
            return "redirect:/api/mgmnt/v1/pelanggan/default";
        }
    }

    @PostMapping("/v1/pelanggan/new")
    public String newPelanggan(@ModelAttribute(value = "pelanggan")
                            @Valid PelangganDTO pelangganDTO
            , BindingResult bindingResult
            , Model model
            , WebRequest request
    )
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }

        /* START VALIDATION */
        if(bindingResult.hasErrors())
        {
            model.addAttribute("pelanggan",pelangganDTO);
            model.addAttribute("status","error");

            return "pelanggan/create_pelanggan";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("pelanggan",pelangganDTO);
            return "pelanggan/create_pelanggan";
        }
        /* END OF VALIDATION */

        Pelanggan pelanggan = modelMapper.map(pelangganDTO, new TypeToken<Pelanggan>() {}.getType());
        objectMapper = pelangganService.savePelanggan(pelanggan,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("message","DATA BERHASIL DISIMPAN");
            Long idDataSave = objectMapper.get("idDataSave")==null?1:Long.parseLong(objectMapper.get("idDataSave").toString());
            return "redirect:/api/mgmnt/v1/pelanggan/fbpsb/0/asc/idpelanggan?columnFirst=id&valueFirst="+idDataSave+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("pelanggan",new PelangganDTO());
            model.addAttribute("status","error");
            return "pelanggan/create_pelanggan";
        }
    }

    @PostMapping("/v1/pelanggan/edit/{id}")
    public String editPelanggan(@ModelAttribute("pelanggan")
                             @Valid PelangganDTO pelangganDTO
            , BindingResult bindingResult
            , Model model
            , WebRequest request
            , @PathVariable("id") Long id
    )
    {
        /* START VALIDATION */
        if(bindingResult.hasErrors())
        {
            model.addAttribute("pelanggan",pelangganDTO);
            return "pelanggan/edit_pelanggan";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("pelanggan",pelangganDTO);
            return "pelanggan/edit_pelanggan";
        }
        /* END OF VALIDATION */

        Pelanggan pelanggan = modelMapper.map(pelangganDTO, new TypeToken<Pelanggan>() {}.getType());
        objectMapper = pelangganService.updatePelanggan(id,pelanggan,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("pelanggan",new PelangganDTO());
            return "redirect:/api/mgmnt/v1/pelanggan/fbpsb/0/asc/idPelanggan?columnFirst=id&valueFirst="+id+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("pelanggan",new PelangganDTO());
            return "pelanggan/edit_pelanggan";
        }
    }

    @GetMapping("/v1/pelanggan/default")
    public String getDefaultData(Model model,WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        Pageable pageable = PageRequest.of(0,5, Sort.by("idPelanggan"));
        objectMapper = pelangganService.findAllPelanggan(pageable,request);
        mappingAttribute.setAttribute(model,objectMapper,request);

        model.addAttribute("pelanggan",new PelangganDTO());
        model.addAttribute("sortBy","idPelanggan");
        model.addAttribute("currentPage",1);
        model.addAttribute("asc","asc");
        model.addAttribute("columnFirst","");
        model.addAttribute("valueFirst","");
        model.addAttribute("sizeComponent",5);
        return "/pelanggan/pelanggan";
    }

    @GetMapping("/v1/pelanggan/fbpsb/{page}/{sort}/{sortby}")
    public String findByPelanggan(
            Model model,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy,
            @RequestParam String columnFirst,
            @RequestParam String valueFirst,
            @RequestParam String sizeComponent,
            WebRequest request
    ){
        sortzBy = mapSorting.get(sortzBy);
        sortzBy = sortzBy==null?"idPelanggan":sortzBy;
        Pageable pageable = PageRequest.of(pagez==0?pagez:pagez-1,Integer.parseInt(sizeComponent.equals("")?"5":sizeComponent), sortz.equals("asc")?Sort.by(sortzBy):Sort.by(sortzBy).descending());
        objectMapper = pelangganService.findByPage(pageable,request,columnFirst,valueFirst);
        mappingAttribute.setAttribute(model,objectMapper,request);
        model.addAttribute("pelanggan",new PelangganDTO());
        model.addAttribute("currentPage",pagez==0?1:pagez);
        model.addAttribute("sortBy", ManipulationMap.getKeyFromValue(mapSorting,sortzBy));
        model.addAttribute("columnFirst",columnFirst);
        model.addAttribute("valueFirst",valueFirst);
        model.addAttribute("sizeComponent",sizeComponent);

        return "/pelanggan/pelanggan";
    }

    @GetMapping("/v1/pelanggan/delete/{id}")
    public String deletePelanggan(Model model, WebRequest request, @PathVariable("id") Long id)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        objectMapper = pelangganService.deletePelanggan(id,request);
        mappingAttribute.setAttribute(model,objectMapper);//untuk set session
        model.addAttribute("pelanggan", new PelangganDTO());
        return "redirect:/api/mgmnt/v1/pelanggan/default";
    }
}
