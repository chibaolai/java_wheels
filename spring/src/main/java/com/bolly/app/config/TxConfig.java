package com.bolly.app.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@ComponentScan(basePackages = "com.bolly.app")
@Import({DataSourceConfig.class})
@Configuration
public class TxConfig {
//    @Bean
//    public static PropertiesFactoryBean encryptPropertySourceRegister() {
//        PropertiesFactoryBean register = new PropertiesFactoryBean();
//        register.setLocations("classpath:/app.properties");
//        return register;
//    }
}
