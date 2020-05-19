package com.bolly.design.create.factorymethod;

import com.bolly.support.data.fruit.Fruit;
import com.bolly.support.data.fruit.Orange;

/**
 * @author bolly
 * 橘子工厂
 */
public class OrangeFactory extends FruitFactory {
    @Override
    public Fruit produce() {
        return new Orange();
    }
}
