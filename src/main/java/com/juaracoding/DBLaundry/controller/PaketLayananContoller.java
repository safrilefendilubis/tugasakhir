package com.juaracoding.DBLaundry.controller;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 12/03/2023 13:27
@Last Modified 12/03/2023 13:27
Version 1.1
*/

import com.juaracoding.DBLaundry.configuration.OtherConfig;
import com.juaracoding.DBLaundry.dto.PaketLayananDTO;
import com.juaracoding.DBLaundry.model.PaketLayanan;
import com.juaracoding.DBLaundry.service.PaketLayananService;
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
public class PaketLayananContoller {

    private PaketLayananService paketLayananService;
    @Autowired
    private ModelMapper modelMapper;
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private Map<String,String> mapSorting = new HashMap<String,String>();
    private List<PaketLayanan> lsCPUpload = new ArrayList<PaketLayanan>();
    private String [] strExceptionArr = new String[2];
    private MappingAttribute mappingAttribute = new MappingAttribute();

    @Autowired
    public PaketLayananContoller(PaketLayananService paketLayananService) {
        strExceptionArr[0] = "PaketLayananController";
        mapSorting();
        this.paketLayananService = paketLayananService;
    }

    private void mapSorting()
    {
        mapSorting.put("id","ID PAKET LAYANAN");
        mapSorting.put("nama","NAMA PAKET LAYANAN");
        mapSorting.put("harga","HARGA PER KILO");
        mapSorting.put("tipe","TIPE LAYANAN");
    }

    @GetMapping("/v1/paketlayanan/new")
    public String createPaketLayanan(Model model, WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("paketLayanan", new PaketLayananDTO());
        return "paket/create_paketlayanan";
    }

    @GetMapping("/v1/paketlayanan/edit/{id}")
    public String editPaketLayanan(Model model, WebRequest request, @PathVariable("id") Long id)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        objectMapper = paketLayananService.findById(id,request);
        PaketLayananDTO paketLayananDTO = (objectMapper.get("data")==null?null:(PaketLayananDTO) objectMapper.get("data"));
        if((Boolean) objectMapper.get("success"))
        {
            PaketLayananDTO paketLayananDTOForSelect = (PaketLayananDTO) objectMapper.get("data");
            model.addAttribute("paketLayanan", paketLayananDTO);
            return "paket/edit_paketlayanan";
        }
        else
        {
            model.addAttribute("paketLayanan", new PaketLayananDTO());
            return "redirect:/api/mgmnt/v1/paketlayanan/default";
        }
    }

    @PostMapping("/v1/paketlayanan/new")
    public String newPaketLayanan(@ModelAttribute(value = "paketLayanan")
                            @Valid PaketLayananDTO paketLayananDTO
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
            model.addAttribute("paketLayanan",paketLayananDTO);
            model.addAttribute("status","error");

            return "paket/create_paketlayanan";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("paketLayanan",paketLayananDTO);
            return "paket/create_paketlayanan";
        }
        /* END OF VALIDATION */

        PaketLayanan paketLayanan = modelMapper.map(paketLayananDTO, new TypeToken<PaketLayanan>() {}.getType());
        objectMapper = paketLayananService.savePaketLayanan(paketLayanan,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("message","DATA BERHASIL DISIMPAN");
            Long idDataSave = objectMapper.get("idDataSave")==null?1:Long.parseLong(objectMapper.get("idDataSave").toString());
            return "redirect:/api/mgmnt/v1/paketlayanan/fbpsb/0/asc/idListHarga?columnFirst=id&valueFirst="+idDataSave+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("paketLayanan",new PaketLayananDTO());
            model.addAttribute("status","error");
            return "paket/create_paketlayanan";
        }
    }

    @PostMapping("/v1/paketlayanan/edit/{id}")
    public String editPaketLayanan(@ModelAttribute("paketLayanan")
                             @Valid PaketLayananDTO paketLayananDTO
            , BindingResult bindingResult
            , Model model
            , WebRequest request
            , @PathVariable("id") Long id
    )
    {
        /* START VALIDATION */
        if(bindingResult.hasErrors())
        {
            model.addAttribute("paketLayanan",paketLayananDTO);
            return "paket/edit_paketlayanan";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("paketLayanan",paketLayananDTO);
            return "paket/edit_paketlayanan";
        }
        /* END OF VALIDATION */

        PaketLayanan paketLayanan = modelMapper.map(paketLayananDTO, new TypeToken<PaketLayanan>() {}.getType());
        objectMapper = paketLayananService.updatePaketLayanan(id,paketLayanan,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("paketLayanan",new PaketLayananDTO());
            return "redirect:/api/mgmnt/v1/paketlayanan/fbpsb/0/asc/idListHarga?columnFirst=id&valueFirst="+id+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("paketLayanan",new PaketLayananDTO());
            return "paket/edit_paketlayanan";
        }
    }

    @GetMapping("/v1/paketlayanan/default")
    public String getDefaultData(Model model,WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        Pageable pageable = PageRequest.of(0,5, Sort.by("idListHarga"));
        objectMapper = paketLayananService.findAllPaketLayanan(pageable,request);
        mappingAttribute.setAttribute(model,objectMapper,request);

        model.addAttribute("paketLayanan",new PaketLayananDTO());
        model.addAttribute("sortBy","idListHarga");
        model.addAttribute("currentPage",1);
        model.addAttribute("asc","asc");
        model.addAttribute("columnFirst","");
        model.addAttribute("valueFirst","");
        model.addAttribute("sizeComponent",5);
        return "paket/paketlayanan";
    }

    @GetMapping("/v1/paketlayanan/fbpsb/{page}/{sort}/{sortby}")
    public String findByPaketLayanan(
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
        sortzBy = sortzBy==null?"idListHarga":sortzBy;
        Pageable pageable = PageRequest.of(pagez==0?pagez:pagez-1,Integer.parseInt(sizeComponent.equals("")?"5":sizeComponent), sortz.equals("asc")?Sort.by(sortzBy):Sort.by(sortzBy).descending());
        objectMapper = paketLayananService.findByPage(pageable,request,columnFirst,valueFirst);
        mappingAttribute.setAttribute(model,objectMapper,request);
        model.addAttribute("paketLayanan",new PaketLayananDTO());
        model.addAttribute("currentPage",pagez==0?1:pagez);
        model.addAttribute("sortBy", ManipulationMap.getKeyFromValue(mapSorting,sortzBy));
        model.addAttribute("columnFirst",columnFirst);
        model.addAttribute("valueFirst",valueFirst);
        model.addAttribute("sizeComponent",sizeComponent);

        return "paket/paketlayanan";
    }

    @GetMapping("/v1/paketlayanan/delete/{id}")
    public String deletePaketLayanan(Model model
            , WebRequest request
            , @PathVariable("id") byte id
    ) {
        if (OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        objectMapper = paketLayananService.deletePaketLayanan((long) id,request);
        if (objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if ((Boolean) objectMapper.get("success")) {
            mappingAttribute.setAttribute(model, objectMapper);
            model.addAttribute("paket", new PaketLayananDTO());
            return "redirect:/api/mgmnt/v1/paketlayanan/fbpsb/0/asc/idListHarga?columnFirst=idListHarga&valueFirst=" + id + "&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        } else {
            mappingAttribute.setAttribute(model, objectMapper);
            model.addAttribute("paket", new PaketLayananDTO());
            return "paket/paketlayanan";
        }
    }
}
