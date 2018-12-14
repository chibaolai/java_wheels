package com.bolly.springboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Result<T> {

    private int status = 0;

    private String msg;

    @JsonInclude(Include.NON_NULL)
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(Status status) {
        this.status = status.getStatus();
        this.msg = status.getMsg();
    }

    public static <T> Result<T> data() {
        return new Result<T>();
    }

    public static <T> Result<T> data(T data) {
        Result<T> result = new Result<T>();
        result.setStatus(Status.OK);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> status(Status status) {
        Result<T> result = new Result<T>();
        result.setStatus(status);
        return result;
    }

    public static <T> Result<T> status(Status status, String validateMessage) {
        Result<T> result = new Result<T>();
        result.setStatus(status);
        result.setMsg(validateMessage);
        return result;
    }

    public static <T> Result<T> status(Status status, T data) {
        Result<T> result = new Result<T>();
        result.setStatus(status);
        result.setData(data);
        return result;
    }
}
