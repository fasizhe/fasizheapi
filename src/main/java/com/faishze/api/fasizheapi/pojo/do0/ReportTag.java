package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ReportTag {
    private Long id;

    private String type;

    private Integer count;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        return "ReportTag{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", count=" + count +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}