package com.bolly.redis.service.impl;

import com.bolly.redis.service.OperationRedis;
import redis.clients.jedis.Jedis;

/**
 * @author bolly
 */
public class ListOperationRedis implements OperationRedis {

    /**
     * rpush(key, value)：在名称为key的list尾添加一个值为value的元素
     * lpush(key, value)：在名称为key的list头添加一个值为value的 元素
     * llen(key)：返回名称为key的list的长度
     * lrange(key, start, end)：返回名称为key的list中start至end之间的元素（下标从0开始，下同）
     * ltrim(key, start, end)：截取名称为key的list，保留start至end之间的元素
     * lindex(key, index)：返回名称为key的list中index位置的元素
     * lset(key, index, value)：给名称为key的list中index位置的元素赋值为value
     * lrem(key, count, value)：删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，count>0      从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
     * lpop(key)：返回并删除名称为key的list中的首元素
     * rpop(key)：返回并删除名称为key的list中的尾元素
     * blpop(key1, key2,… key N, timeout)：lpop 命令的block版本。即当timeout为0时，若遇到名称为key i的list不存在或该list为空，则命令结束。如果 timeout>0，则遇到上述情况时，等待timeout秒，如果问题没有解决，则对key i+1开始的list执行pop操作。
     * brpop(key1, key2,… key N, timeout)：rpop的block版本。参考上一命令。
     * rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
     * @param jedis
     */
    @Override
    public void doOperation(Jedis jedis) {
        jedis.del("fruit");
        jedis.lpush("fruit","apple","banana","grape","pear");
        for (int i = 0 ; i<jedis.llen("fruit");i++) {
            System.out.println("redis List key=fruit,value=" + jedis.lindex("fruit",i));
        }
    }
}
