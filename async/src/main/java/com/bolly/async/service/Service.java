package com.bolly.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final static Logger logger = LoggerFactory.getLogger(Service.class);

    /**
     * 同步
     * @throws InterruptedException
     */
    public void synchroEvent() throws InterruptedException{
        Thread.sleep(3000);
        Thread.sleep(2000);
        Thread.sleep(1000);
        logger.info("同步方法执行完了");
    }

    /**
     * 异步
     * @throws InterruptedException
     */
    @Async
    public void asyncEvent() throws InterruptedException{
        Thread.sleep(3000);
        Thread.sleep(2000);
        Thread.sleep(1000);
        logger.info("异步方法执行完了");
    }
}
