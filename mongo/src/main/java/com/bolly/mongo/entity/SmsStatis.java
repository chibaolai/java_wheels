package com.bolly.mongo.entity;

public class SmsStatis {


	private String id;
	/**
	 * 医院ID
	 */
	private int hospId;
	/**
	 * 医院名
	 */
	private String hospName;
	/**
	 * 报告数量
	 */
	private int count;
	/**
	 * 短信查询时间
	 */
	private String inputDate;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHospId() {
        return hospId;
    }

    public void setHospId(int hospId) {
        this.hospId = hospId;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

}
