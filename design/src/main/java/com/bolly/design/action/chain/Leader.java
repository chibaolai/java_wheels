package com.bolly.design.action.chain;

public abstract class Leader {
    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    public abstract void doSomeThing(int leaveDays);

    public static void main(String[] args) {
        Leader classMaster = new ClassMaster();
        Leader departmentMaster = new DepartmentMaster();
        Leader schoolMaster = new SchoolMaster();

        classMaster.setNext(departmentMaster);
        departmentMaster.setNext(schoolMaster);

        classMaster.doSomeThing(6);
    }
}
