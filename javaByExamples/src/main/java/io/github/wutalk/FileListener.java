/*
 * @(#)	2015-2-6
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author wutalk
 */
public class FileListener {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar FileListener.jar /path/to/listen");
			System.exit(0);
		}
		String path = args[0];
		File f = new File(path);
		if (f.isFile() || f.isDirectory()) {
			(new Thread(new Monitor(f))).start();
		} else {
			System.out.println(f.getName() + " is not applicable");
		}
	}
}

class Monitor implements Runnable {
	File f;
	long lastModified = 0L;

	public Monitor(File f) {
		this.f = f;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("start checking...");
			long modified = f.lastModified();
			if (modified != lastModified) {
				System.out.println(f.getName() + " is changed");
				lastModified = modified;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}