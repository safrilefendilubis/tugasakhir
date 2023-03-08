package com.juaracoding.DBLaundry.service;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/7/2023 3:41 PM
@Last Modified 3/7/2023 3:41 PM
Version 1.1
*/

import com.juaracoding.DBLaundry.configuration.OtherConfig;
import com.juaracoding.DBLaundry.core.BcryptImpl;
import com.juaracoding.DBLaundry.handler.ResponseHandler;
import com.juaracoding.DBLaundry.model.Users;
import com.juaracoding.DBLaundry.repo.UserRepo;
import com.juaracoding.DBLaundry.utils.ConstantMessage;
import com.juaracoding.DBLaundry.utils.ExecuteSMTP;
import com.juaracoding.DBLaundry.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class UserService {

    private UserRepo userRepo;

    private String[] strExceptionArr = new String[2];

    @Autowired
    public UserService(UserRepo userService) {
        strExceptionArr[0] = "UserService";
        this.userRepo = userService;
    }

    public Map<String, Object> checkRegis(Users users, WebRequest request) {
        int intVerification = new Random().nextInt(100000, 999999);
        List<Users> listUserResult = userRepo.findByEmailOrNoHPOrUsername(users.getEmail(), users.getNoHP(), users.getUsername());//INI VALIDASI USER IS EXISTS
        String emailForSMTP = "";
        try {
            if (listUserResult.size() != 0)//kondisi mengecek apakah user terdaftar
            {

                emailForSMTP = users.getEmail();
                Users nextUser = listUserResult.get(0);
                if (nextUser.getIsDelete() != 0)//sudah terdaftar dan aktif
                {
                    //PEMBERITAHUAN SAAT REGISTRASI BAGIAN MANA YANG SUDAH TERDAFTAR (USERNAME, EMAIL ATAU NOHP)
                    if (nextUser.getEmail().equals(users.getEmail())) {
                        return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_EMAIL_ISEXIST,
                                HttpStatus.NOT_ACCEPTABLE, null, "FV01001", request);//EMAIL SUDAH TERDAFTAR DAN AKTIF
                    } else if (nextUser.getNoHP().equals(users.getNoHP())) {//FV = FAILED VALIDATION
                        return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_NOHP_ISEXIST,
                                HttpStatus.NOT_ACCEPTABLE, null, "FV01002", request);//NO HP SUDAH TERDAFTAR DAN AKTIF
                    } else if (nextUser.getUsername().equals(users.getUsername())) {
                        return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_USERNAME_ISEXIST,
                                HttpStatus.NOT_ACCEPTABLE, null, "FV01003", request);//USERNAME SUDAH TERDAFTAR DAN AKTIF
                    } else {
                        return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_USER_ISACTIVE,
                                HttpStatus.NOT_ACCEPTABLE, null, "FV01004", request);//KARENA YANG DIAMBIL DATA YANG PERTAMA JADI ANGGAPAN NYA SUDAH TERDAFTAR SAJA
                    }
                } else {
                    nextUser.setPassword(BcryptImpl.hash(users.getPassword() + users.getUsername()
                            + users.getNamaLengkap()));
                    nextUser.setRememberToken(BcryptImpl.hash(String.valueOf(intVerification)));
                    nextUser.setModifiedBy(Integer.parseInt(nextUser.getIdUser().toString()));
                    nextUser.setModifiedDate(new Date());
                }
            } else//belum terdaftar
            {
                users.setPassword(BcryptImpl.hash(users.getPassword() + users.getUsername()));
                users.setRememberToken(BcryptImpl.hash(String.valueOf(intVerification)));
                userRepo.save(users);
            }
            /*EMAIL NOTIFICATION*/
            if (OtherConfig.getFlagSMTPActive().equalsIgnoreCase("y") && !emailForSMTP.equals("")) {
                new ExecuteSMTP().sendSMTPToken(emailForSMTP, "VERIFIKASI TOKEN REGISTRASI",
                        "TOKEN UNTUK VERIFIKASI EMAIL", String.valueOf(intVerification));
            }
            System.out.println("VERIFIKASI -> " + intVerification);
        } catch (Exception e) {
            strExceptionArr[1] = "checkRegis(Userz users) --- LINE 70";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                    HttpStatus.NOT_FOUND, null, "FE01001", request);
        }
        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_CHECK_REGIS,
                HttpStatus.CREATED, null, null, request);


    }

}




