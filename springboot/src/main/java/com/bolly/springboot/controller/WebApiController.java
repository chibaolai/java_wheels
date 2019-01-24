package com.bolly.springboot.controller;

import com.bolly.springboot.dto.Result;
import com.bolly.springboot.req.PingReq;
import com.bolly.springboot.res.PingResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API1 http接口
 */
@RestController
@Validated
public class WebApiController {
    private static Logger LOGGER = LoggerFactory.getLogger(WebApiController.class);
    /**
     * 心跳
     *
     * @return
     */
    @RequestMapping(value = "/ping")
    public Result<PingResp> ping(@RequestBody PingReq req) {
        PingResp ret = new PingResp();
        LOGGER.info("");
        return Result.data(ret);
    }
}
