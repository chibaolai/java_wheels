package com.bolly.thrift.ifaceImpl;

import com.bolly.thrift.api.tf.Api;
import com.bolly.thrift.api.tf.req.PingReq;
import com.bolly.thrift.api.tf.resp.PingResp;
import com.bolly.thrift.service.PingService;
import com.bolly.thrift.service.impl.PingServiceImpl;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ThriftAction implements Api.Iface {
    @Autowired
    private PingService pingService;


    @Override
    public PingResp ping(PingReq req) throws TException {
        return pingService.ping(req);
    }
}
