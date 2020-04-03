package com.bolly.jdk.proxy.service;

public interface Action<Req, Resp> {
    void validate(Req req);

    Resp execute(Req req);
}
