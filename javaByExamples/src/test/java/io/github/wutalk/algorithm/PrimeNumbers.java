package io.github.wutalk.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PrimeNumbers {

    @Test
    public void testListPrimeNumbers() {
        int n = 30;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        assertTrue(isPrime(1));
        assertTrue(isPrime(3));
        assertTrue(isPrime(5));
        assertTrue(isPrime(7));
        assertTrue(isPrime(17));
    }

    public boolean isPrime(int num) {
        if (num == 1) {
            return true;
        }
        int cnt = 0;
        for (int j = num; j >= 1; j--) {
            if (num % j == 0) {
                cnt++;
            }
        }
        return cnt == 2;// 1 and itself
    }
}
