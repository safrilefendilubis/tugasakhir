package com.juaracoding.DBLaundry.model;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/1/2023 4:51 PM
@Last Modified 3/1/2023 4:51 PM
Version 1.1
*/

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MstPesananLaundry")
public class PesananLaundry {

    @Id
    @Column(name = "IDPesanan",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPesanan;

    @Column(name = "Longitude")
    private String lon;

    @Column(name = "Latiitude")
    private String lat;

    @ManyToOne
    @JoinColumn(name = "IDPembayaran")
    private Pembayaran pembayaran;

    @ManyToOne
    @JoinColumn(name = "IDListHarga")
    private PaketLayanan idPaketLayanan;

    @ManyToOne
    @JoinColumn(name = "IDUsers")
    private Users idusers;

    @ManyToOne()
    @JoinColumn(name = "IDStatus")
    private Status idStatus;

    /*
     * Start Audit Trails
     * */
    @Column(name = "CreatedDate")
    private Date createdDate = new Date();

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "CreatedBy")
    private byte createdBy = 1;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete")
    private byte isDelete = 0;

    /*
     * End Audit Trails
     * */
    public Long getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public PaketLayanan getIdPaketLayanan() {
        return idPaketLayanan;
    }

    public void setIdPaketLayanan(PaketLayanan idPaketLayanan) {
        this.idPaketLayanan = idPaketLayanan;
    }

    public Users getIdusers() {
        return idusers;
    }

    public void setIdusers(Users idusers) {
        this.idusers = idusers;
    }

    public Status getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Status idStatus) {
        this.idStatus = idStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdAt) {
        this.createdDate = createdAt;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public byte getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(byte createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }
}
