package com.bolly.springboot.controller;

import com.bolly.springboot.dto.Result;
import com.bolly.springboot.req.PingReq;
import com.bolly.springboot.res.PingResp;
import com.bolly.sservice.entity.User;
import com.bolly.sservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
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


    @Autowired
    private UserService userService;

    /**
     * 心跳
     *
     * @return
     */
    @RequestMapping(value = "/ping")
    public Result<PingResp> ping(@RequestBody PingReq req) {
        PingResp ret = new PingResp();
        LOGGER.info("");
        try {
            User user = userService.get(4);
//            int count = userService.queryForInt();
            Thread.sleep(4000);
            ret.setName(req.getName()+":"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.data(ret);
    }
}
