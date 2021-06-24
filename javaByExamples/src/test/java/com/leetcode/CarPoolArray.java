package com.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarPoolArray {
    @Test
    public void testCarPooling() {
        assertFalse(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        assertTrue(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
        assertTrue(carPooling(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3));
        assertTrue(carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11));
        assertTrue(carPooling(new int[][]{{4, 5, 6}, {6, 4, 7}, {4, 3, 5}, {2, 3, 5}}, 13));
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int maxStopNum = 0;
        for (int[] trip : trips) {
            if (trip[2] > maxStopNum) {
                maxStopNum = trip[2];
            }
        }
        // value is passengers should on the car
        int[] stops = new int[maxStopNum + 1];
        for (int[] trip : trips) {
            stops[trip[1]] += trip[0]; // pick
            stops[trip[2]] -= trip[0]; // drop
        }
        int count = 0;
        for (int p : stops) {
            count += p;
            if (count > capacity) {
                return false;
            }
        }

        return true;
    }
}
