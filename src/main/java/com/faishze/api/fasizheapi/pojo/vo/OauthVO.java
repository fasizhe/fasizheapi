package com.faishze.api.fasizheapi.pojo.vo;

/**
 * 用户绑定返回的
 * @author masonluo
 * @date 2019/10/29 3:13 PM
 */
public class OauthVO {
    private String username;

    private String openID;

    private String oauthType;

    public OauthVO() {
    }

    public OauthVO(String username, String openID, String oauthType) {
        this.username = username;
        this.openID = openID;
        this.oauthType = oauthType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType;
    }
}
