package com.bolly.stub;

import com.alibaba.fastjson.JSON;
import com.bolly.stub.client.SmsFeignClient;
import com.bolly.stub.req.SmsReq;
import com.bolly.stub.res.SmsRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private SmsFeignClient smsFeignClient;

    @Test
    public void feignStubMockTest() {
        SmsReq smsReq=new SmsReq();
        smsReq.setAccount("XXXXXXX");
        smsReq.setPassword("XXXXXXX");
        smsReq.setMsg("登录验证码:{$var}，请不要对非本人透露。");
        smsReq.setParams("18104096929,123456");
        smsReq.setReport("true");

        SmsRes send = smsFeignClient.send(smsReq);

        //打印结果
        System.out.println(JSON.toJSON(send));
    }

}

