package com.bolly.thrift.service.impl;

import com.bolly.thrift.api.tf.RespHeader;
import com.bolly.thrift.api.tf.req.PingReq;
import com.bolly.thrift.api.tf.resp.PingResp;
import com.bolly.thrift.client.proxy.SelfCheckServiceProxy;
import com.bolly.thrift.service.PingService;
import com.niox.api2.contract.basedata.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingServiceImpl implements PingService {

    @Autowired
    private SelfCheckServiceProxy selfCheckServiceProxy;
//    private Roc roc;

    @Override
    public PingResp ping(PingReq req) {
        Result result = selfCheckServiceProxy.heartBeat();

        PingResp resp = new PingResp();
        RespHeader respHeader = new RespHeader();
        respHeader.setMsg(result.getMsg());
        resp.setHeader(respHeader);
        return resp;
    }
}
