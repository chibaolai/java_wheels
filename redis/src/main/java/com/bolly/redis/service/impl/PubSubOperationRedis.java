package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

public class PubSubOperationRedis implements OperationRedis {
    @Override
    public void doOperation(Jedis jedis) {
        jedis.watch("");
    }
}
