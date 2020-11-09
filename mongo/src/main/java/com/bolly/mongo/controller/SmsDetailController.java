package com.bolly.mongo.controller;

import com.bolly.mongo.entity.SmsStatis;
import com.bolly.mongo.req.DynamicVO;
import com.bolly.mongo.service.SmsDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SmsDetailController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsDetailController.class);
	@Autowired
	private SmsDetailService smsDetailService;

	@RequestMapping(value = "/pc/statis/sms/search", method = RequestMethod.GET)
	@ResponseBody
	public List<SmsStatis> search(DynamicVO condition) {
		condition.setHospId("10050");
		return smsDetailService.findSms(condition);
	}
}
