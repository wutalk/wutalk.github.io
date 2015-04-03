/*
 * @(#)	2014-12-4
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author wutalk
 */
public class FortyHoursSum {

	public static void main(String[] args) throws IOException {
		String fn = "D:/var/tmp/40h.txt";
		BufferedReader br = new BufferedReader(new FileReader(fn));
		String line = "";
		int totalMinutes = 0;
		while ((line = br.readLine()) != null) {
			String[] times = line.split(":");
			int hours = Integer.parseInt(times[0]);
			int minutes = Integer.parseInt(times[1]);
			totalMinutes += hours * 60 + minutes;
		}
		System.out.println("total hours: " + totalMinutes / 60.0);
	}

}
