package com.juaracoding.DBLaundry.Repo;/*
Created By IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author Syarifudin a.k.a. Muhamad Syarifuidn
Java Developer
Created on 05/03/2023 2:28
@Last Modified 05/03/2023 2:28
Version 1.1
*/


import com.juaracoding.DBLaundry.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
}
