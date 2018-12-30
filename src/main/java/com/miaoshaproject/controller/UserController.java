package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private  HttpServletRequest httpServletRequest;

    //user login
    @RequestMapping("/login")
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telephone") String telephone,
                                     @RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if(org.apache.commons.lang3.StringUtils.isEmpty(telephone)||StringUtils.isEmpty(password)){

            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"login parameter cant be empty");

        }
        UserModel userModel= userService.validateLogin(telephone,this.EncodeByMd5(password));

        //add user info to session
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);

        //user register
    }
    //User Register
    @RequestMapping("/register")
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telephone") String telephone, @RequestParam(name="otpCode") String otpCode,
                                     @RequestParam(name="name") String name,
                                     @RequestParam(name="gender") Integer gender,
                                     @RequestParam(name="age") Integer age,
                                     @RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //verify code the same with the otpcode
        String inSessionOtpCode=(String) this.httpServletRequest.getSession().getAttribute(telephone);
        if(!com.alibaba.druid.util.StringUtils.equals(otpCode,inSessionOtpCode)){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"otpcode not correct.");

        }
        UserModel userModel=new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setThirdpartyid("1");
        userModel.setEncryptPassword(this.EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);

        //user register
    }

    public String EncodeByMd5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();

        //Encry String
        String newstr= base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    @RequestMapping("/getuser")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
       UserModel userModel= userService.getUserById(id);

//       if (userModel==null){
//           throw  new BusinessException(EmBusinessError.USER_NOT_EXIST);
//       }

        //show exceptionuserModel.setEncryptPassword("1230");
       UserVO userVO=  convertFromModel(userModel);
       return  CommonReturnType.create(userVO);
    }


    //Get Otp
    @RequestMapping(value = "/getotp",method ={RequestMethod.POST},consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telephone") String telephone){

        //Base on some rule to generate the otp
        Random random=new Random();
        int radomInt=random.nextInt(9999);
        radomInt+=10000;
        String optCode=String.valueOf(radomInt);

        //otp relate to headphone, use httpseesion bind otp code and phone

        httpServletRequest.getSession().setAttribute(telephone,optCode);
        System.out.println("telephone="+telephone+" &otpCode="+optCode);
        return CommonReturnType.create(null);
    }



    private UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO=new UserVO();
      BeanUtils.copyProperties(userModel,userVO);
      return userVO;
    }


}
