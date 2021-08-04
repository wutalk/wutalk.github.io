package com.ex.cibtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolutionTest {

    Main s = new Main();

    @Test
    public void test() {
        assertTrue(s.isPrime(2));
        assertTrue(s.isPrime(3));
        assertTrue(s.isPrime(5));
        assertTrue(s.isPrime(7));
        assertTrue(s.isPrime(11));
        assertTrue(s.isPrime(13));
        assertTrue(s.isPrime(17));
        assertEquals(1, 1);
    }

    @Test
    public void testListPrime() {
        System.out.println(s.listPrimes(50));
    }

    @Test
    public void solutionTest() {
        assertEquals("2*3*3*5", s.solution(90));
    }
}
