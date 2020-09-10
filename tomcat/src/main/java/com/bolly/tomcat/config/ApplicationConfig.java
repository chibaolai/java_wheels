package com.bolly.tomcat.config;

import com.bolly.tomcat.jasypt.EncryptPropertySourceRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 主配置
 */
@ComponentScan(value = {"com.bolly.tomcat"})
@Configuration
public class ApplicationConfig {
    /**
     * 属性源
     * @return
     */
    @Bean
    public static EncryptPropertySourceRegister encryptPropertySourceRegister() {
        EncryptPropertySourceRegister register = new EncryptPropertySourceRegister();
        register.setPropertyFileLocation("classpath:/tomcat.properties");
        return register;
    }
}
