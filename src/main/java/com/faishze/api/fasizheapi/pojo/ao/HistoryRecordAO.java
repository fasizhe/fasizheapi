package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.constant.HistoryRecordType;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/5
 */
public class HistoryRecordAO {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "历史记录的类型不能为空")
    private HistoryRecordType type;

    @NotNull(message = "具体记录的Id不能为空")
    private Long typeId;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "HistoryRecordAO{" +
                "userId=" + userId +
                ", type=" + type +
                ", typeId=" + typeId +
                '}';
    }
}
