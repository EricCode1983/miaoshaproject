package com.miaoshaproject.validator;


import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private javax.validation.Validator validator;


    public ValidationResult validate(Object bean)  {

        ValidationResult result=new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);

        if(constraintViolationSet.size()>0){
            //Has error
            result.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg=constraintViolation.getMessage();
                String propertyName= constraintViolation.getPropertyPath().toString();
                result.getErrMsgMap().put(propertyName,errMsg);
            });
        }
        return result;


    }


    @Override
    public void afterPropertiesSet() throws Exception {

        //User hibernate validator using factory initialization to instance it
       this.validator=Validation.buildDefaultValidatorFactory().getValidator();
    }
}
