package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {

    //User user id to get user object
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;
}
