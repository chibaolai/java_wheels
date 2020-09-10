package com.bolly.thrift.entity;

public class ConfigHosp {

	private int hospId;

	private String apiKey;

	public ConfigHosp(int hospId, String apiKey) {
		this.hospId = hospId;
		this.apiKey = apiKey;
	}

	public int getHospId() {
		return hospId;
	}

	public String getApiKey() {
		return apiKey;
	}

}
