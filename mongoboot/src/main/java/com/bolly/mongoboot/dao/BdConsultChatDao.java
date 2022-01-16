package com.bolly.mongoboot.dao;


import com.bolly.mongoboot.doc.BdConsultChat;
import com.bolly.mongoboot.doc.LiveRecord;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface BdConsultChatDao {
	/**
	 * 根据融云消息ID获取消息信息
	 *
	 * */
	BdConsultChat getByMsgUID(String msgUID);

	/**
	 * 根据融云消息ID获取消息信息
	 *
	 * */
	BdConsultChat getByMsgUID(String msgUID,String groupID);
	/**
	 * 分页查询聊天信息
	 *
	 * */
	List<BdConsultChat> findPageByCondition(BdConsultChat bdConsultChat, Pageable pageable);
	/**
	 * 取出所有房间
	 *
	 * */
	List<BdConsultChat> findAll();
	/**
	 * 取出某个期间某个房间的聊天记录
	 *
	 * */
	List<BdConsultChat> findPageByCondition(LiveRecord liveRecord, Date startDate, Date endDate);

	/**
	 * 取出某个房间的聊天记录
	 *
	 * */
	List<BdConsultChat> findPageByCondition(LiveRecord liveRecord);

	/**
	 * 按问诊id获取聊天记录
	 */
	List<BdConsultChat> findByOnlineConsultId(long onlineConsultId);
}
