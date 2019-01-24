package com.bolly.redis.service;

import redis.clients.jedis.Jedis;

public interface OperationRedis {
    public void doOperation(Jedis jedis);
}
