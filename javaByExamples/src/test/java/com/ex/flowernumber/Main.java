package com.ex.flowernumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            int lc = 0;
            while (scanIn.hasNextLine()) {
                String line = scanIn.nextLine().trim();
                System.out.printf("line#%d: [%s]\n", ++lc, line);
                String[] items = line.split(" ");
                int f = Integer.parseInt(items[0]);
                int t = Integer.parseInt(items[1]);
                System.out.println(output(f, t));
            }
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }

    public static String output(int f, int t) {
        List<String> fn = new ArrayList<>();
        for (int i = f; i <= t; i++) {
            if (isFlowerNumber(i)) {
//                        System.out.printf("%d ", i);
                fn.add(String.valueOf(i));
            }
        }
        if (fn.isEmpty()) {
            return "no";
        } else {
            return String.join(" ", fn);
        }
    }

    public static boolean isFlowerNumber(int i) {
        String s = String.valueOf(i);
        char[] cs = s.toCharArray();
        int sum = 0;
        for (char c : cs) {
            int ci = Integer.parseInt(String.valueOf(c));
            sum += ci * ci * ci;
        }
        return i == sum;
    }


}