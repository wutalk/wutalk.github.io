package com.ex;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class SwitchPositionChecker {
    @Test
    public void testChecker() {
        assertTrue(isSwitched("a", "a"));
        assertTrue(isSwitched("ab", "ba"));
        assertTrue(isSwitched("abc", "bac"));
        assertTrue(isSwitched("abcd", "badc"));
        assertTrue(isSwitched("abcde", "badce"));
    }

    @Test
    public void testBubbleSort() {
        int[] input = new int[]{5, 12, 2, 45, 19};
//        int[] expect = new int[]{45, 19, 12, 5, 2};
        int[] expect = sort(input);
        for (int i = 1; i < expect.length; i++) {
            if (expect[i] > expect[i - 1]) {
                fail();
            }
        }
    }

    private int[] sort(int[] input) {
        for (int i = 0; i < input.length; i++) {

        }

        return new int[0];
    }

    private boolean isSwitched(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        int len = ch1.length;
        char[] newChars = new char[len];
        if (len % 2 != 0) {
            len = len - 1;
            newChars[len] = ch1[len];
        }
        for (int i = 0; i < len; i = i + 2) {
            newChars[i] = ch1[i + 1];
            newChars[i + 1] = ch1[i];
        }
        String result = new String(newChars);
        return s2.equals(result);
    }
}
