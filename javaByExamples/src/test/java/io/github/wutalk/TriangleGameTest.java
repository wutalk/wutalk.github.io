/*
 * @(#)	2014-7-21
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * 
 * @author wutalk
 */
public class TriangleGameTest {
	@Test
	public void testEquilateral() throws Exception {
		System.out.println(TriangleGame.tell(3, 9, 5));
		assertEquals("Sides[5, 5, 5] is a Equilateral Triangle.", TriangleGame.tell(5, 5, 5));
		assertEquals("Sides[3, 5, 5] is a Isosceles Triangle.", TriangleGame.tell(3, 5, 5));
		assertEquals("Sides[3, 5, 9] cannot be a Triangle, "
				+ "the sum of any two sides should be greater than the rest side.",
				TriangleGame.tell(3, 5, 9));

		assertEquals("Sides[5, 5, -5] cannot be a Triangle, all sides should be greater than 0.",
				TriangleGame.tell(5, 5, -5));
		assertEquals("Sides[0, 0, 0] cannot be a Triangle, all sides should be greater than 0.",
				TriangleGame.tell(0, 0, 0));
		assertEquals("Sides[2147483647, 2147483647, 2147483647] is a Equilateral Triangle.",
				TriangleGame.tell(2147483647, 2147483647, 2147483647));
	}

	@Test
	public void testParseInt() throws Exception {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		// -2^31 ~ 2^31-1 (-2147483648 ~ 2147483647)
		int max = Integer.parseInt("2147483647");
		assertEquals(Integer.MAX_VALUE, max);
	}

	@Test
	public void testAll() throws Exception {
		String argsLine = "-p 3 4 5";
		String[] args = argsLine.split("\\s+");
		TriangleGame.main(args);
	}

	@Test
	public void testLongMax() throws Exception {
		long max = Long.MAX_VALUE;
		BigInteger bi = BigInteger.valueOf(max);

		assertEquals(19, String.valueOf(max).length());
		assertEquals(new BigInteger("18446744073709551614"), bi.add(bi));// ok
		assertEquals(-2, max + max);// overflow
	}
}
