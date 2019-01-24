package com.bolly.redis.controller;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

@RestController
public class Controller {

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("test")
    public void checkLock() {
        String orderNo = "orderNo";
        Lock lock = redisson.getLock(orderNo);
        lock.lock();

        int num = Integer.parseInt(stringRedisTemplate.opsForValue().get("mylock"));
        if(num > 0) {
            stringRedisTemplate.opsForValue().set("mylock",(num -1)+"");
            System.out.println("扣减成功，库存剩余"+(num -1));
        }else {
            System.out.println("扣减失败，库存不足");
        }

        lock.unlock();
    }
}
