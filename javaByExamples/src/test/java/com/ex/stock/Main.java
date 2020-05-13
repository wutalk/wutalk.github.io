package com.ex.stock;

import java.util.Scanner;

/**
 * https://exercise.acmcoder.com/online/online_judge_ques?ques_id=1664&konwledgeId=134
 * <pre>
 * 第一天不变，以后涨一天，跌一天，涨两天，跌一天，涨三天，跌一天...依此
 * 1 n=1
 * +1
 * -1 (2=0+2)
 * +1
 * +1
 * -1 (5=2+3)
 * +1
 * +1
 * +1
 * -1 (9=5+4)
 * +1
 * +1
 * +1
 * +1
 * -1 (14=9+5)
 * ...
 * n
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            int lc = 0;
            while (scanIn.hasNextInt()) {
                int n = scanIn.nextInt();
                int sum = 1;
                int prev = 0;
                int prevMinus = 0;
                for (int i = 1; i < n; i++) {
                    if (i == 2) {
                        prevMinus = 2;
                        prev = 3;
                        --sum;
                        continue;
                    }
                    if (i == prevMinus + prev) {
                        --sum;
                        prevMinus += prev++;
                    }
                    ++sum;
                }
                System.out.println(sum);
            }
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }


}