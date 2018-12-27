package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")

public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private  HttpServletRequest httpServletRequest;

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
