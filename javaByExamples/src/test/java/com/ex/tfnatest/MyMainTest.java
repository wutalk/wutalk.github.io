package com.ex.tfnatest;

public class MyMainTest {
    public int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }
}
