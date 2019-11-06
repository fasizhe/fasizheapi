package com.faishze.api.fasizheapi.pojo.dto;

import com.faishze.api.fasizheapi.constant.HistoryRecordType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/5
 */
public class HistoryRecordDTO<T> implements Serializable {

    private static final long serialVersionUID = -6048093189339242778L;
    private Long id;

    private Integer userId;

    private HistoryRecordType type;

    private T data;

    //首次浏览
    private Date createTime;

    //最近一次浏览
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

    public HistoryRecordType getType() {
        return type;
    }

    public void setType(HistoryRecordType type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
        return "HistoryRecordDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", data=" + data +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
