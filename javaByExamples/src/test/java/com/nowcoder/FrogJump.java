package com.nowcoder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * took 10 mins to pass the test
 * https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4
 * 描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 示例1
 * 输入：2
 * 返回值：2
 * 示例2
 * 输入：7
 * 返回值：21
 */
public class FrogJump {
    /**
     * 运行时间：134ms 超过44.38% 用Java提交的代码
     * 占用内存：9588KB 超过17.45%用Java提交的代码
     */
    public int jumpFloor(int target) {
        if (target <= 2) {
            return target;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    /**
     * 运行时间：14ms 超过72.52% 用Java提交的代码
     * 占用内存：9456KB 超过29.51%用Java提交的代码
     */
    public int jumpFloor3(int target) {
        if (target <= 2) {
            return target;
        }
        int[] dp = new int[target];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target - 1];
    }


    @Test
    public void test() {
        assertEquals(1, jumpFloor(1));
        assertEquals(2, jumpFloor(2));
        assertEquals(3, jumpFloor(3));
        assertEquals(21, jumpFloor(7));
    }
}
