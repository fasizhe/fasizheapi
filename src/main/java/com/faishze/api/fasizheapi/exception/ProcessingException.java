package com.faishze.api.fasizheapi.exception;


import com.faishze.api.fasizheapi.result.ErrorCode;

/**
 * 描述: 处理 处理过程中的异常
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-26
 */
public class ProcessingException extends RuntimeException {

    private final ErrorCode errorCode;

    private String message;

    public ProcessingException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ProcessingException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
