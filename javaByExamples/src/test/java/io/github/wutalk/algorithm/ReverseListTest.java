/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

import org.junit.Test;


/**
 * 
 * @author wutalk
 */
class Reversor {
	private static final int REVERSE_THRESHOLD = 18;

	public static void reverse(List<?> list) {
		int size = list.size();
		if (size < REVERSE_THRESHOLD || list instanceof RandomAccess) {
			// method #1
			for (int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--)
				swap(list, i, j);
		} else {
			// method #2
			ListIterator fwd = list.listIterator();
			ListIterator rev = list.listIterator(size);
			for (int i = 0, mid = list.size() >> 1; i < mid; i++) {
				Object tmp = fwd.next();
				fwd.set(rev.previous());
				rev.set(tmp);
			}
		}
	}

	public static void swap(List<?> list, int i, int j) {
		final List l = list;
		// l.set(i, l.set(j, l.get(i)));
		l.set(j, l.set(i, l.get(j)));
	}
}

public class ReverseListTest {

	@Test
	public void testReversedList() throws Exception {
		List<String> numbers = new ArrayList<String>();
		numbers.add("one");
		numbers.add("two");
		numbers.add("three");
		numbers.add("four");

		assertEquals("one", numbers.get(0));

		// Collections.reverse(numbers);
		Reversor.reverse(numbers);

		assertEquals("four", numbers.get(0));
	}
}
