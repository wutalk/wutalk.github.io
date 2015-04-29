/*
 * @(#)	2015年4月24日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author wutalk
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int dnNum = 100 * 1000; // 100k
		FileWriter outer = null;
		try {
			outer = new FileWriter("D:/wutalk/tmp/100k_DN.txt");
			String dn = "PLMN-PLMN/MRBTS-600480/RET-x";
			for (int i = 0; i < dnNum; i++) {
				outer.write(dn);
				outer.write(i+";");
				if (i % 100 == 0) {
					outer.flush();
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("finish write " + dnNum + " DNs");
		}
	}
}
