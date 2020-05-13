package com.ex.cmcc.first;

import java.util.Scanner;

/**
 * biggest n^m, where min <= n < m <= max
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            int min = scanIn.nextInt();
            int max = scanIn.nextInt();
            int r = getR(min, max);
            System.out.println(r);
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }

    public static int getR(int min, int max) {
        int r = 0;
        for (int i = min; i < max; i++) {
            for (int j = i + 1; j <= max; j++) {
                int t = i ^ j;
                if (t > r) {
                    r = t;
                }
            }
        }
        return r;
    }


}