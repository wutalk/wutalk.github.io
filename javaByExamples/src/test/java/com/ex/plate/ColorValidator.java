package com.ex.plate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ColorValidator {

    public static void main(String[] args) {
        ColorValidator validator = new ColorValidator();
        Scanner scanIn = new Scanner(System.in);
        String t = scanIn.nextLine();
        int caseNumber = Integer.parseInt(t);
        for (int i = 0; i < caseNumber; i++) {
            String n = scanIn.nextLine();
            int plateSize = Integer.parseInt(n);
            List<int[]> plates = new ArrayList<int[]>();
            for (int j = 0; j < plateSize; j++) {
                String line = scanIn.nextLine();
                String[] itemStrs = line.split(" ");
                int items[] = new int[itemStrs.length];
                for (int k = 0; k < itemStrs.length; k++) {
                    items[k] = Integer.parseInt(itemStrs[k]);
                }
                plates.add(items);
            }
            System.out.println();
            for (int[] it : plates) {
                System.out.println(Arrays.toString(it));
            }
            System.out.println();
            boolean valid = validator.validate(plates);
            if (valid) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        scanIn.close();
    }

 /*   public static void main(String[] args) {
        List<int[]> plates = new ArrayList<int[]>();
        plates.add(new int[]{0, 1, 0});
        plates.add(new int[]{1, 0, 1});
        plates.add(new int[]{0, 1, 0});
//        plates.add(new int[]{1, 0, 1});
        boolean valid = validate(plates);
        if (valid) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }*/

    public boolean validate(List<int[]> plates) {
        int[] preLine = plates.get(0);
        boolean valid = verifyLine(preLine);
        if (!valid) {
            return false;
        }
        for (int i = 1; i < plates.size(); i++) {
            int[] line = plates.get(i);
            if (preLine[0] == line[0]) {
                return false;
            }
            int previous = line[0];
            for (int j = 1; j < plates.size(); j++) {
                int current = line[j];
                if (current == previous || preLine[j] == line[j]) {
                    return false;
                }
                previous = current;
            }
            preLine = line;
        }
        return true;
    }

    private boolean verifyLine(int[] line) {
        int previous = line[0];
        for (int i = 1; i < line.length; i++) {
            int current = line[i];
            if (current == previous) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    /**
     * simplified to compare if any 2 adjacent number is the same, if so not valid
     *
     * @param plates
     * @return
     */
    public boolean validateRefactor(List<int[]> plates) {
        int[] preLine = createPreviousLine(plates);
        for (int i = 0; i < plates.size(); i++) {
            int[] line = plates.get(i);
            int previous = -1;
            for (int j = 0; j < plates.size(); j++) {
                int current = line[j];
                if (current == previous || preLine[j] == line[j]) {
                    return false;
                }
                previous = current;
            }
            preLine = line;
        }
        return true;
    }

    private int[] createPreviousLine(List<int[]> plates) {
        int len = plates.get(0).length;
        int[] preLine = new int[len];
        for (int i = 0; i < len; i++) {
            preLine[i] = -1;
        }
        return preLine;
    }

}
/*
1 // t
2 // n
0 0
0 0

1
2
0 0
0 0

3 // t
2 // n
0 0
0 0
3
1 0 1
0 0 0
1 1 0
2
0 1
1 0

2
1 0 1 0
0 1 1 0
0 1 0 1
1 0 1 0

 */