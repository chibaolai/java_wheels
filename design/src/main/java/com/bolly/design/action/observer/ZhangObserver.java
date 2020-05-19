package com.bolly.design.action.observer;

public class ZhangObserver implements Observer {

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("zhang.update: " + temp + " " + humidity + " " + pressure);
    }
}
