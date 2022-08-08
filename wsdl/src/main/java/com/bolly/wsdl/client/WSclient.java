package com.bolly.wsdl.client;

import com.bolly.wsdlserver.WSserver;
import com.bolly.wsdlserver.WSserverService;

import javax.xml.ws.BindingProvider;
import java.util.Map;

public class WSclient {
    public static void main(String[] args) {
        WSserverService sserverService = new WSserverService();
        WSserver client = sserverService.getWSserverPort();
        Map<String,Object> requestContext = ((BindingProvider)client).getRequestContext();
        requestContext.put("com.sun.xml.internal.ws.connect.timeout",2*1000);
        requestContext.put("com.sun.xml.internal.ws.request.timeout",2*1000);
        System.out.println(client.ping("world"));

    }
}
