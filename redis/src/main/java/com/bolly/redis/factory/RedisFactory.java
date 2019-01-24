package com.bolly.redis.factory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisFactory {
    /**
     * 普通连接
     * @return
     */
    public static Jedis getSampleRedisClient() {
        return new Jedis("localhost");
    }

    /**
     * 普通连接池
     * @return
     */
    public static Jedis getPoolRedisClient() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.set
        return null;
    }

    /**
     * 主从切换机制
     * @return
     */
    public static Jedis getSentinelRedis() {
//        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool();
        return null;
    }
}
