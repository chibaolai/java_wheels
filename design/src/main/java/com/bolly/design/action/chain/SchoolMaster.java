package com.bolly.design.action.chain;

public class SchoolMaster extends Leader{
    @Override
    public void doSomeThing(int leaveDays) {
        if(leaveDays <= 10) {
            System.out.println("校长批准您请假" + leaveDays + "天。");
        }else if(getNext() != null){
            getNext().doSomeThing(leaveDays);
        }else {
            System.out.println("无审批人");
        }
    }
}
