include "Req.thrift"
include "Resp.thrift"

namespace java com.bolly.thrift.api.tf


/**<pre>
BOLLY Api
</pre>*/
service Api {
	/**<pre>
	描述:		ping
	认证方式:	默认签名
	</pre> */
	Resp.PingResp ping(1: Req.PingReq req)
}

