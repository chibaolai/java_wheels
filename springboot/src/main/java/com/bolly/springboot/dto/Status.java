package com.bolly.springboot.dto;

public enum Status implements IdNameEnumable{
	SRV_UPDATE_ERROR(-7, "业务数据更新失败")
    ,REPEATE_ERROR(-6, "重复提交错误")
	,NEED_SSL(-5, "NEED SSL")
    , INVALID_TIME_WINDOW(-4, "请检查手机的系统时间是否已设置为标准时间")
    , INVALID_TOKEN(-3, "您的帐号登录已失效,请重新登录")
    , SERVER_VALIDATION_ERROR(-2, "数据输入有误")
    , SERVER_ERROR(-1, "服务端错误")
    , OK(0, "")
    , REQ_CODE_ERROR(1, "验证码发送失败");
	
    private int st;

    private String msg;

    private Status(int status, String msg) {
        this.st = status;
        this.msg = msg;
    }

    public int getStatus() {
        return st;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 检查给定的条件是否满足,如果不满足将抛出{@link StatusException}
     * @param expression
     * @throws StatusException
     */
    public void check(boolean expression) {
        if (!expression) {
            throw new StatusException(this);
        }
    }
    
    public StatusException exception() {
    	return new StatusException(this);
    }

    /**
     * 直接抛出{@link StatusException}
     * @throws StatusException
     */
    public void throwOut()  {
        throw new StatusException(this);
    }

    /**
     * 直接抛出{@link StatusException}
     * @throws StatusException
     */
    public void throwOut(String msg) {
        throw new StatusException(this, msg);
    }

    public static class StatusException extends RuntimeException {

        private static final long serialVersionUID = 8212993805692858774L;

        private final Status status;


        public StatusException(Status status) {
            this.status = status;
        }

        public StatusException(Status status, String msg) {
            super(msg);
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }

    }

    @Override
    public int getId() {
        return st;
    }

    @Override
    public String getName() {
        return super.name();
    }

    public static Status parseById(int id) {
        return (Status) EnumUtils.parseById(id, Status.class);
    }
}
