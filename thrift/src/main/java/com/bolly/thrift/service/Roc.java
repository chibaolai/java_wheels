package com.bolly.thrift.service;


import com.bolly.thrift.client.proxy.SelfCheckService;

/**
 * Roc Facade
 * 
 */
public interface Roc {

	/**
	 * 检查(非线程安全,每次调用都需获取新的)
	 * @param hospId 医院ID
	 */
	SelfCheckService getSelfCheckService(int hospId);
}
