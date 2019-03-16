package com.bolly.wsdlserver;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class WSserver {

    public String ping(String word) {
        return "hello "+ word;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/Service/WSserver", new WSserver());
        System.out.println("Publish Success");
    }
}
