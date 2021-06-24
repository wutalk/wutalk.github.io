package com.example.lang;

import org.junit.Test;

import java.util.Calendar;

public class ControlStructuresTest {

    @Test
    public void testSwitch() {
        int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        System.out.println(i);
        // required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum'
        switch (i) {
            case Calendar.MONDAY:
                System.out.println("Hello Monday");
                break;
            case Calendar.TUESDAY:
                System.out.println("Hello TUESDAY");
                break;
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
                System.out.println("Hello working days");
                break;
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:
                System.out.println("Hello weekend days");
        }

    }
}
