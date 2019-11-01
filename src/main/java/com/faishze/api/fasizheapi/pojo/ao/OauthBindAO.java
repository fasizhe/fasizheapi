package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.enums.OauthType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author masonluo
 * @date 2019/10/29 2:31 PM
 */
public class OauthBindAO {

    // 第三方平台类型ID
    @NotNull(message = "第三方类型ID不能为空")
    private Integer oauthTypeID;
    // 发送给手机的验证码
    @NotBlank(message = "手机验证码不能为空")
    private String validateCode;
    // 后端颁发的code
    @NotBlank(message = "后端颁发的凭证不能为空")
    private String code;
    // 头像url
    private String avatarUrl;
    // 昵称
    private String nickName;

    public Integer getOauthTypeID() {
        return oauthTypeID;
    }

    public void setOauthTypeID(Integer oauthTypeID) {
        this.oauthTypeID = oauthTypeID;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
