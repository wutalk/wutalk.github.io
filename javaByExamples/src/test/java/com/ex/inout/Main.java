package com.ex.inout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            // scanIn.nextInt();
            // while (!"".equals(line)) {
            int lc = 0;
            while (scanIn.hasNext()) {
                String line = scanIn.nextLine().trim();
                System.out.printf("line#%d: [%s]\n", ++lc, line);
                String[] items = line.split(" ");
                if (items.length == 2) {
                    int personNumber = Integer.parseInt(items[0]);
                    int caseNumber = Integer.parseInt(items[1]);
                    // System.out.printf("%d, %d\n", personNumber, caseNumber);

                    List<String[]> cases = new ArrayList<>();
                    for (int i = 0; i < caseNumber; i++) {
                        line = scanIn.nextLine().trim();
                        System.out.printf("line#%d: [%s]\n", ++lc, line);
                        items = line.split(" ");
                        if (!"0".equals(items[2].trim())) {
                            cases.add(new String[] { items[0].trim(), items[1].trim() });
                        }
                    }
                    // for (String[] c : cases) {
                    // System.out.println(Arrays.toString(c));
                    // }
                    Set<String> same = countSame(cases);
                    System.out.println(same);
                    System.out.println(same.size() - 1);
                }
            }
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }

    public static Set<String> countSame(List<String[]> cases) {
        Set<String> same = new HashSet<>();
        same.add("1");
        for (String[] p : cases) {
            if (same.contains(p[0])) {
                same.add(p[1]);
            } else if (same.contains(p[1])) {
                same.add(p[0]);
            }
        }
        System.out.println(same);
        for (String[] p : cases) {
            if (same.contains(p[0])) {
                same.add(p[1]);
            } else if (same.contains(p[1])) {
                same.add(p[0]);
            }
        }
        return same;
    }

}