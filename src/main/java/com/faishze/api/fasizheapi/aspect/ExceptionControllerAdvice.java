package com.faishze.api.fasizheapi.aspect;

import com.faishze.api.fasizheapi.exception.ValidateParamException;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:35 PM
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    // 捕捉shiro异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResponse handleShiroError(ShiroException e) {
        return null;
    }

    /**
     * 处理参数校验失败的错误
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateParamException.class)
    public ResponseEntity handleValidateParamError(ValidateParamException e) {
        ErrorResponse errorResponse;
        StringBuilder builder = new StringBuilder();
        List<FieldError> errorList = e.getErrorList();
        for (FieldError error : errorList) {
            builder.append("error param:");
            builder.append(error.getField());
            builder.append("\t\t");
            builder.append("error msg:");
            builder.append(error.getDefaultMessage());
            builder.append("\n");
        }
        errorResponse = new ErrorResponse(ErrorCode.INVALID_PARAMETER.getError(), builder.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
