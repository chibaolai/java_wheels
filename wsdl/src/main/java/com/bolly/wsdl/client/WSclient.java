package com.bolly.wsdl.client;

import com.bolly.wsdlserver.WSserverService;

public class WSclient {
    public static void main(String[] args) {
        WSserverService sserverService = new WSserverService();
        System.out.println(sserverService.getWSserverPort().ping("world"));

    }
}
