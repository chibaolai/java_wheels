package com.bolly.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerListener {

    @KafkaListener(topics="reg")
    public void onMessage(String message){
        System.out.println(message);
    }
}