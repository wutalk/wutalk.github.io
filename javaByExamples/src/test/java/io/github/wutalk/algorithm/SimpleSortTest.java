/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.algorithm;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * all 3 simple sort algorithms are O(n^2) comparison.
 * selection sort eliminates much swap compared to bubble sort.
 * insertion sort is the most efficient one in these 3.
 *
 * @author wutalk
 */
class SimpleSorter {

    /**
     * O(n^2) n*n comparison and n*n swap
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        int size = a.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    // swap(a, j - 1, j);
                    int t = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    /**
     * find out the index of minimal element, and place it in left most
     * O(n^2) n*n comparison but only n swap
     *
     * @param a
     */
    public static void selectionSort(int[] a) {
        int in, out, min;
        for (out = 0; out < a.length - 1; out++) {
            min = out;
            for (in = out + 1; in < a.length; in++) {
                if (a[in] < a[min]) {
                    min = in;
                }
            }
            // swap
            int t = a[out];
            a[out] = a[min];
            a[min] = t;
        }
    }

    /**
     * no swap, only shift(copy)
     *
     * @param a
     */
    public static void insertionSort(int[] a) {
        int in, out;
        for (out = 1; out < a.length; out++) {
            int t = a[out];
            in = out;
            while (in > 0 && a[in - 1] > t) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = t;
        }
    }

    public static void insertionSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // Insert a[i] into the sorted sublist
            // Select the item at the beginning of the as yet unsorted section
            int toInsert = a[i];
            // Work backwards(<-) through the array, finding where toInsert should go
            int pos = i; // best cases, stay where it stays now
            for (; pos > 0; pos--) {
                // Stopped when a[j-1] <= toInsert, so put toInsert at position j
                if (a[pos - 1] <= toInsert)
                    break;
                // If this element is greater than toInsert, move it up one
                a[pos] = a[pos - 1];
            }
            a[pos] = toInsert;
        }
    }

    static void swap(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[j] ^ a[i];
        a[i] = a[i] ^ a[j];
    }
}

public class SimpleSortTest {

    @Test
    public void testBubbleSort() throws Exception {
        int[] scores = {12, 5, 7, 19, 22, 1};
        SimpleSorter.bubbleSort(scores);
        assertSorted(scores);
    }

    @Test
    public void testSwapInt() throws Exception {
        int a = 5;
        int b = 3;
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        assertEquals(3, a);
        assertEquals(5, b);
    }

    @Test
    public void testInsertionSort() throws Exception {
        int[] scores = {12, 5, 7, 19, 22, 1};
        SimpleSorter.insertionSort(scores);
        assertSorted(scores);
    }

    @Test
    public void testSelectionSort() throws Exception {
        int[] scores = {12, 5, 7, 19, 22, 1};
        SimpleSorter.selectionSort(scores);
        assertSorted(scores);
    }

    private void assertSorted(int[] scores) {
        System.out.println(Arrays.toString(scores));
        for (int i = 1; i < scores.length; i++) {
            assertTrue(scores[i] > scores[i - 1]);
        }
        assertEquals(1, scores[0]);
        assertEquals(22, scores[scores.length - 1]);
    }

    @Test
    public void testSwap() throws Exception {
        int[] scores = {2, 5, 8};
        SimpleSorter.swap(scores, 0, 1);
        assertEquals(5, scores[0]);
        SimpleSorter.swap(scores, 0, 2);
        assertEquals(8, scores[0]);
    }
}
