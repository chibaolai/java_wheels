package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TransactionOperationRedis implements OperationRedis {
    @Override
    public void doOperation(Jedis jedis) {
        jedis.multi();
        jedis.hsetnx("order","orderNo","1234567890");
    }
}
