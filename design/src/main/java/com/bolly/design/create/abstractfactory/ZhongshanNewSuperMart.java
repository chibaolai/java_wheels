package com.bolly.design.create.abstractfactory;


/**
 * @author bolly
 * 中山新玛特
 */
public class ZhongshanNewSuperMart extends NewSuperMart {

    /**
     * 香蕉供货商
     * @return
     */
    @Override
    AbstractFruitProduct fruitProduct() {
        return new BananerProduct();
    }

    /**
     * 西红柿供货商
     * @return
     */
    @Override
    AbstractVegetableProduct vegetableProduct() {
        return new TomatoProduct();
    }
}
