package com.bolly.thrift.service.impl;

import com.bolly.thrift.entity.ConfigHospitalCloud;
import com.bolly.thrift.client.proxy.SelfCheckServiceProxy;
import com.bolly.thrift.client.proxy.SelfCheckService;
import com.bolly.thrift.service.Roc;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RocImpl implements Roc {

	@Autowired
	private ObjectFactory<SelfCheckServiceProxy> selfCheckServiceProxyFactory;


	@Override
	public SelfCheckService getSelfCheckService(int hospId) {
		ConfigHospitalCloud hospCloud = getHospConfig(hospId);
		SelfCheckServiceProxy proxy = selfCheckServiceProxyFactory.getObject();
//		proxy.init(hospCloud, SelfCheckService.class.getSimpleName());
		return proxy;
	}

	private ConfigHospitalCloud getHospConfig(int hospId) {
		ConfigHospitalCloud hospCloud = new ConfigHospitalCloud();
		hospCloud.setHospitalId(hospId);
		hospCloud.setIp("127.0.0.1");
		hospCloud.setPort(10028);
		hospCloud.setApiKey("1");
		hospCloud.setTimeout(5000);
		hospCloud.setTransportType(1);
		hospCloud.setDefaultProtocol(1);
		hospCloud.setVersionId(2);
		return hospCloud;
	}

}
