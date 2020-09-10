include "Response.thrift"
include "Request.thrift"
include "BaseData.thrift"

namespace java com.niox.api2.contract
namespace csharp Com.Niox.Api2.ThriftContract
namespace go pc2.api2.thrift.service

service SelfCheckService{
	/**
	*心跳
	*接口编号：CHK001
	*/
	BaseData.Result HeartBeat ()
}