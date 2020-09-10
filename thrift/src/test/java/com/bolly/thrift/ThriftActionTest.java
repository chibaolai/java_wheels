package com.bolly.thrift;

import com.bolly.thrift.api.tf.req.PingReq;
import com.bolly.thrift.api.tf.resp.PingResp;
import com.bolly.thrift.ifaceImpl.ThriftAction;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThriftActionTest extends BaseActionTest{

    @Autowired
    private ThriftAction pingAction;

    @Test
    public void ping() throws TException {
        PingReq req = new PingReq();
//        PingResp resp = pingAction.ping(req);
        PingResp resp =  api.ping(req);
        assertEquals("ok",resp.getHeader().getMsg());
    }
}