package com.faishze.api.fasizheapi.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/11/1 12:20 PM
 */
public class ValidateParamException extends RuntimeException {

    private List<FieldError> errorList;

    public ValidateParamException(String msg){
        super(msg);
    }


    public ValidateParamException(List<FieldError> errors, String msg){
        super(msg);
        this.errorList = errors;
    }

    public ValidateParamException(List<FieldError> errors){
        this.errorList = errors;
    }

    public List<FieldError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<FieldError> errorList) {
        this.errorList = errorList;
    }
}
