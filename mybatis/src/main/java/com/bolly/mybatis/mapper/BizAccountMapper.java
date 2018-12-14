package com.bolly.mybatis.mapper;

import com.bolly.mybatis.entity.BizAccount;
import org.apache.ibatis.annotations.Param;

/**
 * 账号表
 */
public interface BizAccountMapper {

    BizAccount get(@Param("userId") Long userId);

    /**
     * Get Account By Phone Number
     * 
     * @param phoneNo
     *            The Phone Number
     * @return The Account Saved in the Server (If Exists)
     */
    BizAccount getByPhone(String phoneNo);
	
}
