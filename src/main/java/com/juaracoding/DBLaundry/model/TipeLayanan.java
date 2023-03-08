package com.juaracoding.DBLaundry.model;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 03/03/2023 19:50
@Last Modified 03/03/2023 19:50
Version 1.1
*/

import javax.persistence.*;

@Entity
@Table(name = "MstTipeLayanan")
public class TipeLayanan {

    @Id
    @Column(name = "IDTipe",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte idTipe;

    @Column(name = "NamaTipe")
    private String namaTipe;

    public byte getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(byte idTipe) {
        this.idTipe = idTipe;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }
}