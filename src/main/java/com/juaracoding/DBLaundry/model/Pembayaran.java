package com.juaracoding.DBLaundry.model;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/3/2023 4:54 PM
@Last Modified 3/3/2023 4:54 PM
Version 1.1
*/
import javax.persistence.*;

@Entity
@Table(name = "MstPembayaran")
public class Pembayaran {

    @Id
    @Column(name = "IDPembayaran",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte idPembayaran;

    @Column(name = "Pembayaran")
    private String Pembayaran;

    public byte getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(byte idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getPembayaran() {
        return Pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        Pembayaran = pembayaran;
    }
}
