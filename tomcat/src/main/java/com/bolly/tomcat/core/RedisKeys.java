package com.bolly.tomcat.core;

import org.apache.commons.lang3.StringUtils;

public class RedisKeys {

	private RedisKeys() {
	}

	/**
	 * 医院挂号规则 regRules:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String regRules(int hospId) {
		return "regRules:" + hospId;
	}
	
	/**
	 * 医院退号规则 cancleRegRules:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String cancleRegRules(int hospId) {
		return "cancleRegRules:" + hospId;
	}
	
	/**
	 * 医院退号规则 removePatientRules:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String removePatientRules(int hospId) {
		return "removePatientRules:" + hospId;
	}

	/**
	 * 医院支付配置参数 payParam:{hospId}:{payWayTypeId}
	 * 
	 * @param hospId
	 * @param payWayTypeId
	 * @return
	 */
	public static String payParam(int hospId, int payWayTypeId) {
		return "payParam:" + hospId + ":" + payWayTypeId;
	}

	/**
	 * 医院支付配置 cfgHospPayWay:{hospId}:{payWayTypeId}
	 * 
	 * @param hospId
	 * @param payWayTypeId
	 * @return
	 */
	public static String cfgHospPayWay(int hospId, int payWayTypeId) {
		return "cfgHospPayWay:" + hospId + ":" + payWayTypeId;
	}

	/**
	 * 医院PC2配置 cfgHospCloud:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String cfgHospCloud(int hospId) {
		return "cfgHospCloud:" + hospId;
	}

	/**
	 * 大象就医signingKey:{accountId}
	 * 
	 * @param accountId
	 * @return
	 */
	public static String signingKey(long accountId) {
		return "signingKey:" + accountId;
	}

	/**
	 * 大象医生 dcSigningKey:{accountId}
	 * 
	 * @param accountId
	 * @return
	 */
	public static String dcSigningKey(String accountId) {
		return "dcSigningKey:" + accountId;
	}

	/**
	 * errorPwdCount:{phone}
	 * 
	 * @param phone
	 * @return
	 */
	public static String errorPwdCount(String phone) {
		return "errorPwdCount:" + phone;
	}

	/**
	 * 大象就医nonce:{appType}:{nonce}
	 * 
	 * @param nonce
	 * @return
	 */
	public static String nonce(int appType, String nonce) {
		return "nonce:" + appType + ":" + nonce;
	}

	/**
	 * 大象医生dcNonce:{appType}:{nonce}
	 * 
	 * @param nonce
	 * @return
	 */
	public static String dcNonce(int appType, String nonce) {
		return "dcNonce:" + appType + ":" + nonce;
	}

	/**
	 * pc2Nonce:{hospId}:{nonce}
	 * 
	 * @param hospId
	 * @param nonce
	 * @return
	 */
	public static String pc2Nonce(int hospId, String nonce) {
		return "pc2Nonce:" + hospId + ":" + nonce;
	}

	/**
	 * feedback:{UserId}
	 * 
	 * @param UserId
	 * @return
	 */
	public static String feedback(Long userId) {
		return "feedback:" + userId;
	}

	/**
	 * deptId:{hospId}:{hisDeptId}
	 * 
	 * @param hospId
	 * @param hisDeptId
	 * @return
	 */
	public static String deptId(int hospId, String hisDeptId) {
		return "deptId:" + hospId + ":" + hisDeptId;
	}

	/**
	 * hisDeptId:{deptId}
	 * 
	 * @param deptId
	 * @return
	 */
	public static String hisDeptId(long deptId) {
		return "hisDeptId:" + deptId;
	}

	/**
	 * doctorId:{hospId}:{hisDoctorId}
	 * 
	 * @param hospId
	 * @param hisDoctorId
	 * @return
	 */
	public static String doctorId(int hospId, String hisDoctorId) {
		return "doctorId:" + hospId + ":" + hisDoctorId;
	}

	/**
	 * hisDoctorId:{doctorId}
	 * 
	 * @param hospId
	 * @param hisDoctorId
	 * @return
	 */
	public static String hisDoctorId(long doctorId) {
		return "hisDoctorId:" + doctorId;
	}

	/**
	 * symptomDictVer
	 * 
	 * @return
	 */
	public static String symptomDictVer() {
		return "symptomDictVer";
	}

	/**
	 * 医院自定义信息配置 cfgHospCustom:{hospId}:{type}
	 * 
	 * @param hospId
	 * @param type
	 * @return
	 */
	public static String cfgHospRegCustom(int hospId) {
		return "cfgHospRegCustom:" + hospId;
	}

	/**
	 * comDeptId:{comDeptId}
	 * 
	 * @param comDeptId
	 * @return
	 */
	public static String comDeptId(String comDeptId) {
		return "comDeptId:" + comDeptId;
	}

