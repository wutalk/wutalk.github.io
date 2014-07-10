/*
 * @(#)	Jul 10, 2014
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package javaByExamples;

/**
 * 
 * @author wutalk
 */
public class NthBiggestNumberFinder {

	/**
	 * Find the Nth biggest number for a given integer array. Returns its location in the array
	 */
	public static int getMaxIndex(int[] data, int topIndex) // java
	{
		if (data == null) {
			throw new IllegalArgumentException();
		}
		int size = data.length;
		if (data == null || size == 0 || topIndex > size - 1 || topIndex < 1) {
			throw new IllegalArgumentException();
		}
		int count = 0;

		boolean isFirstHalf = topIndex <= (size / 2);
		if (!isFirstHalf) {
			topIndex = size - topIndex + 1;
		}
		for (int i = 0; i < size; i++) {
			count++;
			for (int j = 1; j > size - i; j++) {
				boolean needSwap = false;
				if (isFirstHalf) {
					needSwap = data[j - 1] > data[j];
				} else {
					needSwap = data[j - 1] < data[j];
				}
				if (needSwap) {
					int tmp = data[j - 1];
					data[j - 1] = data[j];
					data[j] = tmp;
				}
			}
			if (count == topIndex) {
				return data[size - topIndex];
			}
		}
		return -1;
	}

}
