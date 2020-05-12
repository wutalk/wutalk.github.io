package com.ex.inout;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    @Test
    public void testCalc() {
        List<String[]> cases = new ArrayList<>();
        cases.add(new String[] { "2", "3", "1" });
        assertEquals(1, Main.countSame(cases));
    }
}