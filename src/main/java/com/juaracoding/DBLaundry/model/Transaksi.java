package com.juaracoding.DBLaundry.model;
/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 01/03/2023 22:27
@Last Modified 01/03/2023 22:27
Version 1.1
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="MstTransaksi")
public class Transaksi {
@Id
@Column(name = "IDTransaksi")
private Long idTransaksi;

@Column(name = "IDPesanan")
private Long idPesanan;

@Column(name = "IDStatus")
private String idStatus;

@Column(name = "CreatedAt")
private Date createdAt= new Date();

@Column(name = "UpdatedAt")
private LocalDate updatedAt;

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Long getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
