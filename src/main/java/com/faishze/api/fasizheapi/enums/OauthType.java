package com.faishze.api.fasizheapi.enums;

/**
 * FIXME 将数据库表中的枚举字段更改为tinyInt，如果数据库读出来一个String，那也需要我们这边定义的name一模一样, 如果以后添加类型，数据库就需要改变表结构，比较麻烦，不如直接一个整形
 * @author masonluo
 * @date 2019/10/26 12:07 AM
 */
public enum  OauthType {
    WECHAT(1, "WECHAT");

    private Integer oauthId;

    private String oauthName;

    OauthType(Integer oauthId, String oauthName) {
        this.oauthId = oauthId;
        this.oauthName = oauthName;
    }

    public static OauthType getOauthTypeById(Integer id){
        for(OauthType oauthType : OauthType.values()){
            if(oauthType.getOauthId().equals(id)){
                return oauthType;
            }
        }
        return null;
    }

    public static OauthType getOauthTypeByOauthName(String oauthName){
        for(OauthType oauthType : OauthType.values()){
            if(oauthType.getOauthName().equals(oauthName)){
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

    public String getOauthName() {
        return oauthName;
    }

    public void setOauthName(String oauthName) {
        this.oauthName = oauthName;
    }
}
