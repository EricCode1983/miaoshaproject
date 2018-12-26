package com.miaoshaproject.service.model;

public class UserModel {
    private Integer id;
    private String  name;
    private Integer gender;
    private Integer age;
    private String  telephone;
    private String  thirdpartyid;
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