	/**
	 * prevPicVer:{hSize_wSize_appType}
	 * 
	 * @param hSize
	 * @param wSize
	 * @param appType
	 * @return
	 */
	public static String prevPicVer(int hSize, int wSize, int appType) {
		return "prevPicVer:" + hSize + "_" + wSize + "_" + appType;
	}

	/**
	 * signInAppInfo:{accountId}
	 * 
	 * @param accountId
	 * @return
	 */
	public static String signInAppInfo(long accountId) {
		return "signInAppInfo:" + accountId;
	}

	/**
	 * cfgAppPush:{appKind}
	 * 
	 * @param appKind
	 * @return
	 */
	public static String cfgAppPush(int appKind) {
		return "cfgAppPush:" + appKind;
	}

	/**
	 * expAccountId:{accountId}
	 * 
	 * @param accountId
	 * @return
	 */
	public static String expAccountId(long accountId) {
		return "expAccountId:" + accountId;
	}

	/**
	 * getDepts:{hospId}:{isMutiDept}
	 * 
	 * @param hospId
	 * @param isMutiDept
	 * @return
	 */
	public static String getDepts(int hospId, int isMutiDept, int scheduleType, Integer comDeptId) {
		if (comDeptId != null) {
			return "getDepts:" + hospId + ":" + isMutiDept + ":" + scheduleType + ":" + comDeptId;
		}
		return "getDepts:" + hospId + ":" + isMutiDept + ":" + scheduleType;
	}

	/**
	 * regTargets:{hospId}:{deptId}:{scheduleDate} /
	 * regTargets:{hospId}:{deptId}
	 * 
	 * @param hospId
	 * @param deptId
	 * @param scheduleDate
	 * @return
	 */
	public static String regTargets(Integer hospId, Long deptId, Integer scheduleType, Integer targetType,
			String scheduleDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("regTargets:" + hospId);
		if (deptId != null) {
			sb.append(":" + deptId);
		}
		if (StringUtils.isNotBlank(scheduleDate)) {
			sb.append(":" + scheduleDate);
		}
		if (scheduleType != null) {
			sb.append(":" + scheduleType);
		}
		if (targetType != null) {
			sb.append(":" + targetType);
		}
		return sb.toString();
	}

	/**
	 * pubRegTime:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String pubRegTime(Integer hospId) {
		return "pubRegTime:" + hospId;
	}

	/**
	 * wxResult:{orderNo}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String wxResult(String orderNo) {
		return "wxResult:" + orderNo;
	}

	/**
	 * comDeptInfo:{comDeptId}
	 * 
	 * @param comDeptId
	 * @return
	 */
	public static String comDeptInfo(String comDeptId) {
		return "comDeptInfo:" + comDeptId;
	}

	/**
	 * reportRecord:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String hospReport(Integer hospId, Integer appType) {
		return "hospReport:" + hospId + ":" + appType;
	}

	/**
	 * que:{hospId}:{userId}:{patientId}
	 * 
	 * @param hospId
	 * @param userId
	 * @param patientId
	 * @return
	 */
	public static String hospQue(int hospId, long userId, Long patientId) {
		if (patientId != null) {
			return "hospQue:" + hospId + ":" + userId + ":" + patientId;
		} else {
			return "hospQue:" + hospId + ":" + userId;
		}

	}

	/**
	 * waitingQue:{hospId}:{queId}
	 * 
	 * @param hospId
	 * @param queId
	 * @return
	 */
	public static String waitingQue(int hospId, String queId) {
		return "waitingQue:" + hospId + ":" + queId;
	}

	/**
	 * waitingQueInfo:{hospId}:{patientId}
	 * 
	 * @param hospId
	 * @param patientId
	 * @return
	 */
	public static String waitingQueInfo(int hospId, String patientId) {
		return "waitingQueInfo:" + hospId + ":" + patientId;
	}

	/**
	 * errorCardInHospPwdCount:{userId}:{markNo}
	 * 
	 * @param userId
	 * @param markNo
	 * @return
	 */
	public static String errorCardInHospPwdCount(long userId, String markNo) {
		return "errCardPwdCount:" + userId + ":" + markNo;
	}
	
	/**
	 * inpatientFeesInMqKey:{recordId}
	 * 
	 * @param recordId
	 * @return
	 */
	public static String inpatientFeesInMqKey(long recordId) {
		return "inpatientFeesInMqKey:" + recordId;
	}
	/**
	 * inpatientInfoExceptionKey:{recordId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String inpatientInfoExceptionKey(int hospId) {
		return "inpatientInfoExceptionKey:" + hospId;
	}
	
	/**
	 * 微信回调nonce:{payTypeId}:{encryptedTradeId}
	 * 
	 * @param encryptedTradeId
	 * @return
	 */
	public static String notifyNonce(Integer payTypeId, String encryptedTradeId) {
		return "notifyNonce:" + payTypeId + ":" + encryptedTradeId;
	}
	
