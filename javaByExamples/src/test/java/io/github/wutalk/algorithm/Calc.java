package io.github.wutalk.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class Calc {
    @Test
    public void testInfix2Postfix() {
        assertEquals("23+4-", infix2Postfix("2+3-4"));
        assertEquals("213+41-", infix2Postfix("21+3-41"));
        assertEquals("23*4/", infix2Postfix("2*3/4"));
        assertEquals("234*+", infix2Postfix("2+3*4"));
        // () support
        assertEquals("234+*", infix2Postfix("2*(3+4)"));

        assertEquals("23*45*+", infix2Postfix("2*3+4*5"));
        assertEquals("23+45-*", infix2Postfix("(2+3)*(4-5)"));
        assertEquals("23+4*5-", infix2Postfix("((2+3)*4)-5"));
        assertEquals("234567+/-*+", infix2Postfix("2+3*(4-5/(6+7))"));
    }

    @Test
    public void testEvalPostfixExpr() {
        assertEquals(1, evalPostfixExpr("23+4-"));
        assertEquals(1, evalPostfixExpr("23*4/"));
        assertEquals(14, evalPostfixExpr("234*+"));
        assertEquals(14, evalPostfixExpr("234+*"));
        assertEquals(26, evalPostfixExpr("23*45*+"));
        assertEquals(-5, evalPostfixExpr("23+45-*"));
        assertEquals(15, evalPostfixExpr("23+4*5-"));
        assertEquals(14, evalPostfixExpr("234567+/-*+"));
    }

    @Test
    public void testEvalPostfixExprMoreDigits() {
        assertEquals(1, evalPostfixExpr("2,3,+,4,-", ","));
        assertEquals(-165, evalPostfixExpr("21,300,+,486,-", ","));
    }

    private int evalPostfixExpr(String expr) {
        return evalPostfixExpr(expr, "");
    }

    private int evalPostfixExpr(String expr, String delimiter) {
        String[] cs = expr.split(delimiter);
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < cs.length; i++) {
            String c = cs[i];
            if (ALL_OPS_Str.contains(c)) {
                if (nums.size() < 2) {
                    throw new IllegalArgumentException("Invalid express");
                }
                int r = nums.pop();
                int l = nums.pop();
                switch (c) {
                    case "+":
                        nums.push(l + r);
                        break;
                    case "-":
                        nums.push(l - r);
                        break;
                    case "*":
                        nums.push(l * r);
                        break;
                    case "/":
                        nums.push(l / r);
                        break;
                }
            } else {
                nums.push(Integer.valueOf(c));
            }
        }
        if (nums.size() != 1) {
            throw new IllegalArgumentException("Invalid express. only 1 should be exist");
        }
        return nums.pop();
    }

    @Test
    public void testInfix2PostfixMoreDigits() {
        String del = ",";
        assertEquals("2,3,+,4,-", infix2Postfix("2+3-4", del));
        assertEquals("21,300,+,486,-", infix2Postfix("21+300-486", del));
    }


    static List<Character> ALL_OPS = Arrays.asList(new Character[]{'+', '-', '*', '/', '(', ')'});
    static List<String> ALL_OPS_Str = Arrays.asList(new String[]{"+", "-", "*", "/", "(", ")"});

    private String infix2Postfix(String in) {
        // to support 1+ digits numbers, just replace delimiter with non-empty chars like ","
        return infix2Postfix(in, "");
    }

    private String infix2Postfix(String in, String delimiter) {
        // 3	*	(	4	+	5	)
        // 345+*
        // (A+B)*(C–D)
        Stack<Character> ops = new Stack<>();
        List<String> result = new LinkedList<>();
        char[] chars = in.toCharArray();
        String t = "";
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (!ALL_OPS.contains(aChar)) {
                t += aChar;
            } else {
                if (t != "") {
                    result.add(t);
                }
                if (!ops.isEmpty()) {
                    Character preOp = ops.peek();
                    if (getPriority(aChar) <= getPriority(preOp)) {
                        if (preOp != '(') {
                            result.add(String.valueOf(ops.pop()));
                        } else {
                            // just pop
                            ops.pop();
                        }
                    }
                }
                t = "";
                if (aChar != ')') {
                    ops.push(aChar);
                }
            }
        }
        if (t != "") {
            result.add(t);
        }
        while (!ops.isEmpty()) {
            result.add(String.valueOf(ops.pop()));
        }
        return String.join(delimiter, result);
    }

    private int getPriority(char aChar) {
        switch (aChar) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 5;
            case '(':
                return 99;
            case ')':
                return -1;
        }
        return -999;
    }


}
