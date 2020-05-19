package com.bolly.design.create.prototype;

interface Shape extends Cloneable{
    /**
     * 拷贝
     * @return
     */
    Object clone();

    /**
     * 计算面积
     */
    void countArea();
}
