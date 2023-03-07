package com.juaracoding.DBLaundry.model;/*
IntelliJ IDEA 2022.3.2 (Ultimate Edition)
Build #IU-223.8617.56, built on January 26, 2023
@Author User a.k.a. Safril Efendi Lubis
Java Developer
Created on 3/1/2023 4:18 PM
@Last Modified 3/1/2023 4:18 PM
Version 1.1
*/

import com.juaracoding.DBLaundry.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "MstUser", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
public class Users {

    @Id
    @Column(name = "IDuser",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty(message = ConstantMessage.WARNING_NAMA_EMPTY)
    @NotNull (message = ConstantMessage.WARNING_NAMA_NULL)
    @NotBlank(message = ConstantMessage.WARNING_NAMA_BLANK)
    @Length(message = ConstantMessage.WARNING_MAXSIMAL_NAMA,max = 40)
    @Column(name = "Name",nullable = false, unique = true, length = 40)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_EMPTY)
    @NotNull(message = ConstantMessage.WARNING_EMAIL_NULL)
    @NotBlank(message = ConstantMessage.WARNING_EMAIL_BLANK)
    @Column(name = "Email",unique = true, nullable = false)
    private String email;

    @Column(name = "EmailVerifiedAt")
    private Date emailVerifiedAt;

    @NotEmpty(message = ConstantMessage.WARNING_USERNAME_EMPTY)
    @NotNull(message = ConstantMessage.WARNING_USERNAME_NULL)
    @NotBlank(message = ConstantMessage.WARNING_USERNAME_BLANK)
    @Length(message = ConstantMessage.WARNING_USERNAME_MAX_LENGTH,min = 5,max = 8)
    @Column(name = "Username")
    private String username;

    @NotEmpty(message = ConstantMessage.WARNING_PASSWORD_EMPTY)
    @NotNull(message = ConstantMessage.WARNING_PASSWORD_NULL)
    @NotBlank(message = ConstantMessage.WARNING_PASSWORD_BLANK)
    @Length(message = ConstantMessage.WARNING_PASSWORD_MINIMAL,min = 8)
    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "RememberToken")
    private String rememberToken;

    @Column(name = "Role")
    private String role;
    /*
  start audit trails
   */

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy = 1;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 0;

    /*
        end audit trails
     */

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emai) {
        this.email = emai;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emaiVerifiedAt) {
        this.emailVerifiedAt = emaiVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}