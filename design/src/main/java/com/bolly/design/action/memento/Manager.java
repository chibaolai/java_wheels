package com.bolly.design.action.memento;

/**
 * 管理器 ctl + z
 * @author bolly
 */
public class Manager {
    private Prototype prototype;

    public Prototype getPrototype() {
        return prototype;
    }

    public void setPrototype(Prototype prototype) {
        this.prototype = prototype;
    }

    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.setState("第十关");
        System.out.println(prototype.getState());
        Manager manager = new Manager();
        // 存档
        manager.setPrototype(prototype.create());
        prototype.setState("第十一关");
        System.out.println(prototype.getState());
        // 读档
        prototype.restore(manager.getPrototype());

        System.out.println(prototype.getState());


    }
}
