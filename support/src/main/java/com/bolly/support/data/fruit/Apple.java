package com.bolly.support.data.fruit;

import java.math.BigDecimal;

/**
 * @author bolly
 * 苹果
 */
public class Apple implements Fruit {
    @Override
    public String taste() {
        return "酸甜";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(10.00);
    }

    @Override
    public String size() {
        return "特等果";
    }
}
