package com.ex.cibtest;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main s = new Main();
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            String str = String.format("%d=%s", n, s.solution(n));
            System.out.println(str);
        }
    }

    String solution(int n) {
        int target = n;
        if (isPrime(n)) {
            return String.valueOf(n);
        }
        List<Integer> r = new ArrayList<>();
        List<Integer> primes = listPrimes(n / 2 + 1);
        for (int i = primes.size() - 1; i >= 0; i--) {
            if (n % primes.get(i) == 0) {
                r.add(primes.get(i));
                n = n / primes.get(i);
                i = primes.size() - 1;
            }
        }
        Collections.reverse(r);
        String[] ret = new String[r.size()];
        for (int i = 0; i < r.size(); i++) {
            ret[i] = String.valueOf(r.get(i));
        }
        return String.join("*", ret);
    }


    List<Integer> listPrimes(int n) {
        List<Integer> r = new ArrayList<>();
        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        for (int i = 4; i < n; i++) {
            if (arr[i] == -1) {
                if (isPrime(i)) {
                    arr[i] = 1;
                } else {
                    arr[i] = 0;
                    for (int j = 2; i * j < n; j++) {
                        arr[i * j] = 0;
                    }
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (arr[i] == 1) {
                r.add(i);
            }
        }

        return r;
    }

    boolean isPrime(int n) {
        if (n == 0 || n == 1 || n == 2 || n == 3) {
            return true;
        }
        int half = n / 2;
        for (int i = 2; i <= half; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
