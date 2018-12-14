package com.bolly.mybatis.mapper;

import java.util.List;

import com.bolly.mybatis.entity.BizAccount;
import org.apache.ibatis.annotations.Param;

/**
 * 平台账号表
 */
public interface BizAccountMapper {

    BizAccount get(@Param("userId") Long userId);

    /**
     * 插入
     * 
     * @param bizAccount
     *            需要插入数据库的账户
     * @return returnCode
     */
    int insert(BizAccount bizAccount);

    /**
     * 删除通过主键
     * 
     * @param id
     *            需要删除的账户的主键
     * @return returnCode
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 单件更新通过主键(全更新)
     * 
     * @param bizAccount
     *            需要更新的账户
     * @return returnCode
     */
    int updateAllByPrimaryKey(BizAccount bizAccount);

    /**
     * 单件更新通过主键(部分更新)
     * 
     * @param bizAccount
     *            需要更新的账户
     * @return returnCode
     */
    int updateByPrimaryKey(BizAccount bizAccount);

    /**
     * 单件检索通过主键
     * 
     * @param id
     *            目标账户的主键
     * @return bizAccount
     */
    BizAccount selectOneByPrimaryKey(Long id);

    /**
     * 检索列表
     * 
     * @param bizAccount
     *            不知道干什么用的，就是看黄线不顺眼
     * @return bizAccountList
     */
    List<BizAccount> selectList(BizAccount bizAccount);

    /**
     * 检索件数
     * 
     * @param bizAccount
     *            不知道干什么用的，就是看黄线不顺眼
     * @return count
     */
    int selectCount(BizAccount bizAccount);

    /**
     * Get Account By Phone Number or Account Name
     * 
     * @param phoneNo
     *            The Phone Number or Account Name
     * @return The Account Saved in the Server (If Exists)
     */
    BizAccount getByPhoneOrAccountName(String phoneNo);

    /**
     * Get Account By Phone Number
     * 
     * @param phoneNo
     *            The Phone Number
     * @return The Account Saved in the Server (If Exists)
     */
    BizAccount getByPhone(String phoneNo);

    /**
     * Update the App Type
     * 
     * @param id
     *            The ID of the Account
     * @param appType
     *            The New App Type
     */
    void updateAppType(@Param("id") Long id, @Param("appType") Integer appType);

    /**
     * Update the App Kind
     * 
     * @param id
     *            The ID of the Account
     * @param appKind
     *            The New App Kind
     */
    void updateAppKind(@Param("id") Long id, @Param("appKind") Integer appKind);
    
    /**
     * 手机号是否存在
     * @param phoneNo
     * @return
     */
    boolean existsPhoneNo(String phoneNo);

	BizAccount getByWxOpenId(@Param("appId") String appId, @Param("wxOpenId") String wxOpenId);

    /**
     * 更新AppType
     * @param account
     * @return
     */
    int update(BizAccount account);
	
}
