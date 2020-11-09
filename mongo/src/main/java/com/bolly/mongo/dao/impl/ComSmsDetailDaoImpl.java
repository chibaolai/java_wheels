package com.bolly.mongo.dao.impl;

import com.bolly.mongo.dao.ComSmsDetailDao;
import com.bolly.mongo.entity.ComSmsDetail;
import com.bolly.mongo.entity.GroupCondition;
import com.bolly.mongo.entity.SmsStatis;
import com.bolly.support.dto.Page;
import com.bolly.support.dto.PageFactory;
import com.bolly.support.dto.Pageable;
import com.bolly.support.utils.DateUtils;
import com.bolly.support.utils.JacksonUtils;
import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.mongojack.Aggregation;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.DBSort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ComSmsDetailDaoImpl extends DataDaoSupport<ComSmsDetail, String> implements ComSmsDetailDao {
	private static final String HOSPITAL_ID = "hospId";
	private static final String HOSPITAL_NAME = "hospName";

	@Override
	public List<SmsStatis> groupByCondition(GroupCondition condition, List<String> hosps) {
		// 执行过滤的条件 [类似于where]
		BasicDBObject cond = new BasicDBObject();
		// 医院ID
		if (condition.getHospId() != null) {
			cond.append(HOSPITAL_ID, condition.getHospId());
		}
		// 医院名
		if (StringUtils.isNotBlank(condition.getHospName())) {
			cond.append(HOSPITAL_NAME, condition.getHospName());
		}
		if (!StringUtils.equals(hosps.get(0),"admin")) {
			List<Integer> hospsInt = new ArrayList<Integer>();
			for (String hosp :hosps) {
				hospsInt.add(Integer.valueOf(hosp));
			}
			cond.put(HOSPITAL_ID, new BasicDBObject("$in", hospsInt));
		}
		// 统计时间
		BasicDBObject dateCond = new BasicDBObject();
		if (condition.getFromDate() != null) {
			dateCond.append("$gt", DateUtils.getDayStart(condition.getFromDate()));
		}
		if (condition.getToDate() != null) {
			dateCond.append("$lte", DateUtils.getDayEnd(condition.getToDate()));
		}
		if (!dateCond.isEmpty()) {
			cond.put(CREATE_DATE, dateCond);
		}
		BasicDBObject initial = new BasicDBObject();
		initial.append("count", 0);
		String reduce = "function (obj, prev) { prev.count += obj.sendTimes}";
		String finalize = null;
		// key：用来分组文档的字段。和keyf两者必须有一个 [类似于group by]
		String keyf = "function(doc) {" + " return {'hospName': doc.hospName,'hospId': doc.hospId}; }";
		// 按天统计短信数
		if ("1".equals(condition.getGroupType())) {
			keyf = "function(doc) {" + " var createDate = doc.createDate;"
									 + " if (!createDate) { createDate = doc.updateDate; };"
							         + " var date = new Date(createDate);"
							         + " var month = (date.getMonth()+1);"
							         + " if (month < 10) { month = '0' + month};"
							         + " var day = date.getDate();"
							         + " if (day < 10) { day = '0' + day};"
							         + " var dateKey = date.getFullYear()+''+month+''+day;"
							         + " return {'inputDate': dateKey,'hospName': doc.hospName,'hospId': doc.hospId}; }";
		}
//		GroupCommand cmd = new GroupCommand(getJackCollection().getDbCollection(), keyf, cond, initial, reduce, finalize);
//		DBObject group = getJackCollection().getDbCollection().group(cmd);


//		String map = "function() {emit(this.hospId,this.sendTimes)}";
//		String mrReduce = "function(key,values) { return Array.sum(values)}";
//		MapReduceCommand mapReduceCommand = new MapReduceCommand(getJackCollection().getDbCollection(),map,mrReduce,null, MapReduceCommand.OutputType.INLINE,cond);
//		MapReduceOutput out = getJackCollection().getDbCollection().mapReduce(mapReduceCommand);


		List<DBObject> aggregateList = new ArrayList();
		DBObject match = new BasicDBObject();
		match.put("$match",cond);

		DBObject hospIdGroup = new BasicDBObject();
		hospIdGroup.put("$first","$hospId");
		DBObject hospNameGroup = new BasicDBObject();
		hospNameGroup.put("$first","$hospName");
		DBObject countGroup = new BasicDBObject();
		countGroup.put("$sum","$sendTimes");

		DBObject subGroup = new BasicDBObject();
		subGroup.put("_id","$hospId");
		subGroup.put("hospId",hospIdGroup);
		subGroup.put("hospName",hospNameGroup);
		subGroup.put("count",countGroup);
		DBObject group = new BasicDBObject();
		group.put("$group",subGroup);

		aggregateList.add(match);
		aggregateList.add(group);
		Cursor out = getJackCollection().getDbCollection().aggregate(aggregateList,AggregationOptions.builder().batchSize(1000).build());

		List<SmsStatis> smsStatis = new ArrayList<>();
		out.forEachRemaining(e -> {
			SmsStatis item = new SmsStatis();
			item.setId(e.get("_id").toString());
			item.setHospId((Integer) e.get("hospId"));
			item.setHospName(e.get("hospName").toString());
			item.setCount((Integer) e.get("count"));
			smsStatis.add(item);
		});



//		@SuppressWarnings("unchecked")
//		List<SmsStatis> smsStatis = (List<SmsStatis>) JacksonUtils.unmarshal(out.results().toString(), List.class,
//				SmsStatis.class);
		return smsStatis;
	}

	@Override
	public Page<ComSmsDetail> findPageByCondition(Integer id, Date startDate, Date endDate, Pageable pageable) {
		DBCursor<ComSmsDetail> dbCursor = getJackCollection().find();
		Integer hospitalId = id;
		if (hospitalId != null) {
			dbCursor.is(HOSPITAL_ID, hospitalId);
		}
		if (startDate != null) {
			dbCursor.greaterThanEquals(CREATE_DATE, DateUtils.getDayStart(startDate));
		}
		if (endDate != null) {
			dbCursor.lessThanEquals(CREATE_DATE, DateUtils.getDayEnd(endDate));
		}
		int total = dbCursor.count();
		List<ComSmsDetail> users = dbCursor.skip(pageable.getOffset()).limit(pageable.getPageSize()).sort(new BasicDBObject("createDate",-1)).toArray();
		return PageFactory.createPage(users, pageable, total);
	}

	@Override
	public List<ComSmsDetail> findPageByCond(Integer id, Date startDate, Date endDate) {
		List<ComSmsDetail> list = new ArrayList<>();
		Query query = DBQuery.empty();
		query.is(HOSPITAL_ID, id);
		if (startDate != null) {
			query.greaterThanEquals(CREATE_DATE, startDate);
		}
		if (endDate != null) {
			query.lessThanEquals(CREATE_DATE, endDate);
		}
		list = getJackCollection().find(query).sort(DBSort.desc(UPDATE_DATE)).toArray();
		return list;
	}

}
