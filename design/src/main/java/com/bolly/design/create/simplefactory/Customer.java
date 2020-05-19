package com.bolly.design.create.simplefactory;

import com.bolly.support.data.*;
import com.bolly.support.data.fruit.Apple;
import com.bolly.support.data.fruit.Lemon;
import com.bolly.support.data.vegetables.Kugua;
import com.bolly.support.data.vegetables.Pepper;
import com.bolly.support.enums.Flavor;

/**
 * @author bolly
 */
public class Customer {

    /**
     * 根据顾客口味推荐
     * @param flavor
     * @return
     */
    public static Food getFood(Flavor flavor) {
        switch (flavor) {
            case SUAN:
                return new Lemon();
            case TIAN:
                return new Apple();
            case KU:
                return new Kugua();
            case LA:
                return new Pepper();
                default:
                    return new Apple();
        }
    }

    public static void main(String[] args) {
       String taste = Customer.getFood(Flavor.KU).taste();
        System.out.println(taste);
    }
}
