include "Base.thrift"

namespace java com.bolly.thrift.api.tf.req

struct PingReq {
	1: Base.ReqHeader header
	2: i32 hospId
}