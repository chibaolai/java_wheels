package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

public class SortedSetOperationRedis implements OperationRedis {

    /**
     * zadd(key, score, member)：向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序。
     * zrem(key, member) ：删除名称为key的zset中的元素member
     * zincrby(key, increment, member) ：如果在名称为key的zset中已经存在元素member，则该元素的score增加increment；否则向集合中添加该元素，其score的值为increment
     * zrank(key, member) ：返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
     * zrevrank(key, member) ：返回名称为key的zset（元素已按score从大到小排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
     * zrange(key, start, end)：返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
     * zrevrange(key, start, end)：返回名称为key的zset（元素已按score从大到小排序）中的index从start到end的所有元素
     * zrangebyscore(key, min, max)：返回名称为key的zset中score >= min且score <= max的所有元素
     * zcard(key)：返回名称为key的zset的基数
     * zscore(key, element)：返回名称为key的zset中元素element的score
     * zremrangebyrank(key, min, max)：删除名称为key的zset中rank >= min且rank <= max的所有元素
     * zremrangebyscore(key, min, max) ：删除名称为key的zset中score >= min且score <= max的所有元素
     * zunionstore / zinterstore(dstkeyN, key1,…,keyN, WEIGHTS w1,…wN, AGGREGATE SUM|MIN|MAX)：对N个zset求并集和交集，并将最后的集合保存在dstkeyN中。对于集合中每一个元素的score，在进行AGGREGATE运算前，都要乘以对于的WEIGHT参数。如果没有提供WEIGHT，默认为1。默认的AGGREGATE是SUM，即结果集合中元素的score是所有集合对应元素进行 SUM运算的值，而MIN和MAX是指，结果集合中元素的score是所有集合对应元素中最小值和最大值。
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {

    }
}
