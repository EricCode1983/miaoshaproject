package com.miaoshaproject.dataobject;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.telephone
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.thirdPartyId
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    private String thirdpartyid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.gender
     *
     * @return the value of user_info.gender
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.gender
     *
     * @param gender the value for user_info.gender
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.telephone
     *
     * @return the value of user_info.telephone
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.telephone
     *
     * @param telephone the value for user_info.telephone
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.thirdPartyId
     *
     * @return the value of user_info.thirdPartyId
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public String getThirdpartyid() {
        return thirdpartyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.thirdPartyId
     *
     * @param thirdpartyid the value for user_info.thirdPartyId
     *
     * @mbg.generated Mon Dec 31 15:52:59 WIB 2018
     */
    public void setThirdpartyid(String thirdpartyid) {
        this.thirdpartyid = thirdpartyid == null ? null : thirdpartyid.trim();
    }
}