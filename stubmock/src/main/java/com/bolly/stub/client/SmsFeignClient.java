package com.bolly.stub.client;

import com.bolly.stub.req.SmsReq;
import com.bolly.stub.res.SmsRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author bolly
 */
@FeignClient(name = "smsclient", url = "${sms.url}", primary = false)
public interface SmsFeignClient {

    @PostMapping("/msg/variable/json")
    SmsRes send(@RequestBody SmsReq request);
}
