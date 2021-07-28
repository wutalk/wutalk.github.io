package com.nowcoder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("{");
//        return sb.toString();
//    }
}

public class ListReverser {
    public ListNode ReverseList(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode node = stack.pop();
        ListNode result = node;
        while (!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }

        return result;
    }
}
