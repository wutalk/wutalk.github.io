package com.example.encrypt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CryptoUtilsTest {
    @Test
    public void testHex() {
        assertEquals("68656c6c6f", CryptoUtils.hex("hello".getBytes()));
        assertEquals("68656c6c6f20776f726c64", CryptoUtils.hex("hello world".getBytes()));
    }
}
