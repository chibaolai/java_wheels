package com.bolly.design.action.chain;

public class DepartmentMaster extends Leader {
    @Override
    public void doSomeThing(int leaveDays) {
        if(leaveDays <= 7) {
            System.out.println("系主任批准您请假" + leaveDays + "天。");
        }else if(getNext() != null){
            getNext().doSomeThing(leaveDays);
        }else {
            System.out.println("无审批人");
        }
    }
}
