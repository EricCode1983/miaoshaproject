package com.miaoshaproject.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {
    private Integer id;
    @NotBlank(message = "User name cant be empty.")
    private String  name;
    @NotNull(message = "Gender need fill.")
    private Integer gender;
    @NotNull(message = "Age need fill.")
    @Min(value = 0,message = "age need more than 0.")
    @Max(value = 150,message = "age need less than 150.")
    private Integer age;
    @NotNull(message = "Mobile phone cant be empty.")
    private String  telephone;
    private String  thirdpartyid;
    @NotNull(message = "Password cant be empty.")
    private String  encryptPassword;


    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getThirdpartyid() {
        return thirdpartyid;
    }

    public void setThirdpartyid(String thirdpartyid) {
        this.thirdpartyid = thirdpartyid;
    }
}
