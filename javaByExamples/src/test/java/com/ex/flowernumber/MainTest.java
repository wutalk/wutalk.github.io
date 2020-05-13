package com.ex.flowernumber;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void test() {
        List<String[]> cases = new ArrayList<>();
        assertTrue(Main.isFlowerNumber(153));
        assertTrue(Main.isFlowerNumber(370));
        assertTrue(Main.isFlowerNumber(371));

        assertEquals("no", Main.output(100, 120));
        assertEquals("370 371", Main.output(300, 380));
    }
}