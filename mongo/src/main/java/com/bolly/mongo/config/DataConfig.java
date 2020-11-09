package com.bolly.mongo.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@ComponentScan(value = { "com.bolly.mongo" }, useDefaultFilters = false, includeFilters = @Filter({
		Component.class }) , excludeFilters = @Filter({ Controller.class, Configuration.class }) , lazyInit = true)
@Configuration
public class DataConfig {

	@Autowired
	private DataEnv env;

	@Lazy
	@Bean(name = "nioxDataMongoClient", destroyMethod = "close")
	public MongoClient mongoClient() {
		return new MongoClient(env.getMongoNioxdbURI());
	}
}
