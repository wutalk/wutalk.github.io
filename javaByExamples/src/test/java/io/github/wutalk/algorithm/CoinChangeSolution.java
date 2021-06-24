package io.github.wutalk.algorithm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。

示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：

输入：coins = [2], amount = 3
输出：-1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoinChangeSolution {

    class Case {

    }

    @Test
    public void test() {
        assertEquals(3, coinChange(new int[]{1, 2, 5}, 11));
    }

    Map<Integer, Integer> m = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int c : coins) {
            int subAmount = amount - c;
            if (subAmount < 0) {
                continue;
            }
            if (subAmount == 0) {
                min = 1;
            }
            int count = 0;
            if (m.containsKey(subAmount)) {
                count = m.get(subAmount);
            } else {
                count = coinChange(coins, subAmount);
                m.put(subAmount, count);
            }
            if (count == -1) {
                continue;
            }
            if (count < min) {
                min = count;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min + 1;
    }

}

