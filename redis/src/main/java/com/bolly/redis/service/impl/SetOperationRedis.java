package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

/**
 * @author bolly
 */
public class SetOperationRedis implements OperationRedis {
    /**
     * sadd(key, member)：向名称为key的set中添加元素member
     * srem(key, member) ：删除名称为key的set中的元素member
     * spop(key) ：随机返回并删除名称为key的set中一个元素
     * smove(srckey, dstkey, member) ：将member元素从名称为srckey的集合移到名称为dstkey的集合
     * scard(key) ：返回名称为key的set的基数
     * sismember(key, member) ：测试member是否是名称为key的set的元素
     * sinter(key1, key2,…key N) ：求交集
     * sinterstore(dstkey, key1, key2,…key N) ：求交集并将交集保存到dstkey的集合
     * sunion(key1, key2,…key N) ：求并集
     * sunionstore(dstkey, key1, key2,…key N) ：求并集并将并集保存到dstkey的集合
     * sdiff(key1, key2,…key N) ：求差集
     * sdiffstore(dstkey, key1, key2,…key N) ：求差集并将差集保存到dstkey的集合
     * smembers(key) ：返回名称为key的set的所有元素
     * srandmember(key) ：随机返回名称为key的set的一个元素
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {
        jedis.sadd("car","bus","taxi","train");
//        for(int i = 0 ; i< jedis.s)
    }
}
