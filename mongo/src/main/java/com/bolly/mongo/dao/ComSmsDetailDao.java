package com.bolly.mongo.dao;

import com.bolly.mongo.entity.ComSmsDetail;
import com.bolly.mongo.entity.GroupCondition;
import com.bolly.mongo.entity.SmsStatis;
import com.bolly.mongo.mongo.MongoDao;
import com.bolly.support.dto.Page;
import com.bolly.support.dto.Pageable;

import java.util.Date;
import java.util.List;

public interface ComSmsDetailDao extends MongoDao<ComSmsDetail,String> {
	List<SmsStatis> groupByCondition(GroupCondition condition, List<String> hosps);

	Page<ComSmsDetail> findPageByCondition(Integer id, Date startDate, Date endDate, Pageable pageable);

	List<ComSmsDetail> findPageByCond(Integer id, Date startDate,Date endDate);
}
