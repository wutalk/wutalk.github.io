/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author wutalk
 */
public class ShiftAsMultiplicationOrDivisionTest {
	@Test
	public void testRightShiftAsDivision() throws Exception {
		int n = 24;
		// shift by 1, divide by 2^1
		assertEquals(12, n >> 1);
		// shift by 2, divide by 2^2
		assertEquals(6, n >> 2);

		n = 15;
		assertEquals(7, n >> 1);
		assertEquals(3, n >> 2);

		n = -24;
		assertEquals(-12, n >> 1);
		assertEquals(-6, n >> 2);
	}

	@Test
	public void testLeftShiftAsMultiplication() throws Exception {
		int n = 7;
		int shift = 1;
		// shift by 1, multiply 2^1
		assertEquals(n * (Math.pow(2, shift)), n << shift, 0.01);
		// shift by 2, multiply 2^2
		shift = 2;
		assertEquals(n * (Math.pow(2, shift)), n << shift, 0.01);

		n = -7;
		assertEquals(-14, n << 1);
		assertEquals(-28, n << 2);
	}
}
