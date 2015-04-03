/*
 * @(#)	2015年4月3日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class CountingTask extends TimerTask {
	String name;

	public CountingTask(String name) {
		super();
		this.name = name;
	}

	int count = 0;

	@Override
	public void run() {
		System.out.println(name + " count: " + count);
		try {
			if (count++ > 3) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
		}
	}
}

class CountingRunnable implements Runnable {
	String name;

	public CountingRunnable(String name) {
		this.name = name;
	}

	int count = 0;

	@Override
	public void run() {
		System.out.println(name + " count: " + count);
		try {
			if (count++ > 3) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
		}
	}
}

/**
 * 
 * @author wutalk
 */
public class TimerVsExecutorService {

	public static void main(String[] args) {

		Timer timer = new Timer(false);
		timer.scheduleAtFixedRate(new CountingTask("timer"), 500, 1000);

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
//		exec.scheduleAtFixedRate(new CountingRunnable("exec"), 500, 1000, TimeUnit.MILLISECONDS);

		System.out.println("waiting...");
		// try {
		// TimeUnit.SECONDS.sleep(10);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
