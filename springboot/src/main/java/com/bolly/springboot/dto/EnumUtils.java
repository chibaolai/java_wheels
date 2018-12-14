package com.bolly.springboot.dto;

import java.util.EnumSet;
import java.util.Set;

public class EnumUtils {

    private EnumUtils() {
    }

    /**
     * parse IdNameEnumable EnUM ById
     * @param id
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E> & IdNameEnumable> Enum<E> parseById(int id, Class<E> clazz) {
        Set<E> enums = EnumSet.allOf(clazz);
        for (IdNameEnumable e : enums) {
            if (e.getId() == id) {
                return (E) e;
            }
        }
        throw new IllegalArgumentException("Illegal argument id:" + id);
    }
    
    /**
     * ToStringUtil类 方便打印IdNameEnumable类型的枚举
     * @param clazz
     * @return
     */
	public static <E extends Enum<E> & IdNameEnumable> String toString(Class<E> clazz) {
    	Set<E> enums = EnumSet.allOf(clazz);
    	StringBuilder sb = new StringBuilder();
        for (IdNameEnumable e : enums) {
        	sb.append(e.getId()).append(" : ").append(e.getName()).append("\n");
        }
        return sb.toString();
    }

}
