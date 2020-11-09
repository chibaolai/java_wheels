package com.bolly.mongo.mongo;

import org.mongojack.WriteResult;

import java.util.List;

public interface MongoDao<T extends IdAware<K>, K> {

	WriteResult<T, K> insert(T obj);

	WriteResult<T, K> insert(List<T> objs);

	WriteResult<T, K> update(T obj);

	WriteResult<T, K> remove(K id);

	T get(K id);
}
