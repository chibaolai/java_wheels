package com.bolly.stub.client.stub;

import com.bolly.stub.client.SmsFeignClient;
import com.bolly.stub.req.SmsReq;
import com.bolly.stub.res.SmsRes;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author bolly
 */
@Primary
@Component
@ConditionalOnProperty(name = "stubmock.his.sms.mode", havingValue = "stub")
public class StubClient implements SmsFeignClient {
    private static final Logger LOG = LoggerFactory.getLogger(StubClient.class);

    @Override
    public SmsRes send(SmsReq request) {
        SmsRes yunxunSmsRespDto = new SmsRes();

        //模拟正常响应结果
        yunxunSmsRespDto.setCode("0");
        yunxunSmsRespDto.setFailNum("0");
        yunxunSmsRespDto.setSuccessNum("1");
        yunxunSmsRespDto.setMsgId(String.valueOf(RandomUtils.nextLong()));
        yunxunSmsRespDto.setTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        yunxunSmsRespDto.setErrorMsg("");

        String params = request.getParams();
        String[] paramSplit = StringUtils.split(params, ",");
        if (paramSplit[0].length() != 11) {
            //模拟错误响应结果
            yunxunSmsRespDto.setCode("107");
            yunxunSmsRespDto.setMsgId("");
            yunxunSmsRespDto.setErrorMsg("手机号码格式错误");
        }
        return yunxunSmsRespDto;
    }
}
