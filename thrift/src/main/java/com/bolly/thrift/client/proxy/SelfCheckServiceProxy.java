package com.bolly.thrift.client.proxy;

import com.bolly.thrift.exception.Api2Exceptions;
import com.niox.api2.contract.basedata.Result;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class SelfCheckServiceProxy implements SelfCheckService {

	@Override
	public Result heartBeat() {
		try {
			Result result = requestRoc();
//			Api2Exceptions.assertResult(result);
			return result;
		} catch (TException e) {
			throw Api2Exceptions.transException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Result requestRoc() throws TException, FileNotFoundException {
//		TSSLTransportFactory.TSSLTransportParameters tsslTransportParams = new TSSLTransportFactory.TSSLTransportParameters();
//		String api2TrustoreUrl = ResourceUtils.getURL("classpath:keystore/api2truststore.jks").toString();
//		tsslTransportParams.setTrustStore(api2TrustoreUrl, "NioxPC2#1212123?");
//		TTransport transport = TSSLTransportFactory.getClientSocket("127.0.0.1", 10028, 50000, tsslTransportParams);
		TTransport transport = new TSocket("127.0.0.1", 10028, 50000);
//		((TSocket) transport).setConnectTimeout(1);
//		((TSocket) transport).setSocketTimeout(1);
//		((TSocket) transport).setTimeout(1);
		TProtocol protocol = new TCompactProtocol(transport);
		com.niox.api2.contract.SelfCheckService.Client rocClient = new com.niox.api2.contract.SelfCheckService.Client(protocol);
		transport.open();
		return rocClient.heartBeat();
	}

}
