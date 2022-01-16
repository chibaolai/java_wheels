package com.bolly.mongoboot.dao.impl;

import com.bolly.mongoboot.doc.BaseDoc;

/**
 * @author chibaolai
 */
public class DataDaoSupport<T extends BaseDoc<K>, K> {
	
	protected static final String ID = "_id";
	
	protected static final String ENABLE = "enable";
	
	protected static final String CREATE_BY = "createBy";
	
	protected static final String CREATE_DATE = "createDate";
	
	protected static final String UPDATE_BY = "updateBy";
	
	protected static final String UPDATE_DATE = "updateDate";

}
