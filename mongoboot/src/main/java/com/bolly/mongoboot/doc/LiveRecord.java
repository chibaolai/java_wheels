package com.bolly.mongoboot.doc;

import org.springframework.data.annotation.Id;

public class LiveRecord extends BaseDoc<String> {

	@Id
	private String id;
	/**
	 * 房间号
	 * */
	private String roomId;
	/**
	 * 混流号
	 * */
	private String streamId;
	/**
	 * 混流地址
	 * */
	private String streamUrl;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束
	 */
	private String endTime;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
