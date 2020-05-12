package com.ex.yuedeer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testCalc() {
        assertEquals(1, Main.matchCount("a", "1"));
        assertEquals(1, Main.matchCount("@", "0"));
        assertEquals(0, Main.matchCount("@", "1"));
        assertEquals(0, Main.matchCount("a", "0"));
        assertEquals(5, Main.matchCount("abc123%^&&", "1110001100"));
        assertEquals(6, Main.matchCount("@!%12dgsa", "010111100"));
    }
}