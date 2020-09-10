package com.bolly.thrift.ifaceImpl;

import com.niox.api2.contract.SelfCheckService;
import com.niox.api2.contract.basedata.Result;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;

@Controller
public class SelfCheckServiceAction implements SelfCheckService.Iface {
    @Override
    public Result heartBeat() throws TException {
        Result result = new Result();
        System.out.println("roc selfCheckService");
        result.setCode("99");
        result.setMsg("NG");
        return result;
    }
}
