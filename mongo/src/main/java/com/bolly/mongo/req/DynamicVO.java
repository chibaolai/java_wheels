package com.bolly.mongo.req;

public class DynamicVO {
    private String hospId;
    private String fromDate;
    private String toDate;
    private String totalType;
    private String hospName;

    public String getHospId() {
        return hospId;
    }

    public void setHospId(String hospId) {
        this.hospId = hospId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTotalType() {
        return totalType;
    }

    public void setTotalType(String totalType) {
        this.totalType = totalType;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }
}
