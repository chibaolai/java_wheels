package com.bolly.rsa.controller;

import com.bolly.rsa.bean.User;
import com.bolly.rsacore.annotation.Decrypt;
import com.bolly.rsacore.annotation.Encrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    /**
     * 对返回值加密
     * @return
     */
    @Encrypt
    @GetMapping("/test01")
    public User test01(){
        User user = new User();
        user.setName("bolly");
        user.setAge(18);
        return user;
    }

    /**
     * 对传过来的加密参数进行解密
     * @param user
     * @return
     */
    @Decrypt
    @PostMapping("/test02")
    public String test02(@RequestBody User user){
        return user.toString();
    }

}
