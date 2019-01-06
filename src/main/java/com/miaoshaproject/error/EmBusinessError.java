package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError{

    //common error type 10001
    PARAMETER_VALIDATION_ERROR(10001,"Parameter illegal"),

    //UNKNOW ERROR
    UNKNOWN_ERROR(10002,"UnKnown Error"),

    //10000 for user error
    USER_NOT_EXIST(20001,"User not exists"),
    USER_LOGIN_FAIL(20002,"phone or password not correct"),
    USER_NOT_LOGIN(20003,"User not login the website"),
    //30000 trading info error
    STOCK_NOT_ENOUGH(30001,"stock not enough")
    ;
    private EmBusinessError(int errCode,String errMsg)
    {
        this.errCode=errCode;
        this.errMsg=errMsg;
    }
    
    private  int errCode;
    private  String errMsg;


    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
