package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ReportReportTag {
    private Long id;

    private Integer reportId;

    private Integer reportTagId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportTagId() {
        return reportTagId;
    }

    public void setReportTagId(Integer reportTagId) {
        this.reportTagId = reportTagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReportReportTag{" +
                "id=" + id +
                ", reportId=" + reportId +
                ", reportTagId=" + reportTagId +
                ", createTime=" + createTime +
                '}';
    }
}