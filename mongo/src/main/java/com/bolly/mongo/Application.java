package com.bolly.mongo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author bolly
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder();
        applicationBuilder.sources(Application.class);
        applicationBuilder.run(args);
    }
}
