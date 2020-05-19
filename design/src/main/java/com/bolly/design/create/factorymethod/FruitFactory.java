package com.bolly.design.create.factorymethod;

import com.bolly.support.data.fruit.Fruit;

import java.math.BigDecimal;

/**
 * @author bolly
 * 水果工厂
 * java.util.Calendar
 * java.util.ResourceBundle
 * java.text.NumberFormat
 * java.nio.charset.Charset
 * java.net.URLStreamHandlerFactory
 * java.util.EnumSet
 * javax.xml.bind.JAXBContext
 *
 */
public abstract class FruitFactory {

    /**
     * 生产水果
     * @return
     */
    abstract public Fruit produce();

    /**
     * 售价
     * @return
     */
    public BigDecimal sale() {
        Fruit fruit = produce();
        return fruit.price();
    }

    public static void main(String[] args) {
        FruitFactory factory = new AppleFactory();
        System.out.println(factory.sale());
    }
}
