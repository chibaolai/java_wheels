package com.bolly.thrift.config;

import org.apache.thrift.transport.TSSLTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

//@Configuration
public class RocConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocConfig.class);


    @Bean(name = "tsslTransportParams")
    public TSSLTransportFactory.TSSLTransportParameters tsslTransportParams() {
        TSSLTransportFactory.TSSLTransportParameters params = null;
        try {
            params = new TSSLTransportFactory.TSSLTransportParameters();
            String api2TrustoreUrl = ResourceUtils.getURL("classpath:keystore/api2truststore.jks").toString();
            params.setTrustStore(api2TrustoreUrl, "NioxPC2#1212123?");
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return params;
    }

}