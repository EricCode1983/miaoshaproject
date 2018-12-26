package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    //define exceptional handler to solve the exceptional which cant solve by controller
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerExpetion(HttpServletRequest request, Exception ex){
        Map<String, Object> reponseData = new HashMap<>();
        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            reponseData.put("errCode", businessException.getErrCode());
            reponseData.put("errMsg", businessException.getErrMsg());
        }
        else
        {
            reponseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            reponseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create(reponseData,"fail");
    }
}
