package com.bolly.app.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource("classpath:/app.properties")
public class DataSourceConfig {
    public static final String DATA_SOURCE_URL = "dataSource.url";

    public static final String DATA_SOURCE_USERNAME = "dataSource.username";

    public static final String DATA_SOURCE_PWD_KEY = "dataSource.password";

    public static final String DATA_SOURCE_DRIVER = "dataSource.driver";

    public static final String DATA_SOURCE_MAX_ACTIVE = "dataSource.maxActive";

    public static final String DATA_SOURCE_MAX_IDLE = "dataSource.maxIdle";

    public static final String DATA_SOURCE_VALIDATION_QUERY = "dataSource.validationQuery";

    @Autowired
    private Environment env;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(env.getProperty(DATA_SOURCE_DRIVER));
        dataSource.setUrl(env.getProperty(DATA_SOURCE_URL));
        dataSource.setUsername(env.getProperty(DATA_SOURCE_USERNAME));
        dataSource.setPassword(env.getProperty(DATA_SOURCE_PWD_KEY));
        dataSource.setMaxActive(env.getProperty(DATA_SOURCE_MAX_ACTIVE, Integer.class));
        dataSource.setMaxIdle(env.getProperty(DATA_SOURCE_MAX_IDLE, Integer.class));
        dataSource.setMinIdle(0);
        dataSource.setDefaultAutoCommit(false);
        // 连接Idle超过60 seconds会被移除
        dataSource.setMinEvictableIdleTimeMillis(60000);
        // 每6 seconds检查一次
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        dataSource.setValidationQuery(env.getProperty(DATA_SOURCE_VALIDATION_QUERY));
        dataSource.setTestWhileIdle(true);
        dataSource.setJmxEnabled(true);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }
}
