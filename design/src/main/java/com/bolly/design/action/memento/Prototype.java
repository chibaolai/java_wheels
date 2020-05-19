package com.bolly.design.action.memento;

/**
 * 发起人
 * @author bolly
 */
public class Prototype implements Cloneable {
    private String state;

    /**
     * 创建原型
     * @return
     * @throws CloneNotSupportedException
     */
    public Prototype create() {
        return this.clone();
    }

    /**
     * 存储原型
     * @param prototype
     */
    public void restore(Prototype prototype) {
        this.setState(prototype.getState());
    }

    /**
     * 克隆原型
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
