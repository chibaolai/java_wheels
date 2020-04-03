package com.bolly;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class RemoteDebugMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println("输出进程ID："+ ManagementFactory.getRuntimeMXBean().getName());
        String targetPid = "33056";
        VirtualMachine vm = VirtualMachine.attach(targetPid);
        vm.loadAgent(System.getProperty("user.dir") + "/target/tuling-apm-demo-1.0-SNAPSHOT.jar",
                "toagent");
    }

}
