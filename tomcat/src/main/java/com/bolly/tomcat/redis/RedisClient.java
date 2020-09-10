package com.bolly.tomcat.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface RedisClient {

	void set(String key, String value);

	void set(String key, Object value);

	void set(String key, String value, long timeout, TimeUnit unit);

	void set(String key, Object value, long timeout, TimeUnit unit);

	String get(String key);

	<T> T get(String key, Class<T> targetType);
	
	<T> T get(String key, Class<T> targetType, Class<?>... parameterTypes);
	
	void delete(String key);
	
	void delete(Collection<String> keys);
	
	StringRedisTemplate getClient();

}
