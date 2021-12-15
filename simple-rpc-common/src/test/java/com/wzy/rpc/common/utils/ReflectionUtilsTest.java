package com.wzy.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethod() {
        Method[] methods = ReflectionUtils.getPublicMethod(TestClass.class);
        assertEquals(1, methods.length);
        String name = methods[0].getName();
        assertEquals("b", name);
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethod(TestClass.class);
        TestClass t = new TestClass();
        Object invoke = ReflectionUtils.invoke(t, methods[0]);
        assertEquals("b", invoke);
    }
}