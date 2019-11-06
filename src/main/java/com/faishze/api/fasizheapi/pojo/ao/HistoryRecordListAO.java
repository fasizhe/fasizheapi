package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/5
 */
public class HistoryRecordListAO {

    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;

    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HistoryRecordListAO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", userId=" + userId +
                '}';
    }
}
