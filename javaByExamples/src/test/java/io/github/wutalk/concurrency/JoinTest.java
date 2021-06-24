package io.github.wutalk.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        o.wait(); // error
    }

    @Test
    public void testJoin() {
        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());
        t1.start();
        t2.start();

        System.out.println("all started");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main ending");
    }

    @Test
    public void testGC() {
        // -XX:+PrintGCDetails

    }
}

class Task1 implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("after 3 s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task2 implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("after 2 s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}