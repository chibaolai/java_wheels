package com.bolly.springboot.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.bolly.support.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author bolly
 */
@Component
@Order(1)
public class ApplicationStartupRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);
    @Autowired
    private ApplicationArguments arguments;

    @Override
    public void run(String... args) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("启动参数{}",JacksonUtils.marshal(arguments.getSourceArgs()));
        }
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);

    }
}
