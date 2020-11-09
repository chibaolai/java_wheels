package com.bolly.mongo.entity;

import org.mongojack.Id;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

@MongoCollection(name = "com_sms_detail")
public class ComSmsDetail extends BaseDoc<String> {
    @Id
    @ObjectId
    private String id;
    /**
     * 医院ID
     */
    private int hospId;
    /**
     * 医院名
     */
    private String hospName;
    /**
     * 短信ID(PC2)
     */
    private String messageId;
    /**
     * 短信类型
     */
    private String msgType;
    /**
     * 接收者
     */
    private String userName;
    /**
     * 接收电话号码
     */
    private String phoneNo;
    /**
     * 发送次数
     */
    private int sendTimes;
    /**
     * 短信Title(医院标签)
     */
    private String hospSign;
    /**
     * 发送短信内容
     */
    private String msgBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public int getHospId() {
        return hospId;
    }

    public void setHospId(int hospId) {
        this.hospId = hospId;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getSendTimes() {
        return sendTimes;
    }

    public void setSendTimes(int sendTimes) {
        this.sendTimes = sendTimes;
    }

    public String getHospSign() {
        return hospSign;
    }

    public void setHospSign(String hospSign) {
        this.hospSign = hospSign;
    }

}
