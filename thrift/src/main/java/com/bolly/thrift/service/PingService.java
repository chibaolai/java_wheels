package com.bolly.thrift.service;

import com.bolly.thrift.api.tf.req.PingReq;
import com.bolly.thrift.api.tf.resp.PingResp;

public interface PingService {

    PingResp ping(PingReq req);
}
