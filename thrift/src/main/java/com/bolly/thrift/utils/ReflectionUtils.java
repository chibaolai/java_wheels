package com.bolly.thrift.utils;

import com.bolly.thrift.exception.UnexpectedException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * 反射实用工具类.包括使用反射API获取构造方法信息，创建类的实例，获取方法信息，方法调用，获取字段信息，字段值的获取与设置， java
 * bean相关操作等.
 *
 */
public class ReflectionUtils {

    private static final String NOT_NULL_MSG = "The targetObj value must not be null";

    private ReflectionUtils() {
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#Constructors
    // -------------------------------------------------------------------------

    /**
     * 获取给定类型名称的类型实例
     *
     * @param <T>
     * @param fullClassName
     *            给定类型名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> findClass(String fullClassName) {
        try {
            return (Class<T>) Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 获取给定类型的构造方法实例
     *
     * @param <T>
     * @param clazz
     *            给定类型
     * @param parameterTypes
     *            构造方法的各个参数类型
     * @return
     */
    public static <T> Constructor<T> findConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 使用给定构造方法的实例创建对应的类型的实例
     *
     * @param <T>
     * @param constructor
     *            构造方法的实例
     * @param initargs
     *            构造方法的各个参数
     * @return
     */
    public static <T> T newInstance(Constructor<T> constructor, Object... initargs) {
        try {
            return constructor.newInstance(initargs);
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 使用给定类型的名称创建对应类型的实例
     *
     * @param <T>
     * @param fullClassName
     *            类型名称
     * @param parameterTypes
     *            构造方法各个参数的类型
     * @param initargs
     *            构造方法的各个参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String fullClassName, Class<?>[] parameterTypes, Object... initargs) {
        return (T) newInstance(findClass(fullClassName), parameterTypes, initargs);
    }

    /**
     * 使用给定类型建对应类型的实例
     *
     * @param <T>
     * @param clazz
     * @param parameterTypes
     *            构造方法的各个参数类型
     * @param initargs
     *            构造方法的各个参数
     * @return
     */
    public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object... initargs) {
        return newInstance(findConstructor(clazz, parameterTypes), initargs);
    }

    /**
     * 使用给定类型的名称创建对应类型的实例。给定类型必须有无参的构造方法.
     *
     * @param <T>
     * @param fullClassName
     *            类型的名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String fullClassName) {
        return (T) newInstance(findClass(fullClassName));
    }

    /**
     * 使用给定类型创建对应类型的实例。给定的类型必须有无参构造方法。
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#Arrays
    // -------------------------------------------------------------------------

    /**
     * 使用反射创建数组实例
     *
     * @param elementType
     *            数组元素的类型
     * @param length
     *            数组的长度
     * @return
     */
    public static Object newArray(Class<?> elementType, int length) {
        return Array.newInstance(elementType, length);
    }

    /**
     * 使用反射创建多位数组实例
     *
     * @param elementType
     * @param dimentions
     * @return
     */
    public static Object newArray(Class<?> elementType, int... dimentions) {
        return Array.newInstance(elementType, dimentions);
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#Methods
    // -------------------------------------------------------------------------

    /**
     * 获取给定类型的方法实例，如果没有找到将返回null
     *
     * @param clazz
     *            给定类型
     * @param methodName
     *            方法名称
     * @param parameterTypes
     *            方法各个参数的类型
     * @return
     */
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods();
            for (Method method : methods) {
                if (isIdentical(method, methodName, parameterTypes)) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * 调用给定的方法实例代表的方法.
     *
     * @param targetObj
     *            目标对象.如果目标对象为null将调用静态方法
     * @param method
     *            方法实例
     * @param args
     *            传递给方法的各个参数
     * @return 调用方法所得的返回值
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object targetObj, Method method, Object... args) {
        method.setAccessible(true);
        try {
            return (T) method.invoke(targetObj, args);
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 调用给定对象的实例方法
     *
     * @param <T>
     * @param targetObj
     *            给定的对象
     * @param methodName
     *            实例方法的名称
     * @param parameterTypes
     *            实例方法的各个参数的类型
     * @param args
     *            实例方法的参数列表
     * @return 调用方法所得的返回值
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeInstanceMethod(Object targetObj, String methodName, Class<?>[] parameterTypes,
        Object... args) {
        notNull(targetObj, NOT_NULL_MSG);
        Method method = findMethod(targetObj.getClass(), methodName, parameterTypes);
        notNull(method, "Not found Method [" + methodName + "].");
        return (T) invokeMethod(targetObj, method, args);
    }

    /**
     * 调用给定类型的静态方法
     *
     * @param <T>
     * @param clazz
     *            给定的类型
     * @param methodName
     *            静态方法名称
     * @param parameterTypes
     *            静态方法的各个参数的类型
     * @param args
     *            静态方法的各个参数
     * @return 调用方法所得的返回值
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeStaticMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object... args) {
        Method method = findMethod(clazz, methodName, parameterTypes);
        notNull(method, "Not found Method [" + methodName + "].");
        return (T) invokeMethod(null, method, args);
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#Fields
    // -------------------------------------------------------------------------

    /**
     * 获取给定类型的所有字段
     *
     * @param clazz
     * @return
     */
    public static Field[] findFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<Field>();
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            searchType = searchType.getSuperclass();
        }
        return fieldList.toArray(new Field[fieldList.size()]);
    }

    /**
     * 获取给定类型的字段.未获取到将返回null
     *
     * @param clazz
     * @param fieldName
     *            字段的名称
     * @return
     */
    public static Field findField(Class<?> clazz, String fieldName) {
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * 获取对象的实例字段或静态字段的值.如果给定的对象为null，将获取静态字段的值，否则将获取实例字段的值
     *
     * @param <T>
     * @param targetObj
     *            给定的对象，如果为null，将获取静态字段的值
     * @param field
     *            字段的实例
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getField(Object targetObj, Field field) {
        field.setAccessible(true);
        try {
            return (T) field.get(targetObj);
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 获取给定对象的实例字段的值
     *
     * @param <T>
     * @param targetObj
     *            给定对象
     * @param fieldName
     *            实例字段的名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstanceField(Object targetObj, String fieldName) {
        notNull(targetObj, NOT_NULL_MSG);
        Field field = findField(targetObj.getClass(), fieldName);
        notNull(field, notFoundFieldMsg(fieldName));
        return (T) getField(targetObj, field);

    }

    /**
     * 获取给定类型的静态字段的值
     *
     * @param <T>
     * @param clazz
     *            给定的类型
     * @param fieldName
     *            静态字段的名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getStaticField(Class<?> clazz, String fieldName) {
        Field field = findField(clazz, fieldName);
        notNull(field, notFoundFieldMsg(fieldName));
        return (T) getField(null, field);
    }

    /**
     * 设置给定对象上的实例字段的值，如果给定对象为null，将设置静态字段的值
     *
     * @param targetObj
     *            给定的对象
     * @param field
     * @param fieldValue
     *            设置的值
     */
    public static void setField(Object targetObj, Field field, Object fieldValue) {
        field.setAccessible(true);
        try {
            field.set(targetObj, fieldValue);
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    /**
     * 设置给定对象的实例字段的值
     *
     * @param targetObj
     *            给定的对象
     * @param fieldName
     *            实例字段的名称
     * @param fieldValue
     *            设置的值
     */
    public static void setInstanceField(Object targetObj, String fieldName, Object fieldValue) {
        notNull(targetObj, NOT_NULL_MSG);
        Field field = findField(targetObj.getClass(), fieldName);
        notNull(field, notFoundFieldMsg(fieldName));
        setField(targetObj, field, fieldValue);
    }

    /**
     * 设置给定类型的静态字段的值
     *
     * @param clazz
     *            给定的类型
     * @param fieldName
     *            静态字段的名称
     * @param fieldValue
     *            设置的值
     */
    public static void setStaticField(Class<?> clazz, String fieldName, Object fieldValue) {
        Field field = findField(clazz, fieldName);
        notNull(field, notFoundFieldMsg(fieldName));
        setField(null, field, fieldValue);
    }

    private static String notFoundFieldMsg(String fieldName) {
        return "Not found Field [" + fieldName + "].";
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#JavaBeans
    // -------------------------------------------------------------------------

    /**
     * 获取给定java bean类型的属性描述符
     *
     * @param beanType
     *            给定的java bean类型
     * @param propertyName
     *            属性名称
     * @return
     */
    public static PropertyDescriptor findProperty(Class<?> beanType, String propertyName) {
        try {
            return new PropertyDescriptor(propertyName, beanType);
        } catch (IntrospectionException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * 获取给定java bean对象上的属性值
     *
     * @param <T>
     * @param bean
     *            给定的java bean对象
     * @param propertyName
     *            属性名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProperty(Object bean, String propertyName) {
        try {
            return (T) findProperty(bean.getClass(), propertyName).getReadMethod().invoke(bean);
        } catch (Exception e) {
            throw transReflectionException(e);
        }

    }

    /**
     * 设置给定的java bean对象上的属性值
     *
     * @param bean
     *            给定的java bean对象
     * @param propertyName
     *            属性名称
     * @param propertyValue
     *            设置的属性值
     * @return
     */
    public static Object setProperty(Object bean, String propertyName, Object propertyValue) {
        try {
            return findProperty(bean.getClass(), propertyName).getWriteMethod().invoke(bean,
                new Object[] {propertyValue});
        } catch (Exception e) {
            throw transReflectionException(e);
        }
    }

    // -------------------------------------------------------------------------
    // ReflectionUtils#Annotations
    // -------------------------------------------------------------------------

    /**
     * Find Annotation on class. If not found return null.
     *
     * @param clazz
     * @param annotationType
     * @return
     */
    public static <T extends Annotation> T findAnnotaion(Class<?> clazz, Class<T> annotationType) {
        return clazz.getAnnotation(annotationType);
    }

    /**
     * Find Annotation on method. If not found return null.
     *
     * @param method
     * @param annotationType
     * @return
     */
    public static <T extends Annotation> T findAnnotaion(Method method, Class<T> annotationType) {
        return method.getAnnotation(annotationType);
    }

    /**
     * Find Annotation on constructor. If not found return null.
     *
     * @param constructor
     * @param annotationType
     * @return
     */
    public static <T extends Annotation> T findAnnotaion(Constructor<?> constructor, Class<T> annotationType) {
        return constructor.getAnnotation(annotationType);
    }

    /**
     * Find Annotation on field. If not found return null.
     *
     * @param field
     * @param annotationType
     * @return
     */
    public static <T extends Annotation> T findAnnotaion(Field field, Class<T> annotationType) {
        return field.getAnnotation(annotationType);
    }

    // -------------------------------------------------------------------------
    // private methods
    // -------------------------------------------------------------------------

    private static RuntimeException transReflectionException(Exception e) {
        if (e instanceof InvocationTargetException) {
            return transInvocationTargetException((InvocationTargetException) e);
        }
        if (e instanceof ReflectiveOperationException) {
            return transReflectiveOperationException((ReflectiveOperationException) e);
        }
        if (e instanceof RuntimeException) {
            // include SecurityException IllegalArgumentException
            return (RuntimeException) e;
        }
        return new UnexpectedException(e);
    }

    private static RuntimeException transReflectiveOperationException(ReflectiveOperationException e) {
        if (e instanceof ClassNotFoundException) {
            return new UnexpectedException("Class not found:" + e.getMessage());
        }
        if (e instanceof NoSuchMethodException) {
            return new UnexpectedException("Method not found: " + e.getMessage());
        }
        if (e instanceof InstantiationException) {
            return new UnexpectedException("Instantiation Exception: " + e.getMessage());
        }
        if (e instanceof IllegalAccessException) {
            return new UnexpectedException("Could not access method: " + e.getMessage());
        }
        return new UnexpectedException(e);
    }

    private static RuntimeException transInvocationTargetException(InvocationTargetException e) {
        Throwable th = e.getTargetException();
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        return new UnexpectedException(th);
    }

    private static boolean isIdentical(Method method, String methodName, Class<?>... parameterTypes) {
        return method.getName().equals(methodName) && Arrays.equals(parameterTypes, method.getParameterTypes());
    }
}
