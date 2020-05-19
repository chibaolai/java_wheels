package com.bolly.support.data.fruit;

import java.math.BigDecimal;

/**
 * @author bolly
 * 柠檬
 */
public class Lemon implements Fruit {
    @Override
    public String taste() {
        return "酸";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(3.00);
    }

    @Override
    public String size() {
        return "小";
    }
}
