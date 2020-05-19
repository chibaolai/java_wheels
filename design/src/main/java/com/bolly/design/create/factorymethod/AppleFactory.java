package com.bolly.design.create.factorymethod;

import com.bolly.support.data.fruit.Apple;
import com.bolly.support.data.fruit.Fruit;

/**
 * @author bolly
 * 苹果工厂
 */
public class AppleFactory extends FruitFactory{

    /**
     * 生产苹果
     * @return
     */
    @Override
    public Fruit produce() {
        return new Apple();
    }
}
