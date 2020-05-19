package com.bolly.support.data.fruit;

import java.math.BigDecimal;

/**
 * @author bolly
 * 橘子
 */
public class Orange implements Fruit{
    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(6.00);
    }

    @Override
    public String size() {
        return "超大";
    }

    @Override
    public String taste() {
        return "酸";
    }
}
