package com.bolly.design.action.state;

/**
 * 消亡
 * @author bolly
 */
public class Dead extends State {
    public Dead() {
        stateName = "死亡状态";
        System.out.println("当前线程处于：死亡状态.");
    }
}
