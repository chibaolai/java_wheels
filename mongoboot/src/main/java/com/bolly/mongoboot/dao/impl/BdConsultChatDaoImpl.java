package com.bolly.mongoboot.dao.impl;

import com.bolly.mongoboot.dao.BdConsultChatDao;
import com.bolly.mongoboot.doc.BdConsultChat;
import com.bolly.mongoboot.doc.LiveRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class BdConsultChatDaoImpl extends DataDaoSupport<BdConsultChat, String> implements BdConsultChatDao {
	/**
	 * 设置集合名称
	 */
	private static final String COLLECTION_NAME = "bd_consult_chat";
	private static final String MSG_UID = "msgUID";
	private static final String ONLINE_CONSULT_ID = "onlineConsultId";
	private static final String GROUP_USER_IDS = "groupUserIds";
	protected static final String CREATE_DATE = "createDate";

	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public BdConsultChat getByMsgUID(String msgUID) {
		Criteria criteria = Criteria.where(MSG_UID).is(msgUID);
		return mongoTemplate.findOne(new Query(criteria),BdConsultChat.class,COLLECTION_NAME);
	}

	@Override
	public BdConsultChat getByMsgUID(String msgUID,String gourpID) {
		Criteria criteriaMsgUID = Criteria.where(MSG_UID).is(msgUID);
		Criteria criteriaGroupId = Criteria.where(GROUP_USER_IDS).is(gourpID);
		Criteria criteria = new Criteria().andOperator(criteriaMsgUID,criteriaGroupId);
		return mongoTemplate.findOne(new Query(criteria),BdConsultChat.class,COLLECTION_NAME);
	}

	@Override
	public List<BdConsultChat> findPageByCondition(BdConsultChat bdConsultChat, Pageable pageable) {
		Query query = new Query();
		if (bdConsultChat != null) {
			Criteria criteriaConsultID = Criteria.where(ONLINE_CONSULT_ID).is(bdConsultChat.getOnlineConsultId());
			query.addCriteria(criteriaConsultID);
		}
		query.with(Sort.by(Sort.Direction.ASC,CREATE_DATE)).skip(pageable.getOffset()).limit(pageable.getPageSize());
		query.with(pageable);
		List<BdConsultChat> bdConsultChatList = mongoTemplate.find(query,BdConsultChat.class,COLLECTION_NAME);
		return bdConsultChatList;
	}

	/**
	 * 取出所有聊天的房间
	 * @return
	 */
	@Override
	public List<BdConsultChat> findAll() {
		return mongoTemplate.findAll(BdConsultChat.class,COLLECTION_NAME);
	}

	/**
	 * 根据房间号，取出对应聊天列表
	 * @param liveRecord
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public List<BdConsultChat> findPageByCondition(LiveRecord liveRecord, Date startDate, Date endDate) {
		Query query = new Query();
		if (liveRecord != null) {
			Criteria criteriaUserIDs = Criteria.where(GROUP_USER_IDS).is(liveRecord.getRoomId());
			Criteria criteriaCreateDate = Criteria.where(UPDATE_DATE).gte(startDate);
			Criteria criteriaUpdateDate = Criteria.where(UPDATE_DATE).lt(endDate);
			query.addCriteria(criteriaUserIDs).addCriteria(criteriaCreateDate).addCriteria(criteriaUpdateDate);
		}
		query.with(Sort.by(Sort.Direction.DESC,CREATE_DATE));
		List<BdConsultChat> bdConsultChatList = mongoTemplate.find(query,BdConsultChat.class,COLLECTION_NAME);
		return bdConsultChatList;
	}

	/**
	 * 根据房间号，取出对应聊天列表
	 * @param liveRecord
	 * @return
	 */
	@Override
	public List<BdConsultChat> findPageByCondition(LiveRecord liveRecord) {
		Query query = new Query();
		if (liveRecord != null) {
			Criteria criteriaUserIDs = Criteria.where(GROUP_USER_IDS).is(liveRecord.getRoomId());
			query.addCriteria(criteriaUserIDs);
		}
		query.with(Sort.by(Sort.Direction.DESC,CREATE_DATE));
		List<BdConsultChat> bdConsultChatList = mongoTemplate.find(query,BdConsultChat.class,COLLECTION_NAME);
		return bdConsultChatList;
	}


	/**
	 * 根据聊天id获取对应聊天记录
	 * @param onlineConsultId
	 * @return
	 */
	@Override
	public List<BdConsultChat> findByOnlineConsultId(long onlineConsultId) {
		Query query = new Query();
		Criteria criteriaConsultId = Criteria.where(ONLINE_CONSULT_ID).is(onlineConsultId);
		query.addCriteria(criteriaConsultId).with(Sort.by(Sort.Direction.DESC,CREATE_DATE));
		return mongoTemplate.find(query,BdConsultChat.class,COLLECTION_NAME);
	}
}
