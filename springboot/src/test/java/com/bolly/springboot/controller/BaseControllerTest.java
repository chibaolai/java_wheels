package com.bolly.springboot.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    protected MockMvc mvc;

    protected MockHttpSession session;
    protected HttpHeaders httpHeaders;


    @BeforeAll
    public static void init(){
        System.out.println("测试开始-----------");
    }

    @AfterAll
    public static void after(){
        System.out.println("测试结束-----------");
    }
}
