include "BaseData.thrift"
include "Enums.thrift"

namespace java com.niox.api2.contract.request
namespace csharp Com.Niox.Api2.ThriftContract.Request
namespace go pc2.api2.thrift.request


/**
*心跳接口参数
*/
struct HeartBeatRequest{             
	/**
	*安全信息
	*/
	1: BaseData.Security security    
}