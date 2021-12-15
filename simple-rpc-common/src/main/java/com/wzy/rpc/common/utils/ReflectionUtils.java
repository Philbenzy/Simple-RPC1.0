package com.wzy.rpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: simple-rpc
 * @description: 反射工具类
 * @author: WZY
 * @create: 2021-12-09 13:31
 **/
public class ReflectionUtils {
    /**
     * 根据类型创建对象
     * @param clazz 字节码对象
     * @param <T> 对象类型
     * @return 目标对象
     */
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取字节码对象的公共方法
     * @param clazz
     * @return 返回方法数组
     */
    public static Method[] getPublicMethod(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        for (Method m : methods){
            if (Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 反射
     * @param obj
     * @param method 被调用方法
     * @param args 方法参数
     * @return
     */
    public static Object invoke(Object obj, Method method, Object...args){
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
