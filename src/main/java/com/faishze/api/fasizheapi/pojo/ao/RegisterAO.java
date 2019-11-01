package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author masonluo
 * @date 2019/11/1 12:15 PM
 */
public class RegisterAO {

    @NotBlank
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$")
    private String phoneNum;

    @NotBlank
    private String password;

    @NotBlank
    private String validateCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
