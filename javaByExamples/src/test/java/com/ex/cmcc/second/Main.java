package com.ex.cmcc.second;

import java.util.Scanner;

/**
 * at least how long should be the ladder to hang all posters
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);

            int n = scanIn.nextInt();
            int h = scanIn.nextInt();

            int[] wallPoints = new int[n];
            for (int i = 0; i < n; i++) {
                wallPoints[i] = scanIn.nextInt();
            }
            int[] posterLengths = new int[n];
            for (int i = 0; i < n; i++) {
                posterLengths[i] = scanIn.nextInt();
            }

            double maxLadder = 0;
            for (int i = 0; i < wallPoints.length; i++) {
                double ladder = Math.ceil(wallPoints[i] - posterLengths[i] * 0.25 - h);
                if (ladder > maxLadder) {
                    maxLadder = ladder;
                }
            }
            System.out.println((int) maxLadder);
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }

}