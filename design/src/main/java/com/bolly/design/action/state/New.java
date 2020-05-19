package com.bolly.design.action.state;

/**
 * 新建
 * @author bolly
 */
public class New extends State {
    public New() {
        stateName = "新建状态";
        System.out.println("当前线程处于：新建状态.");
    }

    public void start(Context context) {
        if (stateName.equals("新建状态")) {
            context.setState(new Runnable());
        } else {
            System.out.println("当前线程不是新建状态，不能调用start()方法.");
        }
    }
}
