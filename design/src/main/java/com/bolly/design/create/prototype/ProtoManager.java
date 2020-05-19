package com.bolly.design.create.prototype;

import java.util.HashMap;

/**
 * @author bolly
 */
public class ProtoManager {
    private static HashMap<ShapeEnum,Shape> shapes = new HashMap<>();
    static {
        shapes.put(ShapeEnum.Circle,new Circle());
        shapes.put(ShapeEnum.Square,new Square());
    }

    /**
     * 增加一个形状
     * @param key
     * @param obj
     */
    public void addshape(ShapeEnum key,Shape obj) {
        shapes.put(key,obj);
    }

    /**
     * 获取一个形状
     * @param key
     * @return
     */
    public Shape getShape(ShapeEnum key) {
        return (Shape) shapes.get(key).clone();
    }

    public static void main(String[] args) {
        ProtoManager protoManager = new ProtoManager();
        protoManager.getShape(ShapeEnum.Circle).countArea();
        protoManager.getShape(ShapeEnum.Square).countArea();

    }
}
