package com.bolly.stub.client.mock;

import com.bolly.stub.client.SmsFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author bolly
 */
@Primary
@Component
@ConditionalOnProperty(name = "stubmock.his.sms.mode", havingValue = "mock")
@FeignClient(name = "smsclient-mock", url = "${stubmock.his.sms.mockUrl}" ,path = "/")
public interface MockClient extends SmsFeignClient {

}
