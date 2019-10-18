package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class Opinion {
    private Long id;

    private Integer userId;

    private String content;

    private Byte read;

    private Date createTime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getRead() {
        return read;
    }

    public void setRead(Byte read) {
        this.read = read;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", read=" + read +
                ", createTime=" + createTime +
                '}';
    }
}