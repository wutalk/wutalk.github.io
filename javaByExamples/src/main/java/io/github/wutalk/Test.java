package io.github.wutalk;

interface Face {
    int c = 40;
}

public class Test implements Face {
    private static int c;

    public static void main(String[] args) {
        System.out.println(++c); // 1
        System.out.println(getValue(1)); // 6
    }

    public static int getValue(int i) {
        int r = 0;
        switch (i) {
            case 1:
                r += i;
            case 2:
                r += i * 2;
            case 3:
                r += i * 3;
        }
        return r;
    }
}
