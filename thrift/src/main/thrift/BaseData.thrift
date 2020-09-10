include "Enums.thrift"

namespace java com.niox.api2.contract.basedata
namespace csharp Com.Niox.Api2.ThriftContract.BaseData
namespace go pc2.api2.thrift.basedata

/**
* 接口的执行结果
*/
struct Result{
	/**
	* 执行结果代码
	* "0" 执行成功
	* "-1" 系统错误
	* "1" 业务错误
	*/
	1: string code
	/**
	* 执行结果描述
	*/
	2: string msg
}
/**
* 访问接口时，传入的系统安全相关的信息
*/
struct Security{
 	/**
	* 签名
	*/
	1: string sig
	/**
	* PC1的医院ID
	*/
	2: i32 hospId
	/**
	* 时间戳
	*/
	3: i64 timeStamp
	/**
	* 随机数
	*/
	4: string nonce
	/**
	* 访问者
	*/
	5: string requester
	/**
	* 访问者ID
	*/
	6: string userId
	/**
	* 访问者sig
	*/
	7: string userSig
	/**
	* 扩展json串
	*/
	8: string extras

}