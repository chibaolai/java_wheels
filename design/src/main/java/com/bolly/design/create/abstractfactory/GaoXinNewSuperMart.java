package com.bolly.design.create.abstractfactory;

/**
 * @author bolly
 * 高新园区新玛特
 */
public class GaoXinNewSuperMart extends NewSuperMart {

    /**
     * 供货苹果
     * @return
     */
    @Override
    AbstractFruitProduct fruitProduct() {
        return new AppleProduct();
    }

    /**
     * 供货苦瓜
     * @return
     */
    @Override
    AbstractVegetableProduct vegetableProduct() {
        return new PotatoProduct();
    }
}
