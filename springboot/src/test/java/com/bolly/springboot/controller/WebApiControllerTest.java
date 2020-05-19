package com.bolly.springboot.controller;

import com.bolly.sservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

public class WebApiControllerTest extends BaseControllerTest{

    @MockBean
    private UserService userService;

    @Test
    @Transactional
    public void userAdd() throws Exception {
        String json = "{\n" +
                "    \"name\": \"bolly\",\n" +
                "    \"age\": 35\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("/userAdd")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
//                .headers(httpHeaders)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.msgCode").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}