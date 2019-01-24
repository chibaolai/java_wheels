package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

/**
 * @author bolly
 */
public class OtherOperationRedis implements OperationRedis {
    /**
     * exists(key)
     * del(key)
     * type(key)
     * keys(pattern)
     * randomkey
     * rename(oldname, newname)
     * dbsize
     * expire
     * ttl
     * select(index)
     * move(key, dbindex)
     * flushdb
     * flushall
     *
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {
        jedis.select(0);
        jedis.flushDB();
    }
}
