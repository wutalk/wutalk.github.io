/*
 * @(#)	2015年5月26日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * @author wutalk
 */
public class DNSpecLoader {

	private static Properties PROPS;

	static {
		InputStream is = null;
		try {
			is = DNSpecLoader.class.getResourceAsStream("/io/github/wutalk/DNSpec.properties");

			PROPS = new Properties();
			PROPS.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("test");
		System.out.println(PROPS.getProperty("dn.maxLength"));
	}

	@Test
	public void testLoadProperties() throws Exception {
		String alpha_char_key = "alpha_char";
		String alpha_char = PROPS.getProperty(alpha_char_key);
		String alphanumeric_char_key = "alphanumeric_char";
		String alphanumeric_char = PROPS.getProperty(alphanumeric_char_key);
		String classAbbreviation = PROPS.getProperty("classAbbreviation");
		assertEquals("${alpha_char}(${alphanumeric_char})+", classAbbreviation);

		classAbbreviation = classAbbreviation.replace("${" + alphanumeric_char_key + "}",
				alphanumeric_char);
		assertEquals("${alpha_char}([a-zA-Z]|\\d|_)+", classAbbreviation);
		classAbbreviation = classAbbreviation.replace("${" + alpha_char_key + "}", alpha_char);
		assertEquals("[a-zA-Z]([a-zA-Z]|\\d|_)+", classAbbreviation);
	}

}
