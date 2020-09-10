package com.bolly.thrift.entity;

import java.util.Date;

/**
 * PC2端API服务的地址等信息
 */
public class ConfigHospitalCloud {

    /**
     */
    private Integer id;

    /**
     * 医院ID
     */
    private Integer hospitalId;

    /**
     * 私有云版本ID
     */
    private Integer versionId;

    /**
     * 私有云版本名
     */
    private String versionName;

    /**
     * 私有云IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 服务的超时时间,毫秒
     */
    private Integer timeout;

    /**
     * 协议全名
     */
    private String protocol;

    /**
     * 使用默认协议
     */
    private Integer defaultProtocol;

    /**
     * 出口IP
     */
    private String outingIp;

    /**
     * 0:默认连接 1:TSL加密连接
     */
    private Integer transportType;

    /**
     * Api2 v2.x需要的秘钥
     */
    private String apiKey;

    /**
     * 0:大象云端支付 1:院端RealOnePay
     */
    private Integer payChannel;

    /**
     */
    private Integer enable;

    /**
     */
    private String createBy;

    /**
     */
    private Date createDate;

    /**
     */
    private String updateBy;

    /**
     */
    private Date updateDate;

    /**
     * <pre>
     * </pre>
     * 
     * @return the id
     */
    public Integer getId() {

        return id;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param id the id to set
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * <pre>
     * 医院ID
     * </pre>
     * 
     * @return the hospitalId
     */
    public Integer getHospitalId() {

        return hospitalId;
    }

    /**
     * <pre>
     * 医院ID
     * </pre>
     * 
     * @param hospitalId the hospitalId to set
     */
    public void setHospitalId(Integer hospitalId) {

        this.hospitalId = hospitalId;
    }

    /**
     * <pre>
     * 私有云版本ID
     * </pre>
     * 
     * @return the versionId
     */
    public Integer getVersionId() {

        return versionId;
    }

    /**
     * <pre>
     * 私有云版本ID
     * </pre>
     * 
     * @param versionId the versionId to set
     */
    public void setVersionId(Integer versionId) {

        this.versionId = versionId;
    }

    /**
     * <pre>
     * 私有云版本名
     * </pre>
     * 
     * @return the versionName
     */
    public String getVersionName() {

        return versionName;
    }

    /**
     * <pre>
     * 私有云版本名
     * </pre>
     * 
     * @param versionName the versionName to set
     */
    public void setVersionName(String versionName) {

        this.versionName = versionName;
    }

    /**
     * <pre>
     * 私有云IP地址
     * </pre>
     * 
     * @return the ip
     */
    public String getIp() {

        return ip;
    }

    /**
     * <pre>
     * 私有云IP地址
     * </pre>
     * 
     * @param ip the ip to set
     */
    public void setIp(String ip) {

        this.ip = ip;
    }

    /**
     * <pre>
     * 端口
     * </pre>
     * 
     * @return the port
     */
    public Integer getPort() {

        return port;
    }

    /**
     * <pre>
     * 端口
     * </pre>
     * 
     * @param port the port to set
     */
    public void setPort(Integer port) {

        this.port = port;
    }

    /**
     * <pre>
     * 服务的超时时间,毫秒
     * </pre>
     * 
     * @return the timeout
     */
    public Integer getTimeout() {

        return timeout;
    }

    /**
     * <pre>
     * 服务的超时时间,毫秒
     * </pre>
     * 
     * @param timeout the timeout to set
     */
    public void setTimeout(Integer timeout) {

        this.timeout = timeout;
    }

    /**
     * <pre>
     * 协议全名
     * </pre>
     * 
     * @return the protocol
     */
    public String getProtocol() {

        return protocol;
    }

    /**
     * <pre>
     * 协议全名
     * </pre>
     * 
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {

        this.protocol = protocol;
    }

    /**
     * <pre>
     * 使用默认协议
     * </pre>
     * 
     * @return the defaultProtocol
     */
    public Integer getDefaultProtocol() {

        return defaultProtocol;
    }

    /**
     * <pre>
     * 使用默认协议
     * </pre>
     * 
     * @param defaultProtocol the defaultProtocol to set
     */
    public void setDefaultProtocol(Integer defaultProtocol) {

        this.defaultProtocol = defaultProtocol;
    }

    /**
     * <pre>
     * 出口IP
     * </pre>
     * 
     * @return the outingIp
     */
    public String getOutingIp() {

        return outingIp;
    }

    /**
     * <pre>
     * 出口IP
     * </pre>
     * 
     * @param outingIp the outingIp to set
     */
    public void setOutingIp(String outingIp) {

        this.outingIp = outingIp;
    }

    /**
     * <pre>
     * 0:默认连接 1:TSL加密连接
     * </pre>
     * 
     * @return the transportType
     */
    public Integer getTransportType() {

        return transportType;
    }

    /**
     * <pre>
     * 0:默认连接 1:TSL加密连接
     * </pre>
     * 
     * @param transportType the transportType to set
     */
    public void setTransportType(Integer transportType) {

        this.transportType = transportType;
    }

    /**
     * <pre>
     * Api2 v2.x需要的秘钥
     * </pre>
     * 
     * @return the apiKey
     */
    public String getApiKey() {

        return apiKey;
    }

    /**
     * <pre>
     * Api2 v2.x需要的秘钥
     * </pre>
     * 
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;
    }

    /**
     * <pre>
     * 0:大象云端支付 1:院端RealOnePay
     * </pre>
     * 
     * @return the payChannel
     */
    public Integer getPayChannel() {

        return payChannel;
    }

    /**
     * <pre>
     * 0:大象云端支付 1:院端RealOnePay
     * </pre>
     * 
     * @param payChannel the payChannel to set
     */
    public void setPayChannel(Integer payChannel) {

        this.payChannel = payChannel;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the enable
     */
    public Integer getEnable() {

        return enable;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param enable the enable to set
     */
    public void setEnable(Integer enable) {

        this.enable = enable;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the createBy
     */
    public String getCreateBy() {

        return createBy;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param createBy the createBy to set
     */
    public void setCreateBy(String createBy) {

        this.createBy = createBy;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the createDate
     */
    public Date getCreateDate() {

        return createDate;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {

        this.createDate = createDate;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the updateBy
     */
    public String getUpdateBy() {

        return updateBy;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param updateBy the updateBy to set
     */
    public void setUpdateBy(String updateBy) {

        this.updateBy = updateBy;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the updateDate
     */
    public Date getUpdateDate() {

        return updateDate;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {

        this.updateDate = updateDate;
    }
}
