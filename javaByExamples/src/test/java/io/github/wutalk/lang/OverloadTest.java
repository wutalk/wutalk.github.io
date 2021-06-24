package io.github.wutalk.lang;

import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class OverloadTest {

    @Test
    public void testOverload() {
        Dog a = new Dog();
        System.out.println(a);
        a.sound("dog");
        ClassLoader cl = a.getClass().getClassLoader();
        System.out.println(cl);
        System.out.println(cl.getParent());
        System.out.println(cl.getParent().getParent());
    }

    @Test
    public void testBitOperation() {
        System.out.printf("%4s\n", Integer.toBinaryString(4));
        System.out.printf("%4s\n", Integer.toBinaryString(8));
        assertEquals(6, 3 << 1); // 3*2
        assertEquals(8, 4 << 1); // 4*2
        assertEquals(2, 4 >> 1); // 4/2
    }

    @Test
    public void testHashMapCap() {
        assertEquals(8, tableSizeFor(8));
        assertEquals(16, tableSizeFor(15));
        assertEquals(32, tableSizeFor(18));
        assertEquals(64, tableSizeFor(33));
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void testPassByValue() {
        Member m1 = new Member("M1");
        Member m2 = new Member("M2");

        swap(m1, m2);

        System.out.println("m1=" + m1);
        System.out.println("m2=" + m2);

        HashSet s = new HashSet<String>();
        s.add(new String("hello"));
        s.add(new String("hello"));
    }
    /*
        a=M2
        b=M1
        m1=M1
        m2=M2
     */

    void swap(Member a, Member b) {
        Member t = a;
        a = b;
        b = t;
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}

class Member {
    String name;

    public Member(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Animal {
    void sound() {
        System.out.println("sound");
    }

    void sound(String name) {
        System.out.println("sound " + name);
    }
}

class Dog extends Animal {
    void sound(String name, int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%d sound %s\n", i, name);
        }
    }
}
