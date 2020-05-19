package com.bolly.design.action.observer;

public class LiObserver implements Observer {
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("Li.update: " + temp + " " + humidity + " " + pressure);
    }
}
