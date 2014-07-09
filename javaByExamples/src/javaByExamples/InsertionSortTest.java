package javaByExamples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class Sorter {
	public static void insertionSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			// Insert a[i] into the sorted sublist
			// Select the item at the beginning of the as yet unsorted section
			int toInsert = a[i];
			// Work backwards(<-) through the array, finding where toInsert should go
			int pos = i; // best cases, stay where it stays now
			for (; pos > 0; pos--) {
				// Stopped when a[j-1] <= toInsert, so put v at position j
				if (a[pos - 1] <= toInsert)
					break;
				// If this element is greater than toInsert, move it up one
				a[pos] = a[pos - 1];
			}
			a[pos] = toInsert;
		}
	}
}

public class InsertionSortTest {

	@Test
	public void testInsertionSort() throws Exception {
		int[] ages = { 12, 5, 7, 19, 22, 1 };
		Sorter.insertionSort(ages);
		assertEquals(1, ages[0]);
		assertEquals(22, ages[ages.length - 1]);
	}
}
