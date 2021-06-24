package com.leetcode;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode t = this;
//        StringBuilder sb = new StringBuilder("(");
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        while (t.next != null) {
            t = t.next;
            sb.append(" -> ");
            sb.append(t.val);
        }
//        sb.append(")");
        return sb.toString();
    }
}

public class AddTwoNumbers {

    @Test
    public void test() {
        ListNode l1 = buildList(new int[]{3, 4, 2});
        ListNode l2 = buildList(new int[]{4, 6, 5});

        System.out.println(l1);
        System.out.println(l2);

        System.out.println(toNumber(l1));
        System.out.println(toNumber(l2));

        ListNode r = addTwoNumbers(l1, l2);
        System.out.println(r);
        assertEquals("7 -> 0 -> 8", r.toString());
        assertEquals("0", addTwoNumbers(buildList(new int[]{0}), buildList(new int[]{0})).toString());

        BigInteger bi = new BigInteger("1000000000000000000000000000001");

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger sum = toNumber(l1).add(toNumber(l2));
        char[] chars = sum.toString().toCharArray();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            nums[i] = Integer.parseInt("" + chars[i]);
        }

        return buildList(nums);
    }


    ListNode buildList(int[] nums) {
        ListNode l = null;
        ListNode next = null;
        if (nums.length > 0) {
            l = new ListNode(nums[0]);
            next = l;
        }
        for (int i = 1; i < nums.length; i++) {
            l = new ListNode(nums[i]);
            l.next = next;
            next = l;
        }
        return l;
    }

    BigInteger toNumber(ListNode l) {
        List<String> nums = new ArrayList<>();
        nums.add(Integer.toString(l.val));
        while (l.next != null) {
            l = l.next;
            nums.add(Integer.toString(l.val));
        }
        Collections.reverse(nums);

        String numStr = String.join("", nums);

        return new BigInteger(numStr);
    }

}
