package com.bolly.tomcat.config;

import com.bolly.tomcat.redis.DefaultRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@EnableCaching(proxyTargetClass = true)
@Configuration
public class RedisConfig {

    public static final String REDIS_PWD_KEY = "redis.passsword";
    public static final String REDIS_HOSTNAME = "redis.hostname";
    public static final String REDIS_PORT = "redis.port";
    public static final String REDIS_MAX_TOTAL = "redis.pool.maxTotal";
    public static final String REDIS_MAX_IDLE = "redis.pool.maxIdle";
    public static final String REDIS_MAX_WAIT_MILLIS = "redis.pool.maxWaitMillis";
    public static final String REDIS_DATABASE_INDEX = "redis.database.index";

    @Autowired
    private Environment env;

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(env.getProperty(REDIS_MAX_TOTAL, int.class));
        poolConfig.setMaxIdle(env.getProperty(REDIS_MAX_IDLE, int.class));
        poolConfig.setMaxWaitMillis(env.getProperty(REDIS_MAX_WAIT_MILLIS, long.class));
        poolConfig.setMinEvictableIdleTimeMillis(300000);
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName(env.getProperty(REDIS_HOSTNAME));
        connectionFactory.setPort(env.getProperty(REDIS_PORT, int.class));
        connectionFactory.setDatabase(env.getProperty(REDIS_DATABASE_INDEX, int.class));
        connectionFactory.setPassword(env.getProperty(REDIS_PWD_KEY));
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory());
    }
    
    @Bean(name = "redisClient")
    public DefaultRedisClient redisClient() {
    	return new DefaultRedisClient(stringRedisTemplate());
    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
