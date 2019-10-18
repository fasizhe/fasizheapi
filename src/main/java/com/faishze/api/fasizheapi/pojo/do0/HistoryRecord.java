package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class HistoryRecord {
    private Long id;

    private Integer userId;

    private String type;

    private Long typeId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
        return "HistoryRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", typeId=" + typeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}