package com.ex.inout;

import java.util.Scanner;

public class Adder {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            // scanIn.nextInt();
            // while (!"".equals(line)) {
            int lc = 0;
            while (scanIn.hasNextLine()) {
                String line = scanIn.nextLine().trim();
                System.out.printf("line#%d: [%s]\n", ++lc, line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }
}
