package com.juaracoding.DBLaundry.service;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 09/03/2023 13:00
@Last Modified 09/03/2023 13:00
Version 1.1
*/

import com.juaracoding.DBLaundry.model.Pengeluaran;
import com.juaracoding.DBLaundry.repo.PengeluaranRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PengeluaranService {

    private PengeluaranRepo pengeluaranRepo;

    public PengeluaranService(PengeluaranRepo pengeluaranRepo) {
        this.pengeluaranRepo = pengeluaranRepo;
    }

    public List<Pengeluaran> getAllPengeluaran() {
        return pengeluaranRepo.findAll();
    }

    public Pengeluaran savePengeluaran(Pengeluaran pengeluaran) {
        return pengeluaranRepo.save(pengeluaran);
    }

    public Optional<Pengeluaran> getPengeluaranById(Long id,String x) {
//		return studentRepository.findById(id).get();
        return pengeluaranRepo.findById(id);
    }

    public Pengeluaran getPengeluaranById(Long id) {
        return pengeluaranRepo.findById(id).get();
//		return studentRepository.findById(id);
    }

    public Pengeluaran updatePengeluaran(Pengeluaran pengeluaran) {
        return pengeluaranRepo.save(pengeluaran);
    }

    public void deletePengeluaranById(Long id) {
        pengeluaranRepo.deleteById(id);
    }

}

