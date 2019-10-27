package com.faishze.api.fasizheapi.aspect;

import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import com.faishze.api.fasizheapi.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 描述: 对返回结果的错误进行解析
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-08-19 22:47
 */
@Aspect
@Component
public class ErrorHandlerAspect {

    /**
     * 设置拦截点为所有的Controller
     */
    @Pointcut("execution(* com.faishze.api.fasizheapi.controller.*.*(..))")
    public void pointCut(){
    }

    /**
     * 对返回结果的错误进行解析，只对返回为Result的进行解析
     * 如果Result成功，就取出其中的数据，进行返回，如果不成
     * 功，封装相对应的ResponseEntity
     *
     * @param joinPoint ProceedingJoinPoint
     * @return Object
     */
    @Around(value = "pointCut()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = joinPoint.proceed();
        if (!(ret instanceof Result)) {
            return ret;
        }

        Result result = (Result) ret;
        if (result.isSuccess()) {
            return result.getData();
        }

        ErrorCode errorCode = result.getErrorCode();
        String message = result.getMessage();
        ErrorResponse errorResponse;
        if (message == null) {
            errorResponse = new ErrorResponse(errorCode.getError(), errorCode.getMessage());
        } else {
            errorResponse = new ErrorResponse(errorCode.getError(), message);
        }
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }

}
