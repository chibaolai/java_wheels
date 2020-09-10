package com.bolly.springboot;

import com.bolly.springboot.event.*;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author bolly
 */
@ComponentScans({@ComponentScan(basePackages = "com.bolly.springboot"),@ComponentScan(basePackages = "com.bolly.sservice")})
@SpringBootApplication
@EnableAdminServer
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder();
        applicationBuilder.listeners(new ApplicationStartingEventListener(),new ApplicationEnvironmentPreparedEventListener(),new ApplicationPreparedEventListener(),new ApplicationStartedEventListener(),new ApplicationReadyEventListener(),new ApplicationFailedEventListener());
        applicationBuilder.sources(Application.class);
        applicationBuilder.run(args);
    }
}
