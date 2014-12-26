/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * n >= 2, each number is the sum of previous 2 numbers
 * 
 * @author wutalk
 */
class Fibonacci {
	static int fib(int n) {
		if (n < 2) {
			return n;
		} else {
			return fib(n - 2) + fib(n - 1);
		}
	}

	static int fibIterative(int n) {
		if (n < 2) {
			return n;
		} else {
			int sum = 0;
			int f1 = 1;
			int f2 = 1;
			for (int i = 2; i < n; i++) {
				sum = f1 + f2;
				System.out.print(sum + ", ");
				f1 = f2;
				f2 = sum;
			}
			return sum;
		}
	}
}

public class FibonacciTest {
	@Test
	public void testFibRecursive() throws Exception {
		int n = 10;
		int expected = 55;
		// 2, 3, 5, 8, 13, 21, 34, 55
		assertEquals(expected, Fibonacci.fib(n));
		assertEquals(expected, Fibonacci.fibIterative(n));
	}
}
