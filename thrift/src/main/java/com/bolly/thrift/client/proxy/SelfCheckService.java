package com.bolly.thrift.client.proxy;


import com.niox.api2.contract.basedata.Result;
import com.niox.api2.contract.request.HeartBeatRequest;

public interface SelfCheckService extends com.niox.api2.contract.SelfCheckService.Iface {

	@Override
	Result heartBeat();

}
