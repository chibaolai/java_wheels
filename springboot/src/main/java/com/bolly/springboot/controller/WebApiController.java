package com.bolly.springboot.controller;

import com.bolly.springboot.req.UserAddReq;
import com.bolly.springboot.res.UserAddResp;
import com.bolly.sservice.entity.User;
import com.bolly.sservice.service.UserService;
import com.bolly.support.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 新增用户
     *
     * @return
     */
    @RequestMapping(value = "/userAdd")
    public Result<UserAddResp> userAdd(@RequestBody UserAddReq req) {
        UserAddResp ret = new UserAddResp();
        User arg = new User();
        arg.setName(req.getName());
        arg.setAge(req.getAge());
        userService.insert(arg);
        ret.setName(req.getName()+":"+Thread.currentThread().getName());
        return Result.data(ret);
    }
}
