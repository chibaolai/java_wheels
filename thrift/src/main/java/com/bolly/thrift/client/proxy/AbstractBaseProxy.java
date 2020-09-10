package com.bolly.thrift.client.proxy;

import com.bolly.thrift.entity.ConfigHosp;
import com.bolly.thrift.entity.ConfigHospAware;
import com.bolly.thrift.entity.ConfigHospitalCloud;
import com.bolly.thrift.exception.Api2ConnectException;
import com.bolly.thrift.utils.ReflectionUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;

import javax.annotation.Resource;
import java.io.Closeable;
import java.lang.reflect.Constructor;

public abstract class AbstractBaseProxy<I, C extends I> implements Closeable, ConfigHospAware {
	
	@Resource(name = "tsslTransportParams")
	private TSSLTransportFactory.TSSLTransportParameters tsslTransportParams;
	
    protected I target;

    private TTransport transport;

	private ConfigHosp configHosp;

    public void init(ConfigHospitalCloud configHospCloud, String serviceName) {
    	this.configHosp = new ConfigHosp(configHospCloud.getHospitalId(), configHospCloud.getApiKey());
        transport = createTransport(configHospCloud);
        TProtocol protocol = createProtocol(configHospCloud, transport, serviceName);
        this.target =  createClient(protocol);
    }

	private TTransport createTransport(ConfigHospitalCloud configHospCloud) {
		try {
			TTransport transport;
			if (configHospCloud.getTransportType() != null && configHospCloud.getTransportType() == 1) {
				// TSL Transport自动Open
				transport = TSSLTransportFactory.getClientSocket(configHospCloud.getIp(), configHospCloud.getPort(),
						configHospCloud.getTimeout(), tsslTransportParams);

			} else if(configHospCloud.getTransportType() != null && configHospCloud.getTransportType() == 2) {
				// HTTP
				transport = new THttpClient("http://" +configHospCloud.getIp()+ ":" + configHospCloud.getPort());
			} else if(configHospCloud.getTransportType() != null && configHospCloud.getTransportType() == 3) {
				// HTTPS
				transport = new THttpClient("https://" +configHospCloud.getIp()+ ":" + configHospCloud.getPort());
			} else {
				transport = new TSocket(configHospCloud.getIp(), configHospCloud.getPort(),
						configHospCloud.getTimeout());
				transport.open();
			}
			
			return transport;
		} catch (TTransportException e) {
			throw new Api2ConnectException(e.getMessage(), e);
		}
	}
    
    @Override
    public void close() {
        if (transport != null) {
            transport.close();
        }
    }

    protected abstract C createClient(TProtocol protocol);

    private TProtocol createProtocol(ConfigHospitalCloud configHospCloud, TTransport transport, String serviceName) {
        TProtocol protocol;
        if (configHospCloud.getDefaultProtocol() == 1) {
            protocol = new TBinaryProtocol(transport, true, true);
        } else {
            Class<TProtocol> protocolType = ReflectionUtils.findClass(configHospCloud.getProtocol());
            Constructor<TProtocol> protocolConstructor = ReflectionUtils
                .findConstructor(protocolType, TTransport.class);
            protocol = ReflectionUtils.newInstance(protocolConstructor, transport);
        }
        if(configHospCloud.getVersionId() > 1) {
        	protocol = new TMultiplexedProtocol(protocol, serviceName);
        }
        return protocol;
    }
    
    @Override
	public ConfigHosp getConfigHosp() {
		return configHosp;
	}

}
