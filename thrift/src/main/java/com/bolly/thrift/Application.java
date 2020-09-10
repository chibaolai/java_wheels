package com.bolly.thrift;

import com.bolly.thrift.event.ApplicationStartedEventListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder();
        applicationBuilder.listeners(new ApplicationStartedEventListener());
        applicationBuilder.sources(Application.class);
        applicationBuilder.run(args);
    }
}
