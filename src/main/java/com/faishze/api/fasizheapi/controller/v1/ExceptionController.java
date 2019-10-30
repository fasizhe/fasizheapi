package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.exception.ProcessingException;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author masonluo
 * @date 2019/10/23 11:35 PM
 */
@RestControllerAdvice
public class ExceptionController {
    // 捕捉shiro异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResponse handleShiroError(ShiroException e){
        return null;
    }

    //捕捉业务异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProcessingException.class)
    public ErrorResponse handleProcessingException(ProcessingException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode().getError());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    //捕捉校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validExceptionHandler( MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer stringBuffer = new StringBuffer();
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //该格式仅仅作为response展示和log作用，前端应自己做校验
                stringBuffer.append(fieldError.getObjectName() + "--" +                             fieldError.getDefaultMessage() + " ");
            }
        }
        return new ErrorResponse("412 ",stringBuffer.toString());
    }
}
