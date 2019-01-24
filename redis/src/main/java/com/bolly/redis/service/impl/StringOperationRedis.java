package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;


/**
 * @author bolly
 */
public class StringOperationRedis implements OperationRedis {
    /**
     * set(key, value)：给数据库中名称为key的string赋予值value
     * get(key)：返回数据库中名称为key的string的value
     * getset(key, value)：给名称为key的string赋予上一次的value
     * mget(key1, key2,…, key N)：返回库中多个string（它们的名称为key1，key2…）的value
     * setnx(key, value)：如果不存在名称为key的string，则向库中添加string，名称为key，值为value
     * setex(key, time, value)：向库中添加string（名称为key，值为value）同时，设定过期时间time
     * mset(key1, value1, key2, value2,…key N, value N)：同时给多个string赋值，名称为key i的string赋值value i
     * msetnx(key1, value1, key2, value2,…key N, value N)：如果所有名称为key i的string都不存在，则向库中添加string，名称key i赋值为value i
     * incr(key)：名称为key的string增1操作
     * incrby(key, integer)：名称为key的string增加integer
     * decr(key)：名称为key的string减1操作
     * decrby(key, integer)：名称为key的string减少integer
     * append(key, value)：名称为key的string的值附加value
     * substr(key, start, end)：返回名称为key的string的value的子串
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {
        jedis.set("token","123456");
        System.out.println("redis String key=token,value="+jedis.get("token"));
    }
}
