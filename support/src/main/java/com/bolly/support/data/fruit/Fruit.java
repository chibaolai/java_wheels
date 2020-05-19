package com.bolly.support.data.fruit;

import com.bolly.support.data.Food;

import java.math.BigDecimal;

/**
 * @author bolly
 */
public interface Fruit extends Food {
    /**
     * 售价
     * @return
     */
    BigDecimal price();

    String size();
}
