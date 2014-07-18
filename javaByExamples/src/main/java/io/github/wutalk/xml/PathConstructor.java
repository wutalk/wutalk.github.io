/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.xml;

/**
 * 
 * @author wutalk
 */
public class PathConstructor {
	public static String path(String relativeFileName) {
		return PathConstructor.class.getClassLoader().getResource(relativeFileName).getPath();
	}
}
