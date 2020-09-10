package com.bolly.tomcat.controller;

import com.bolly.support.dto.Result;
import com.bolly.tomcat.res.PingResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebapiController {

    @RequestMapping(value = "/ping")
    public Result<PingResp> ping() {
        PingResp ret = new PingResp();
        return Result.data(ret);
    }
}
