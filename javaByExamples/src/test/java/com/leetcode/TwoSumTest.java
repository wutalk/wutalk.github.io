package com.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;


public class TwoSumTest {
    public int[] twoSum(int[] nums, int target) {
        int[] r = new int[2];
        L:
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    r[0] = i;
                    r[1] = j;
                    break L;
                }
            }
        }
        return r;
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] r = twoSum(nums, target);
        String rs = Arrays.toString(r);
        assertTrue(rs.contains("0"));
        assertTrue(rs.contains("1"));
    }
}
