/*
 * @(#)	2015年4月24日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

/**
 * 
 * @author wutalk
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 1));
        Main app = new Main();

        System.out.println("N2 = 14");
        app.f(14, 1, 18);
    }

    void f(long start, int n, int max) {
        long pow = (long) Math.pow(2, n);
        long n3 = 2 * start - 70 + pow * 77;
        // String formula = String.format("N%s = 2*%s - 70 + (2**%s)*77 => ", n + 2, start, n);
        String formula = String.format("N%s = 2N%s - 70 + (2^%s)*77 => ", n + 2, n + 1, n);
        System.out.println(formula + n3);
        if (n < max) {
            f(n3, ++n, max);
        }
    }
}
