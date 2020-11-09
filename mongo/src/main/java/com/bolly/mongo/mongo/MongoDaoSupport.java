package com.bolly.mongo.mongo;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class MongoDaoSupport<T extends IdAware<K>, K> implements MongoDao<T, K> {

	private MongoClient mongoClient;

	private Class<T> docType;

	private Class<K> keyType;

	private String databaseName;

	private String collectionName;

	@SuppressWarnings("unchecked")
	public MongoDaoSupport() {
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] pTypes = ((ParameterizedType) t).getActualTypeArguments();
			docType = (Class<T>) pTypes[0];
			keyType = (Class<K>) pTypes[1];
			org.mongojack.MongoCollection colAnn = docType.getAnnotation(org.mongojack.MongoCollection.class);
			if (colAnn != null) {
				collectionName = colAnn.name();
			}
		}

	}

	@PostConstruct
	void postConstruct() {
		databaseName = getDatabaseName();
	}

	@SuppressWarnings("deprecation")
	private DBCollection getDBCollection() {
		// mongojack当前还不支持mongo java driver 3.0中的getDatabase
		return mongoClient.getDB(databaseName).getCollection(collectionName);
	}

	protected abstract String getDatabaseName();

	protected MongoCollection<Document> getCollection(){
		return mongoClient.getDatabase(databaseName).getCollection(collectionName);
	}

	protected JacksonDBCollection<T, K> getJackCollection() {
		return JacksonDBCollection.wrap(getDBCollection(), docType, keyType);
	}

	@Override
	public WriteResult<T, K> insert(T obj) {
		return getJackCollection().insert(obj);
	}

	@Override
	public WriteResult<T, K> insert(List<T> objs) {
		return getJackCollection().insert(objs);
	}

	@Override
	public WriteResult<T, K> update(T obj) {
		return getJackCollection().updateById(obj.getId(), obj);
	}

	@Override
	public WriteResult<T, K> remove(K id) {
		return getJackCollection().removeById(id);
	}

	@Override
	public T get(K id) {
		return getJackCollection().findOneById(id);
	}

	public void setMongoClient(MongoClient mongoClient) {
		if(this.mongoClient != null) {
			throw new IllegalStateException("The mongoClient can't be set twice.");
		}
		this.mongoClient = mongoClient;
	}
}
