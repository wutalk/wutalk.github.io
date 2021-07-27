package com.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {

    @Test
    public void testSearch() {
        assertEquals(2, search(new int[]{1, 2, 4, 4, 5}, 4));
        assertEquals(-1, search(new int[]{1, 2, 4, 4, 5}, 3));
        assertEquals(0, search(new int[]{1, 1, 1, 1, 1}, 1));
    }

    public int search(int[] nums, int target) {
        // write code here
        int low = 0;
        int high = nums.length - 1;
        if (target < nums[low] || target > nums[high]) {
            return -1;
        }
        return doSearch(nums, high, low, target);
    }

    int doSearch(int[] nums, int high, int low, int target) {
        if (target == nums[low]) {
            return low;
        }
        if (target == nums[high]) {
            return high;
        }
        int mid = high / 2;
        if (mid == 0) {
            return target == nums[0] ? 0 : -1;
        }
        if (target < nums[mid]) {
            high = mid - 1;
            if (high < low) {
                return -1;
            } else {
                return doSearch(nums, high, low, target);
            }
        } else if (target > nums[mid]) {
            low = mid + 1;
            if (low > high) {
                return -1;
            } else {
                return doSearch(nums, high, low, target);
            }
        } else {
            return mid;
        }
    }
}
