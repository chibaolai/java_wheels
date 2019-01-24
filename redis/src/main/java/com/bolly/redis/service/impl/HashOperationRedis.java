package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

/**
 * @author bolly
 */
public class HashOperationRedis implements OperationRedis {

    /**
     * hset(key, field, value)：向名称为key的hash中添加元素field<—>value
     * hget(key, field)：返回名称为key的hash中field对应的value
     * hmget(key, field1, …,field N)：返回名称为key的hash中field i对应的value
     * hmset(key, field1, value1,…,field N, value N)：向名称为key的hash中添加元素field i<—>value i
     * hincrby(key, field, integer)：将名称为key的hash中field的value增加integer
     * hexists(key, field)：名称为key的hash中是否存在键为field的域
     * hdel(key, field)：删除名称为key的hash中键为field的域
     * hlen(key)：返回名称为key的hash中元素个数
     * hkeys(key)：返回名称为key的hash中所有键
     * hvals(key)：返回名称为key的hash中所有键对应的value
     * hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {
        jedis.hset("zoo","tiger","10");
        System.out.println("redis HashMap key=zoo,value="+jedis.hget("zoo","tiger"));
    }
}
