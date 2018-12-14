package com.bolly.mybatis.entity;
/**
 * 
 *
 */
public class BizAccount extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4093553302529000480L;

    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 登录名
     */
    private String accountName;
    /**
     * 密码
     */
    private String password;
    /**
     * 联系电话
     */
    private String phoneNo;
    /**
     * app类型
     */
    private Integer appType;
    /**
     * 阿里支付宝opendID
     */
    private String aliOpenId;
    /**
     * 微信H5open的ID
     */
    private String wxOpenId;
    /**
     * app种类
     */
    private Integer appKind;
    /**
     * enable
     */
    private Integer enable;
    
    private String headImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAliOpenId() {
        return aliOpenId;
    }

    public void setAliOpenId(String aliOpenId) {
        this.aliOpenId = aliOpenId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getAppKind() {
        return appKind;
    }

    public void setAppKind(Integer appKind) {
        this.appKind = appKind;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
