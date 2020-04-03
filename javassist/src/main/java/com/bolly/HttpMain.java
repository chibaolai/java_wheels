package com.bolly;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * @author bolly
 */
public class HttpMain {

    public static void main(String[] args) throws IOException {
        new HttpMain().openHttpServer();
    }

    public void openHttpServer() throws IOException {
        InetSocketAddress addr = new InetSocketAddress(5555);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext("/server", new HttpMain.HttpHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 5555");
    }

    private class HttpHandler implements com.sun.net.httpserver.HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain;charset=UTF-8");
            exchange.sendResponseHeaders(200, 0);
            OutputStream responseBody = exchange.getResponseBody();
            // 输出c3p0状态
            responseBody.write(HttpMain.this.getStatus().getBytes());
            responseBody.flush();
            responseBody.close();
        }
    }

    public String getStatus() {
        Object source2 = System.getProperties().get("c3p0Source$agent");
        if (source2 == null) {
            return "未初始任何c3p0数据源";
        }
        System.out.println(source2);
        return source2.toString();
    }
}
