/*
 * @(#)	Apr 19, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author wutalk
 */
public class DateCounter {

	public static void main(String[] args) {
		String from = "2013-5-1";
		String to = "2015-04-19";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fd = df.parse(from);
			Date td = df.parse(to);
			long span = td.getTime() - fd.getTime();
			long days = span/(1000*60*60*24);
			
			System.out.println("from " + df.format(fd) + " to " + df.format(td) + ", days: " + days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
