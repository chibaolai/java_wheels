package com.bolly.design.action.chain;

public class ClassMaster extends Leader {
    @Override
    public void doSomeThing(int leaveDays) {
        if(leaveDays < 2) {
            System.out.println("班主任批准您请假" + leaveDays + "天。");
        }else if(getNext() != null){
            getNext().doSomeThing(leaveDays);
        }else {
            System.out.println("无审批人");
        }
    }
}
