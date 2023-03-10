package com.juaracoding.DBLaundry.model;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/1/2023 10:56 PM
@Last Modified 3/1/2023 10:56 PM
Version 1.1
*/
import javax.persistence.*;

@Entity
@Table(name= "MstStatus")
public class Status {

    @Id
    @Column(name="IDStatus",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(name="Status")
    private Long status;

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}