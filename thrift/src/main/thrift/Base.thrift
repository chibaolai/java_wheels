namespace java com.bolly.thrift.api.tf


/**
 * 分页信息
 */
struct Page{
	/**
	 * 当前页码,从1开始
	 */
	1: i32 pageNo = 1
	/**
	 * 每页数量量
	 */
	2: i32 pageSize = 10
	/** 
	 * 数据总量
	 */
	3: optional i64 total
}

/**
 * 设备信息
 */
struct Device {
	/**
	 * 设备Id
	 */
	1: string deviceId
	/**
	 * 设备名称
	 */
	2: string deviceName
	/**
	 * 系统名称
	 */
	3: string osName
}

/**
 * 请求头信息
 */
struct ReqHeader{
	/**
	 * App类型
	 */                     
	1: optional i32 appType
	/**
	 * App版本号
	 */
	2: string appVersion
	/**
	 * Token
	 */
	3: string token
	/**
	 * 当前时间戳(毫秒数)
	 */
	4: i64 timestamp
	/**
	 * 长度大于10位的随机字符串不能重复,推荐使用UUID
	 */
	5: string nonce
	/**
	 * 签名信息
	 */
	6: string signature
	/**
	 * 用户id
	 */
	7: optional i64 userId
	/**
	 * 设备信息
	 */
	8: Device device
}

/**
 * 响应头信息
 */
struct RespHeader{
	/**
	 * 状态码
	 */
	1: i32 status
	/**
	 * 返回结果描述
	 */
	2: string msg
}


