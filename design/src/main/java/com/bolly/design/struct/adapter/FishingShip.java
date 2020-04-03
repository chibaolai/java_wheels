package com.bolly.design.struct.adapter;

/**
 * 捕鱼船
 */
public class FishingShip implements Ship {
    @Override
    public void move() {
        System.out.println(getName()+" move! move! move!");
    }

    protected void fish() {
        System.out.println(getName()+" fishing! fishing! fishing!");
    }

    private String getName() {
        return "FishingShip";
    }
}
