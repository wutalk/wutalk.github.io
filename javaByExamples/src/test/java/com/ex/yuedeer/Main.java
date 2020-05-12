package com.ex.yuedeer;

public class Main {
    public static void main(String[] args) {
        String in = "@!%12dgsa";
        String rule = "010111100";

        int count = matchCount(in, rule);
        double f = 100.0 * count / in.length();
        System.out.printf("%.2f%%\n", f);
    }

    public static int matchCount(String in, String rule) {
        char[] inChs = in.toCharArray();
        char[] ruleChs = rule.toCharArray();
        int count = 0;
        for (int i = 0; i < inChs.length; i++) {
            char t = inChs[i];
            char out = '0';
            if ((t >= 'A' && t <= 'Z') || (t >= 'a' && t <= 'z') || (t >= '0' && t <= '9')) {
                out = '1';
            }
            if (out == ruleChs[i]) {
                count++;
            }
        }
        return count;
    }

}