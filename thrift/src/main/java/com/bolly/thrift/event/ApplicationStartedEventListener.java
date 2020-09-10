package com.bolly.thrift.event;

import com.bolly.thrift.server.PC1ThriftServer;
import com.bolly.thrift.server.RocThriftServer;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        event.getApplicationContext().getBean(PC1ThriftServer.class).serve();
        event.getApplicationContext().getBean(RocThriftServer.class).serve();
    }
}
