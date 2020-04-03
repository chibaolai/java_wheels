package com.bolly.design.struct.adapter;

/**
 * 适配器
 */
public class BattleFishingShip extends FishingShip implements BattleShip{
    @Override
    public void fire() {
        System.out.println(getName()+" fire! peng! peng!");
    }

    @Override
    public void move() {
        System.out.println(getName()+" move! move! move!");
    }

    private String getName() {
        return "BattleFishingShip";
    }
}
