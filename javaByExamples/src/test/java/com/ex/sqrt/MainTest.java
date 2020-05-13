package com.ex.sqrt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void test() {
        assertEquals(94.73, Main.getSum(81, 4), 0.01);
        assertEquals(3.41, Main.getSum(2, 2), 0.01);
        assertEquals(2.00, Main.getSum(2, 1), 0.01);
    }
}