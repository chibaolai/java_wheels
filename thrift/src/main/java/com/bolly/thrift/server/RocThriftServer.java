package com.bolly.thrift.server;

import com.bolly.thrift.client.proxy.SelfCheckServiceProxy;
import com.bolly.thrift.ifaceImpl.SelfCheckServiceAction;
import com.niox.api2.contract.SelfCheckService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;

@Component
public class RocThriftServer {
    private TServer server;
    private TServerTransport serverTransport;

    @Autowired
    private SelfCheckServiceAction selfCheckService;

    @PostConstruct
    public void postConstruct() throws TTransportException, FileNotFoundException, UnknownHostException {
//        TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
//        File keyStoreFile = ResourceUtils.getFile("classpath:keystore/api2truststore.jks");
//        String keyPass = "NioxPC2#1212123?";
//        params.setKeyStore(keyStoreFile.getAbsolutePath(), keyPass);
//        serverTransport = TSSLTransportFactory.getServerSocket(10028, 5000);

        serverTransport = new TServerSocket(10028);

        SelfCheckService.Processor<SelfCheckService.Iface> processor = new SelfCheckService.Processor<SelfCheckService.Iface>(selfCheckService);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
        args.processor(processor);
        args.protocolFactory(new TCompactProtocol.Factory());
//        args.requestTimeout(2);
        server = new TThreadPoolServer(args);
    }

    public void serve() {
        Thread thread = new Thread("thrift server") {
            @Override
            public void run() {
                try {
                    server.serve();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(-1);
                }
            }
        };
        thread.start();
    }

    @PreDestroy
    public void preDestroy() {
        server.stop();
        serverTransport.close();
        System.out.println("The roc Server has stopped.");
    }
}
