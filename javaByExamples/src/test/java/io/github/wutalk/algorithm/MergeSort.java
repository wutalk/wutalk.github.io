package io.github.wutalk.algorithm;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MergeSort {
    @Test
    public void testMerge() {
        arraysEqual(new int[]{1, 2}, merge(new int[]{1}, new int[]{2}));
        arraysEqual(new int[]{1, 2, 3}, merge(new int[]{1, 2}, new int[]{3}));
        arraysEqual(new int[]{1, 4, 5, 6, 10, 12, 15}, merge(new int[]{1, 5, 15}, new int[]{4, 6, 10, 12}));
        arraysEqual(new int[]{1, 4, 5, 6, 10, 11, 12}, merge(new int[]{1, 5, 11}, new int[]{4, 6, 10, 12}));
        arraysEqual(new int[]{1, 4, 5, 6, 10, 11, 12, 13}, merge(new int[]{1, 5, 11}, new int[]{4, 6, 10, 12, 13}));
        arraysEqual(new int[]{7, 14, 23, 39, 47, 55, 62, 74, 81, 95},
                merge(new int[]{23, 47, 81, 95}, new int[]{7, 14, 39, 55, 62, 74}));
    }

    @Test
    public void testMergeSort() {
        arraysEqual(new int[]{1, 2}, mergeSort(new int[]{2, 1}));
    }

    private int[] mergeSort(int[] a) {
        recMergeSort(a, 0, a.length);
        return a;
    }

    private void recMergeSort(int[] a, int lowerbound, int higherBound) {
        if (lowerbound == higherBound) {
            return;
        } else {
            int mid = (lowerbound + higherBound) / 2;
            recMergeSort(a, lowerbound, mid);
            recMergeSort(a, mid + 1, higherBound);
            merge3(a, lowerbound, mid+1, higherBound);
        }
    }

    private void merge3(int[] a, int lowerbound, int i, int higherBound) {

    }

    private void arraysEqual(int[] expect, int[] result) {
        assertEquals(Arrays.toString(expect), Arrays.toString(result));
    }

    private int[] merge(int[] a, int[] b) {
        return merge2(a, b);
    }

    private int[] merge1(int[] a, int[] b) {
        int ai = 0;
        int bi = 0;
        int rsize = a.length + b.length;
        int[] result = new int[rsize];

        for (int ri = 0; ri < rsize; ri++) {
            if (ai < a.length && bi < b.length) {
                if (a[ai] < b[bi]) {
                    result[ri] = a[ai++];
                } else {
                    result[ri] = b[bi++];
                }
                continue;
            }
            if (ai < a.length) {
                result[ri] = a[ai++];
            } else if (bi < b.length) {
                result[ri] = b[bi++];
            }
        }
        return result;
    }

    private int[] merge2(int[] a, int[] b) {
        int ai = 0;
        int bi = 0;
        int ri = 0;
        int[] result = new int[a.length + b.length];

        while (ai < a.length && bi < b.length) {
            if (a[ai] < b[bi]) {
                result[ri++] = a[ai++];
            } else {
                result[ri++] = b[bi++];
            }
        }
        while (ai < a.length) {
            result[ri++] = a[ai++];
        }
        while (bi < b.length) {
            result[ri++] = b[bi++];
        }

        return result;
    }


}
