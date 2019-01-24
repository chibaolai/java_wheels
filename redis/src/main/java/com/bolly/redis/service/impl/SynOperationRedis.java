package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class SynOperationRedis implements OperationRedis {
    @Override
    public void doOperation(Jedis jedis) {
        Pipeline pipeline = jedis.pipelined();
        pipeline.syncAndReturnAll();
    }
}
