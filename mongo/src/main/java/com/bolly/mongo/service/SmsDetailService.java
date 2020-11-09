package com.bolly.mongo.service;

import com.bolly.mongo.dao.ComSmsDetailDao;
import com.bolly.mongo.entity.ComSmsDetail;
import com.bolly.mongo.entity.GroupCondition;
import com.bolly.mongo.entity.SmsStatis;
import com.bolly.mongo.req.DynamicVO;
import com.bolly.support.dto.Page;
import com.bolly.support.dto.PageFactory;
import com.bolly.support.utils.DateUtils;
import com.bolly.support.utils.ObjectUtils;
import com.sun.rowset.internal.Row;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SmsDetailService {
	@Autowired
	private ComSmsDetailDao comSmsDetailDao;

	public List<SmsStatis> findSms(DynamicVO condition) {
		GroupCondition smsCondition = new GroupCondition();
		// 医院ID
		String hospId = condition.getHospId();
		// 开始时间
		String fromDate = condition.getFromDate();
		// 结束时间
		String toDate = condition.getToDate();
		// 统计类型 0:合计 1:按天统计
		String totalType = condition.getTotalType();
		// 医院名
		String hospName = condition.getHospName();
		if (StringUtils.isNotBlank(hospId)) {
			smsCondition.setHospId(Integer.valueOf(hospId));
		}
		if (StringUtils.isNotBlank(hospName)) {
			smsCondition.setHospName(hospName);
		}
		if (StringUtils.isNotBlank(fromDate)) {
			if (fromDate.contains("-")) {
				fromDate = fromDate.replace("-", StringUtils.EMPTY);
			}
			smsCondition.setFromDate(DateUtils.parseCmprs(fromDate));
		}
		if (StringUtils.isNotBlank(toDate)) {
			if (toDate.contains("-")) {
				toDate = toDate.replace("-", StringUtils.EMPTY);
			}
			smsCondition.setToDate(DateUtils.parseCmprs(toDate));
		}
		smsCondition.setGroupType(StringUtils.isNotBlank(totalType) ? Integer.valueOf(totalType) : 0);
		List<String> hospIds = Stream.of("10050").collect(Collectors.toList());
		List<SmsStatis> groups = comSmsDetailDao.groupByCondition(smsCondition,hospIds);
		// 按天统计时，结果按时间倒序排序
		if ("1".equals(totalType)) {
			Collections.sort(groups, new Comparator<SmsStatis>() {
				@Override
				public int compare(SmsStatis o1, SmsStatis o2) {
					String type1 = o1.getInputDate();
					String type2 = o2.getInputDate();
					if (ObjectUtils.isNumeric(type1) && ObjectUtils.isNumeric(type2)) {
						return Integer.valueOf(type2).compareTo(Integer.valueOf(type1));
					}
					return 1;
				}
			});
		}
		List<SmsStatis> newGroups = new ArrayList<SmsStatis>();
		for (SmsStatis group : groups) {
			if (group.getCount() == 0) {
				continue;
			}
			if (!ObjectUtils.isNumeric(group.getInputDate())) {
				group.setInputDate(null);
			}
			String date = group.getInputDate();
			if ("1".equals(totalType)) {
				group.setInputDate(DateUtils.format(DateUtils.parseCmprs(date), "yyyy-MM-dd"));
			} else {
				String datePart = StringUtils.EMPTY;
				if (StringUtils.isNotBlank(fromDate)) {
					datePart += fromDate;
				}
				if (StringUtils.isNotBlank(datePart)) {
					datePart += " ~ ";
				}
				if (StringUtils.isNotBlank(toDate)) {
					datePart += toDate;
				}
				group.setInputDate(datePart);
			}
			newGroups.add(group);
		}
		return groups;
	}
}
