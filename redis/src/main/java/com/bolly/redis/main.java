package com.bolly.redis;

import com.bolly.redis.factory.RedisFactory;
import com.bolly.redis.service.OperationRedis;
import com.bolly.redis.service.impl.HashOperationRedis;
import com.bolly.redis.service.impl.OtherOperationRedis;
import redis.clients.jedis.Jedis;

public class main {

    public static void main(String[] args) {
        Jedis jedis = RedisFactory.getSampleRedisClient();

//        OperationRedis operationRedis = new OtherOperationRedis();
//        OperationRedis operationRedis = new StringOperationRedis();
        OperationRedis operationRedis = new HashOperationRedis();
//        OperationRedis operationRedis = new ListOperationRedis();
//        OperationRedis operationRedis = new SetOperationRedis();
//        OperationRedis operationRedis = new SortedSetOperationRedis();
//        OperationRedis operationRedis = new PubSubOperationRedis();
//        OperationRedis operationRedis = new TransactionOperationRedis();
//        OperationRedis operationRedis = new SynOperationRedis();
        operationRedis.doOperation(jedis);
        //+++++++++++++HyperLogLog start++++++++
        //+++++++++++++HyperLogLog end++++++++++
    }
}
