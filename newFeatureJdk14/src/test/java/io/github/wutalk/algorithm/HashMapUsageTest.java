package io.github.wutalk.algorithm;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapUsageTest {

    //    @Test(expected = NullPointerException.class)
    @Test
    @DisplayName("Hashtable key and value both can NOT be null")
    public void testHashtableNullable() {
        Hashtable ht = new Hashtable();
        assertThrows(NullPointerException.class, () -> {
            ht.put(null, "hello");
        });
        assertThrows(NullPointerException.class, () -> {
            ht.put("name", null);
        });
    }

    @Test
    @DisplayName("HashMap key can be null")
    public void testHashMapNullable() {
        HashMap m = new HashMap();
        Object k = null;
        String v = "hello";
        m.put(k, v);
        assertEquals(v, m.get(k));

        v = "world";
        m.put(k, v);
        assertEquals(v, m.get(k));
    }

    @Test
    public void testHashTableDemo() {
        System.out.println(1 << 5);
        String[] a = new String[16];
        String n = "bob";
//        String n = null;
        int h = hash(n);
        System.out.println(h); // 97716
        System.out.println((a.length - 1) & h); // 4

        short oldCapacity = 8;
        int newCapacity = (oldCapacity << 1) + 1;
        System.out.printf("%6s, %d\n", Integer.toBinaryString(oldCapacity), oldCapacity);
        System.out.printf("%6s, %d\n", Integer.toBinaryString(newCapacity), newCapacity);

        int oldCap = 16;
        int newCap = oldCap << 1;
        System.out.printf("%6s, %d\n", Integer.toBinaryString(oldCap), oldCap);
        System.out.printf("%6s, %d\n", Integer.toBinaryString(newCap), newCap);
    }

    // copied from HashMap.java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
