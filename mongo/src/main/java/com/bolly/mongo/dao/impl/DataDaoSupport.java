package com.bolly.mongo.dao.impl;

import com.bolly.mongo.config.DataEnv;
import com.bolly.mongo.entity.BaseDoc;
import com.bolly.mongo.mongo.MongoDaoSupport;
import com.mongodb.MongoClient;
import org.mongojack.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class DataDaoSupport<T extends BaseDoc<K>, K> extends MongoDaoSupport<T, K> {

	protected static final String ID = "_id";

	protected static final String ENABLE = "enable";

	protected static final String CREATE_BY = "createBy";

	protected static final String CREATE_DATE = "createDate";

	protected static final String UPDATE_BY = "updateBy";

	protected static final String UPDATE_DATE = "updateDate";

	@Autowired
	private DataEnv env;

	@Override
	protected final String getDatabaseName() {
		return env.getMongoNioxdbURI().getDatabase();
	}

	@Autowired
	@Override
	public void setMongoClient(MongoClient mongoClient) {
		super.setMongoClient(mongoClient);
	}

	@Override
	public WriteResult<T, K> insert(T obj) {
		auditCreate(obj);
		return super.insert(obj);
	}

	@Override
	public WriteResult<T, K> insert(List<T> objs) {
		if (objs != null) {
			for (T obj : objs) {
				auditCreate(obj);
			}
		}
		return super.insert(objs);
	}

	@Override
	public WriteResult<T, K> update(T obj) {
		auditUpdate(obj);
		return super.update(obj);
	}

	protected final void auditCreate(BaseDoc<K> doc) {
		if (doc == null) {
			return;
		}
		doc.setEnable(true);
		String acctId = currentAccountId();
		doc.setCreateBy(acctId);
		doc.setUpdateBy(acctId);
		Date currentTime = new Date();
		doc.setCreateDate(currentTime);
		doc.setUpdateDate(currentTime);
	}

	protected final void auditUpdate(BaseDoc<K> doc) {
		if (doc == null) {
			return;
		}
		doc.setUpdateBy(currentAccountId());
		doc.setUpdateDate(new Date());
	}

	protected final String currentAccountId() {
		Object acctId = "bolly";
		if (acctId != null) {
			return acctId.toString();
		}
		return null;
	}

}
