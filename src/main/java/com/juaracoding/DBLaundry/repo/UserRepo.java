package com.juaracoding.DBLaundry.repo;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/7/2023 3:39 PM
@Last Modified 3/7/2023 3:39 PM
Version 1.1
*/
import com.juaracoding.DBLaundry.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<Users,Long> {

    public List<Users> findByEmail(String value);
    public List<Users> findByEmailOrNoHPOrUsername(String emails, String noHP, String userName);

}
