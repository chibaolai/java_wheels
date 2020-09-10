package com.bolly.thrift;

import com.bolly.thrift.api.tf.Api;
import com.bolly.thrift.client.proxy.SelfCheckServiceProxy;
import com.niox.api2.contract.basedata.Result;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RocBaseActionTest {
    private TTransport transport;
    private TCompactProtocol protocol;
    protected Api.Client api;

    @Mock
//    protected Roc roc;
    protected SelfCheckServiceProxy selfCheckServiceProxy;

    @Mock
    protected SelfCheckServiceProxy selfCheckServiceMock;

//    @Autowired
//    @InjectMocks
//    protected RocService rocService;

    @BeforeEach
    public void _actionTestSetUp() throws TTransportException {
        Result result = new Result();
        result.setCode("0");
        result.setMsg("ok");
//        when(roc.getSelfCheckService(1)).thenReturn(selfCheckServiceMock);
        when(selfCheckServiceMock.heartBeat()).thenReturn(result);
        transport = new TSocket("127.0.0.1", 10028, 50000);
        protocol = new TCompactProtocol(transport);
        api = new Api.Client(protocol);
        transport.open();
    }

    @AfterEach
    public void _actionTestTestDown() {
        transport.close();
    }

}
