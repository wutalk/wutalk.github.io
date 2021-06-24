package com.leetcode;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CarPool {

    @Test
    public void testCarPooling() {
        assertFalse(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        assertTrue(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
        assertTrue(carPooling(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3));
        assertTrue(carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11));
        assertTrue(carPooling(new int[][]{{4, 5, 6}, {6, 4, 7}, {4, 3, 5}, {2, 3, 5}}, 13));
    }

    /*
    Example 1:
Input:   trips = [[2,1,5], [3,3,7]], capacity = 4
Output:  false

Example 2:
Input:   trips = [[2,1,5], [3,3,7]], capacity = 5
Output:  true

Example 3:
Input:   trips = [[2,1,5], [3,5,7]], capacity = 3
Output:  true

Example 4:
Input:   trips = [[3,2,7], [3,7,9], [8,3,9]], capacity = 11
Output:  true
     */


    public boolean carPooling(int[][] trips, int capacity) {
        // your solution goes here
        Map<Integer, Stop> sts = new TreeMap<>();
        for (int[] trip : trips) {
//            stops.add(new Stop(trip[1], trip[0], 0));
//            stops.add(new Stop(trip[2], 0, trip[0]));

            Stop start = sts.get(trip[1]);
            if (start == null) {
                sts.put(trip[1], new Stop(trip[1], trip[0], 0));
            } else {
                start.addPick(trip[0]);
            }

            Stop end = sts.get(trip[2]);
            if (end == null) {
                sts.put(trip[2], new Stop(trip[2], 0, trip[0]));
            } else {
                end.addDrop(trip[0]);
            }
        }

        int remainCap = capacity;
        for (Map.Entry<Integer, Stop> s : sts.entrySet()) {
            remainCap = remainCap - s.getValue().getPick() + s.getValue().getDrop();
            if (remainCap >= 0) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    class Stop {
        int stop;
        int pick;
        int drop;

        public Stop(int stop, int pick, int drop) {
            this.stop = stop;
            this.pick = pick;
            this.drop = drop;
        }

        public int getStop() {
            return stop;
        }

        public int getPick() {
            return pick;
        }

        public void addPick(int pick) {
            this.pick += pick;
        }

        public void addDrop(int drop) {
            this.drop += drop;
        }

        public int getDrop() {
            return drop;
        }

        @Override
        public String toString() {
            return "Stop{" +
                    "stop=" + stop +
                    ", pick=" + pick +
                    ", drop=" + drop +
                    '}';
        }
    }
}
