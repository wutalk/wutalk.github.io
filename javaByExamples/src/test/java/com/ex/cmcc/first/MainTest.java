package com.ex.cmcc.first;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void test() {
        assertEquals(15, Main.getR(1, 10));
        System.out.println(Math.ceil(15 - 5 * 0.25-5));
        System.out.println(Math.floor(15 - 5 * 0.25-5));
    }
}