package com.bolly.app.controller;

import com.bolly.sservice.entity.User;
import com.bolly.sservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API1 http接口
 */
@RestController
@Validated
public class WebApiController {


    @Autowired
    private UserService userService;

    /**
     * 心跳
     *
     * @return
     */
    @RequestMapping(value = "/ping")
    public String ping() {
        try {
            User user = userService.get(4);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "pong";
    }
}
