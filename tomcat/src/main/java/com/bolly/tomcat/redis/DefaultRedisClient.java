package com.bolly.tomcat.redis;

import com.bolly.support.utils.JacksonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class DefaultRedisClient implements RedisClient {

	private StringRedisTemplate redisTemplate;

	public DefaultRedisClient(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, JacksonUtils.marshal(value));
	}

	@Override
	public void set(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, JacksonUtils.marshal(value), timeout, unit);
	}

	@Override
	public <T> T get(String key, Class<T> targetType) {
		String val = redisTemplate.opsForValue().get(key);
		return JacksonUtils.unmarshal(val, targetType);
	}
	
	@Override
	public <T> T get(String key, Class<T> targetType, Class<?>... parameterTypes) {
		String val = redisTemplate.opsForValue().get(key);
		return JacksonUtils.unmarshal(val, targetType, parameterTypes);
	}

	@Override
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, String value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void delete(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public StringRedisTemplate getClient() {
		return redisTemplate;
	}
}
