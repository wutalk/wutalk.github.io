/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.reflect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * 
 * @author wutalk
 */
class ConnectionManager {
	private int index;

	public ConnectionManager(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "ConnectionManager#" + index;
	}
}

class Employee {
	private static ConnectionManager cm = new ConnectionManager(0);

	public static ConnectionManager getCm() {
		return cm;
	}
}

public class SetPrivateFieldTest {
	@Test
	public void testResetPrivateField() throws Exception {
		assertNotNull(Employee.getCm());

		Field field = Employee.class.getDeclaredField("cm");
		field.setAccessible(true);
		field.set(null, null);
		assertNull(Employee.getCm());
	}
}
