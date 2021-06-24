package com.ex.tfnatest;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ForthTest {

    public boolean parseBoolExpr(String expression) {
        // &(t,f,(f,|(&(t,f,t),!(t)))
        Deque<Character> ops = new ArrayDeque<>();
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ',') {
                continue;
            }
            if (aChar != ')') {
                ops.push(aChar);
            } else {
                List<Character> tc = new ArrayList<>();
                while (!ops.isEmpty()) {
                    Character t = ops.pop();
                    if (t != '(') {
                        tc.add(t);
                    } else {
                        t = ops.pop(); // real op
                        if (t == '!') {
                            if (tc.get(0) == 't') {
                                ops.push('f');
                            } else if (tc.get(0) == 'f') {
                                ops.push('t');
                            }
                        } else if (t == '&') {
                            char out = 't';
                            for (char c : tc) {
                                if (c == 'f') {
                                    out = 'f';
                                }
                            }
                            ops.push(out);
                        } else if (t == '|') {
                            char out = 'f';
                            for (char c : tc) {
                                if (c == 't') {
                                    out = 't';
                                }
                            }
                            ops.push(out);
                        }
                        break;
                    }
                }
            }


        }
        return ops.pop() == 't';
    }


    @Test
    public void test() {
        assertTrue(parseBoolExpr("!(f)"));
        assertTrue(parseBoolExpr("|(f,t)"));
        assertFalse(parseBoolExpr("&(t,f)"));
        assertFalse(parseBoolExpr("|(&(t,f,t),!(t))"));

        assertTrue(parseBoolExpr("|(f,&(t,t))"));
//        assertFalse(parseBoolExpr("&(t,f,(f,|(&(t,f,t),!(t)))"));

    }

//
//    public boolean parseBoolExprOld(String expression) {
//
//        Deque<Character> ops = new ArrayDeque<>();
//        Deque<Boolean> vals = new ArrayDeque<>();
//        char[] chars = expression.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            char aChar = chars[i];
//            if (isOp(aChar) || aChar == '(') {
//                ops.push(aChar);
//            } else if (aChar == 't') {
//                vals.push(true);
//            } else if (aChar == 'f') {
//                vals.push(false);
//            }
//
//            if (aChar == ')') {
//                Character op = ops.pop();// pop )
//                op = ops.pop();
//                if (op == '!') {
//                    Boolean v = vals.pop();
//                    vals.push(!v);
//                } else if (op == '&') {
//                    Boolean t = true;
//                    while (!vals.isEmpty()) {
//                        if (!vals.pop()) {
//                            t = false;
//                        }
//                    }
//                    vals.push(t);
//                }
//            }
//        }
//
//        boolean r = vals.pop();
//        if (!vals.isEmpty()) {
//            System.out.println("xxx");
//        }
//        return r;
//    }
//
//    private boolean isOp(char aChar) {
//        return aChar == '!' || aChar == '&' || aChar == '|';
//    }

}
