package com.bolly.mongo.config;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientURI;

@Component
public class DataEnv {

	@Autowired
	private Environment env;

	private MongoClientURI mongoNioxdbURI;

	@PostConstruct
	void postConstruct() {
		String uri = env.getProperty("mongo.nioxdb.uri");
		mongoNioxdbURI = new MongoClientURI(uri);
	}

	public MongoClientURI getMongoNioxdbURI() {
		return mongoNioxdbURI;
	}

}
