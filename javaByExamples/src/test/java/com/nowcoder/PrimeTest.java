package com.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PrimeTest {
    @Test
    public void testPf() {
        assertArrayEquals(new int[]{2, 2, 5, 5}, primeFactorization(100));
        assertArrayEquals(new int[]{17}, primeFactorization(17));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int整型
     * @return int整型一维数组
     */
    public int[] primeFactorization(int n) {
        // write code here
        List<Integer> r = new ArrayList<>();

        if (n == 1 || n == 2) {
            r.add(n);
        }
        if (isPrime(n)) {
            return new int[]{n};
        }
        Integer[] primes = listPrimes(n / 2 + 1);
        for (int i = 0; i < n; i++) {
            if (n % primes[i] == 0) {
                r.add(primes[i]);
                n = n / primes[i];
                i = -1;
            }
        }
        Collections.sort(r);
        int[] result = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            result[i] = r.get(i);
        }
        return result;
    }

    private Integer[] listPrimes(int n) {
        List<Integer> r = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                r.add(i);
            }
        }

        return r.toArray(new Integer[0]);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i < n / 2 + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testIsPrime() {
        assertTrue(isPrime(1));
        assertTrue(isPrime(2));
        assertTrue(isPrime(3));
        assertTrue(isPrime(5));
        assertTrue(isPrime(7));

        assertFalse(isPrime(4));
        assertFalse(isPrime(6));
    }
}