	/**
	 * 系统数据
	 * 
	 * @return
	 */
	public static String getSysDictData() {
		return "sysDictDataRedisKey";
	}
	
	/**
	 * 微信回调wxInsSecretKey:{appId}
	 * 
	 * @param encryptedTradeId
	 * @return
	 */
	public static String wxInsSecretKey(String appId) {
		return "wxInsSecretKey:" + appId;
	}
	
	/**
	 * 医院连接报警api2ConnectException:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String api2ConnectExceptionKey(int hospId) {
		return "api2ConnectException:" + hospId;
	}
	
	/**
	 * 手机号码是否发送短信sendMsgKey:{phone}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String sendMsgKey(String phone) {
		return "sendMsgKey:" + phone;
	}
	
	/**
	 * 医院自定义信息配置 hospRegExt:{hospId}:{type}
	 * 
	 * @param hospId
	 * @param type
	 * @return
	 */
	public static String hospRegExt(int hospId) {
		return "hospRegExt:" + hospId;
	}
	
	/**
	 * 医院短信发送签名hospSignTypeKey:{hospId}
	 * 
	 * @param hospId
	 * @return
	 */
	public static String hospSignTypeKey(int hospId) {
		return "hospSignTypeKey:" + hospId;
	}
	
	/**
	 * 验证加入黑名单开关
	 * 
	 * @return
	 */
	public static String getBlacklistAddCheckToggleKey() {
		return "blacklistAddCheckToggleKey";
	}
	
	/**
	 * 义幻接口调用凭证yhInsTokenKey:{appId}
	 * 
	 * @param appId
	 * @return
	 */
	public static String yhInsTokenKey(String appId) {
		return "yhInsTokenKey:" + appId;
	}
	
	/**
	 * 义幻接口用户绑卡链接yhInsMedicalCardBindingUrlKey:{appId}:{openId}
	 * 
	 * @param appId
	 * @param openId
	 * @return
	 */
	public static String yhInsMedicalCardBindingUrlKey(String appId, String openId) {
		return "yhInsMedicalCardBindingUrlKey:" + appId + ":" + openId;
	}
	
	/**
	 * 微信token Key:{appId}
	 * @param appId
	 * @return
	 */
	public static String wxTokenKey(String appId) {
		return "wxTokenKey:" + appId;
	}
	/**
	 * 根据病症ID获取医院科室  symptom:{hosoId}:{symptomId}
	 * @param hosoId
     * @param symptomId
     * @return
	 */
	public static String symptonKey(Integer hospId,Integer symptomId ) {
	    return "symptomId:" + hospId +":" + symptomId;
	}
	/**
	 * 获取医院科室  getSympton:{hospitalId}
	 * @param hospitalId
	 * @return
	 */
	
	public static String getSymptonKey(Integer hospitalId,String crowd) {
	    return "getSympton:" + hospitalId +":"+ crowd;
	}
	/**
     * 住院预交金保存充值信息
     * @param 
     * @return
     */
	public static String inpatinpatienPreienPre(Long userId) {
	    return "inpatienPre:" + userId;
	}
	   /**
     * 就诊卡保存充值信息
     * @param 
     * @return
     */
    public static String orderCharge(Long userId) {
        return "orderCharge:" + userId;
    }
    /**
     * 获取body  getBody:{hospitalId}
     * @param hospitalId
     * @return
     */
    
    public static String getBody(Integer hospitalId,String crowd) {
        return "getBody:" + hospitalId +":"+ crowd;
    }
    
	public static String statisticsCount(String hospId, String date, String interfaceId, String fromId) {
		return "statisticsCount:" + hospId + ":" + date + ":" + interfaceId + ":" + fromId;
	}

	/**
	 * 第三方接口权限authKey:{authKey}:{openList}
	 *
	 * @param authKey
	 * @return
	 */
	public static String authKey(String authKey) {
		return "authKey:" + authKey;
	}
	
	/**
	 * 大象就医token:{accountId}
	 * 
	 * @param accountId
	 * @return
	 */
	public static String userToken(long accountId) {
		return "userToken:" + accountId;
	}
	
	/**
	 * 保险key
	 * 
	 * @param hSize
	 * @param wSize
	 * @param appType
	 * @return
	 */
	public static String insAccountInfo(long hospId, String medNum, String inpatientNo) {
		return "insAccountInfo:" + hospId + "_" + medNum + "_" + inpatientNo;
	}
}
