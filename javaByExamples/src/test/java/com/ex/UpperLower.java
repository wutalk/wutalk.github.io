package com.ex;

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class UpperLower {

    public static final int BASE16 = 16;

    @Test
    public void testUpperLower() {
        String str = "HeLLo, WorLD!";
        String result = convert(str);
        assertEquals("hEllO, wORld!", result);
    }

    private String convert(String str) {
        char[] chars = str.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 32);
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
            }
            result[i] = c;
        }
        return new String(result);
    }

    @Test
    public void testCountChars() {
        String s = "Golang version of Pact. Pact is a contract testing framework for HTTP APIs " +
                "and non-HTTP asynchronous messaging systems.Enables consumer driven contract testing, " +
                "providing a mock service and DSL for the consumer project, and interaction playback " +
                "and verification for the service Provider project.";
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            Integer cnt = charCount.get(c);
            if (cnt == null) {
                cnt = 0;
            }
            charCount.put(c, cnt + 1);
        }
        System.out.println(charCount);
    }

    @Ignore
    @Test
    public void testHex() {
        int i = 0;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 5;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 10;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 15;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 16;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 17;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 32;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 255;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 256;
        assertEquals(Integer.toHexString(i), toHex(i));
        i = 32000;
        assertEquals(Integer.toHexString(i), toHex(i));
    }

    // TODO not finished
    String toHex(int i) {
        StringBuilder sb = new StringBuilder();
        int ori = i;
        int r = i / BASE16;
        if (r == 0) {
            int t = i % BASE16;
            return toBaseHex(t);
        }
        sb.append(toBaseHex(r).toCharArray()[0]);
        while (r > 0) {
            i = i - r * BASE16;
            r = i / BASE16;
            if (i == 0 || r != 0) {
                sb.append(toBaseHex(r));
            }
        }
        if (ori != i) {
            int t = i % BASE16;
//            if (t != 0) {
            sb.append(toBaseHex(t));
//            }
        }
        return sb.toString();
    }

    private String toBaseHex(int t) {
        String str = String.valueOf(t);
        switch (t) {
            case 10:
                str = "a";
                break;
            case 11:
                str = "b";
                break;
            case 12:
                str = "c";
                break;
            case 13:
                str = "d";
                break;
            case 14:
                str = "e";
                break;
            case 15:
                str = "f";
                break;
        }
        return str;
    }
}
