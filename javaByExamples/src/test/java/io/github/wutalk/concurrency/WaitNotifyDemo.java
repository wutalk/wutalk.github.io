package io.github.wutalk.concurrency;

import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo {
    private static String message;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {

            int i = 1;
            System.out.println("thread1 started");
            synchronized (lock) {
                System.out.println("t1 got lock");
                while (message == null) {
                    try {
                        System.out.println("wait #" + i++);
                        lock.wait(); // after notify, need to relinquishes the lock,
                        // then continue after wait(),
                        // means no need to execute lines between got lock and wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(message);
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 started");
            synchronized (lock) {
                System.out.println("t2 got lock");
                message = "A message from thread1";
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
                System.out.println("thread2 end");
            }
        });

        thread1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

