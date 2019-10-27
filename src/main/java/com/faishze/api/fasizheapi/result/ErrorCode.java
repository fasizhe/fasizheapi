package com.faishze.api.fasizheapi.result;

import org.springframework.http.HttpStatus;

/**
 * 描述: 错误码与错误信息的映射
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
public enum ErrorCode {

    /**
     * 参数为null
     */
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,
            "InvalidParameter", "The {Parameter} is not valid."),

    /**
     * 参数为null
     */
    INVALID_PARAMETER_IS_NULL(HttpStatus.BAD_REQUEST,
            "InvalidParameter.IsNull", "The required {Parameter} must be not null."),

    /**
     * 参数为空
     */
    INVALID_PARAMETER_IS_BLANK(HttpStatus.BAD_REQUEST,
            "InvalidParameter.IsBlank", "The required {Parameter} must be not blank."),

    /**
     * 参数值超过限定范围，Number类型
     */
    INVALID_PARAMETER_VALUE_EXCEEDED(HttpStatus.BAD_REQUEST,
            "InvalidParameter.ValueExceeded", "The name of {Parameter} exceeded, max: {Value}."),

    /**
     * 参数值小于限定范围，Number类型
     */
    INVALID_PARAMETER_VALUE_BELOW(HttpStatus.BAD_REQUEST,
            "InvalidParameter.ValueBelow", "The name of {Parameter} below, min: {Value}."),

    /**
     * 参数长度不在规定范围内，String类型
     */
    INVALID_PARAMETER_SIZE(HttpStatus.BAD_REQUEST,
            "InvalidParameter.Size", "The size of {Parameter} must be from {Min} to {Max}."),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(HttpStatus.BAD_REQUEST,
            "UnknownError", "The request processing has failed due to some unknown exception."),

    /**
     * 短时间内请求过多
     */
    THROTTLING(HttpStatus.BAD_REQUEST,
            "Throttling", "You have made too many requests within a short time."),

    /**
     * 未通过认证
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "Unauthorized", "User not authorized."),

    /**
     * 没有附带请求该资源所需的token
     */
    UNAUTHORIZED_TOKEN_IS_NULL(HttpStatus.UNAUTHORIZED,
            "Unauthorized.TokenIsNull", "The request auth token is null."),

    /**
     * 被禁止
     */
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "Forbidden.SubUser", "Forbidden."),

    /**
     * 用户在未认证的情况下操作该资源
     */
    FORBIDDEN_UNAUTHORIZED(HttpStatus.FORBIDDEN,
            "Forbidden.Unauthorized", "User not authorized to operate on the specified resource."),

    /**
     * 用户在无权操作该资源
     */
    FORBIDDEN_SUB_USER(HttpStatus.FORBIDDEN,
            "Forbidden.SubUser", "The specified action is not available for you."),

    /**
     * 未找到该参数
     */
    INVALID_PARAMETER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "InvalidParameter.NotFound", "The specified resource does not exist."),

    /**
     * 未找到该参数
     */
    INVALID_OPERATION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "InvalidOperation.NotFound", "The specified resource does not exist."),

    /**
     * 参数长度不在规定范围内，String类型
     */
    OPERATION_CONFLICT(HttpStatus.CONFLICT,
            "OperationConflict", "Request was denied due to conflict with a previous request."),

    /**
     * 由于某些未知错误、异常或失败，请求处理失败。
     */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "InternalError", "The request processing has failed due to some unknown exception, exception or failure."),

    /**
     * 第三方系统的用户，需要在我们的系统内绑定账号
     * @author masonluo
     */
    NEED_BIND(HttpStatus.TEMPORARY_REDIRECT,
            "NEED_BIND", "The third party user must be bind an account in our system");

    private final HttpStatus httpStatus;
    private final String error;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.error = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
