package com.faishze.api.fasizheapi.enums;

import com.faishze.api.fasizheapi.pojo.do0.Oauth;

/**
 * @author masonluo
 * @date 2019/10/26 12:07 AM
 */
public enum  OauthType {
    WECHAT(1, "weChat");

    private Integer oauthId;

    private String oauthType;

    OauthType(Integer oauthId, String oauthType) {
        this.oauthId = oauthId;
        this.oauthType = oauthType;
    }

    public static OauthType getByOauthTypeById(Integer id){
        for(OauthType oauthType : OauthType.values()){
            if(oauthType.getOauthId().equals(id)){
                return oauthType;
            }
        }
        return null;
    }

    public Integer getOauthId() {
        return oauthId;
    }

    public void setOauthId(Integer oauthId) {
        this.oauthId = oauthId;
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType;
    }
}
