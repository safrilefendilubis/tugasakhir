package com.juaracoding.DBLaundry.model;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/1/2023 11:21 PM
@Last Modified 3/1/2023 11:21 PM
Version 1.1
*/

import com.juaracoding.DBLaundry.utils.ConstantMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MstPaketLayanan")
public class PaketLayanan {

    @Id
    @Column(name = "IDListHarga",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idListHarga;

    @NotEmpty(message = ConstantMessage.WARNING_NAMA_PAKET_EMPTY)
    @NotBlank(message = ConstantMessage.WARNING_NAMA_PAKET_BLANK)
    @Column(name = "NamaLayanan")
    private String namaLayanan;

    @NotEmpty(message = ConstantMessage.WARNING_HARGA_PAKET_EMPTY)
    @NotNull(message = ConstantMessage.WARNING_HARGA_PAKET_NULL)
    @NotBlank(message = ConstantMessage.WARNING_HARGA_PAKET_BLANK)
    @Column(name = "Harga",nullable = false)
    private Long harga;

    @NotEmpty(message = ConstantMessage.WARNING_TIPE_PAKET_EMPTY)
    @NotNull(message = ConstantMessage.WARNING_TIPE_PAKET_NULL)
    @NotBlank(message = ConstantMessage.WARNING_TIPE_PAKET_BLANK)
    @ManyToOne
    @JoinColumn(name = "IDTipe")
    private TipeLayanan tipeLayanan;


    public Long getIdListHarga() {
        return idListHarga;
    }

    public void setIdListHarga(Long idListHarga) {
        this.idListHarga = idListHarga;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }

    public TipeLayanan getTipeLayanan() {
        return tipeLayanan;
    }

    public void setTipeLayanan(TipeLayanan tipeLayanan) {
        this.tipeLayanan = tipeLayanan;
    }
}