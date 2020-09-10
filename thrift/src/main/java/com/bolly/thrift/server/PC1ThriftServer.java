package com.bolly.thrift.server;

import com.bolly.thrift.ifaceImpl.ThriftAction;
import com.bolly.thrift.api.tf.Api;
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

@Component
public class PC1ThriftServer {
    private TServer server;
    private TServerTransport serverTransport;

    @Autowired
    private ThriftAction thriftAction;

    @PostConstruct
    public void postConstruct() throws TTransportException {
        TCompactProtocol.Factory protocolFactory = new TCompactProtocol.Factory();
        serverTransport = new TServerSocket(37938);
        Api.Processor<Api.Iface> processor = new Api.Processor<Api.Iface>(thriftAction);
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
        args.processor(processor);
        args.protocolFactory(protocolFactory);
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
        System.out.println("The API1 Server has stopped.");
    }
}
