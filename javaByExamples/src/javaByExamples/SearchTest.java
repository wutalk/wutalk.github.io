/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package javaByExamples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * 
 * @author wutalk
 */
class SearchEnginee {
	public static int binarySearch(int[] a, int key) {
		return binarySearch(a, 0, a.length, key);
	}

	public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			int midVal = a[mid];
			if (midVal < key) {
				low = mid + 1;
			} else if (key < midVal) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -(low + 1);
	}
}

/**
 * @description
 * 
 * @author owu
 */
public class SearchTest {

	@Test
	public void testBinarySearch() throws Exception {
		int[] a = { 2, 4, 12, 50, 126, 1000 };
		Sorter.bubbleSort(a);
		int pos = SearchEnginee.binarySearch(a, 126);
		assertEquals(4, pos);
		int pos2 = SearchEnginee.binarySearch(a, 5);
		assertEquals(-2 - 1, pos2);
	}
}
