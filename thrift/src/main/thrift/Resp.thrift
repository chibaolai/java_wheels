include "Base.thrift"

namespace java com.bolly.thrift.api.tf.resp

struct PingResp {
	1: Base.RespHeader header = {}
}