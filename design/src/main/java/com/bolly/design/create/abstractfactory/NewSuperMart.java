package com.bolly.design.create.abstractfactory;

/**
 * @author bolly
 * 新玛特
 */
public abstract class NewSuperMart {
    /**
     * 水果供货商
     * @return
     */
    abstract AbstractFruitProduct fruitProduct();

    /**
     * 蔬菜供货
     * @return
     */
    abstract AbstractVegetableProduct vegetableProduct();


    public static void main(String[] args) {
        NewSuperMart foodFactory = new GaoXinNewSuperMart();
        AbstractFruitProduct fruitProduct = foodFactory.fruitProduct();
        AbstractVegetableProduct vegetableProduct = foodFactory.vegetableProduct();
        System.out.println(fruitProduct.getName());
        System.out.println(vegetableProduct.getLogo());
    }
}
