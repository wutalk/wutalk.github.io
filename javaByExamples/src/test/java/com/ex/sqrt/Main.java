package com.ex.sqrt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            int lc = 0;
            while (scanIn.hasNextLine()) {
                String line = scanIn.nextLine().trim();
//                System.out.printf("line#%d: [%s]\n", ++lc, line);
                String[] items = line.split(" ");
                int n = Integer.parseInt(items[0]);
                int m = Integer.parseInt(items[1]);
                double sum = getSum(n, m);
                System.out.printf("%.2f\n", sum);
            }
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }

    public static double getSum(int n, int m) {
        double sum = n;
        double preN = n;
        for (int i = 1; i < m; i++) {
            double sq = Math.sqrt(preN);
            sum += sq;
            preN = sq;
        }
        return sum;
    }

}