package com.bolly.jdk.proxy;

public interface Action<Req, Resp> {
    void validate(Req req);

    Resp execute(Req req);
}
