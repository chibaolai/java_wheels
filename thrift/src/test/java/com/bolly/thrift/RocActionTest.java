package com.bolly.thrift;

import com.bolly.thrift.api.tf.req.PingReq;
import com.bolly.thrift.api.tf.resp.PingResp;
import com.bolly.thrift.client.proxy.SelfCheckServiceProxy;
import com.bolly.thrift.ifaceImpl.ThriftAction;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocActionTest extends RocBaseActionTest{

    @Autowired
    private SelfCheckServiceProxy selfCheckServiceProxy;

    @Test
    public void ping() throws TException {
        selfCheckServiceProxy.heartBeat();
    }
}