package com.wzy.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JsonEncoder();
        TestBean testBean = new TestBean();
        testBean.setAge(18);
        testBean.setName("WZY");
        byte[] bytes = encoder.encode(testBean);

        Decoder decoder = new JsonDecoder();
        TestBean bean2 = decoder.decode(bytes,TestBean.class);
        assertEquals(testBean.getName(), bean2.getName());
        assertEquals(testBean.getAge(), bean2.getAge());
    }
}