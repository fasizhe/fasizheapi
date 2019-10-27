package com.faishze.api.fasizheapi.pojo.do0;

import com.faishze.api.fasizheapi.enums.OauthType;

import java.util.Date;

public class Oauth {
    private Integer id;

    private Integer userId;

    private OauthType oauthType;

    private String oauthId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OauthType getOauthType() {
        return oauthType;
    }

    public void setOauthType(OauthType oauthType) {
        this.oauthType = oauthType;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId == null ? null : oauthId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Oauth{" +
                "id=" + id +
                ", userId=" + userId +
                ", oauthType='" + oauthType + '\'' +
                ", oauthId='" + oauthId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}