/*
 * @(#)	2014-8-8
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author wutalk
 */
public class InetAddressReader {
	public static void main(String[] args) throws Exception {
		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("IP of my system is := " + IP.getHostAddress());
		
	}
}
