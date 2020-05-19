package com.bolly.support.data.fruit;

import java.math.BigDecimal;

/**
 * @author bolly
 * 香蕉
 */
public class Bananer implements Fruit {
    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(5.00);
    }

    @Override
    public String size() {
        return "中等果";
    }

    @Override
    public String taste() {
        return "甜";
    }
}
