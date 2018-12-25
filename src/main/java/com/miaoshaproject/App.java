package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@RestController
@MapperScan("com.miaoshaproject.dao")
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        return "Hello World";
    }
    @RequestMapping("/test")
    public String test(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null)
        {
            return "user not exists";
        }
        else {
            return userDO.getName();
        }

    }

    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
