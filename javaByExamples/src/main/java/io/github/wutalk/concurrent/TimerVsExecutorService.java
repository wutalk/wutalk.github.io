/*
 * @(#)	2015年4月3日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

class CountingTask extends TimerTask {

	private static final Logger LOGGER = Logger.getLogger(CountingTask.class);
	String name;

	public CountingTask(String name) {
		super();
		this.name = name;
	}

	int count = 0;

	@Override
	public void run() {
		LOGGER.info(name + " count: " + count);
		// if don't catch, no error message on log4j
		try {
			if (count++ > 3) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			LOGGER.error("error: " + e);
		}
	}
}

class CountingRunnable implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(CountingRunnable.class);
	String name;

	public CountingRunnable(String name) {
		this.name = name;
	}

	int count = 0;

	@Override
	public void run() {
		LOGGER.info(name + " count: " + count);
		try {
			if (count++ > 3) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			LOGGER.error("error: " + e);
		}
	}
}

/**
 * 
 * @author wutalk
 */
public class TimerVsExecutorService {

	public static void main(String[] args) {

		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new CountingTask("timer"), 500, 1000);

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
		exec.scheduleAtFixedRate(new CountingRunnable("exec"), 500, 1000, TimeUnit.MILLISECONDS);

		System.out.println("waiting...");

	}
}
