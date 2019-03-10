package com.bolly.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@ComponentScan(value = {"com.bolly.app","com.bolly.sservice"})
@Configuration
@Import({DataSourceConfig.class,TxConfig.class})
public class ApplicationConfig {
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(5000);
        // https://jira.spring.io/browse/SPR-11199
        requestFactory.setBufferRequestBody(false);
        return new RestTemplate(requestFactory);
    }
}
