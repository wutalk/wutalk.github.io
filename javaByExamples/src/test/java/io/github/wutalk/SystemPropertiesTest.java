/*
 * @(#)	2014-7-22
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

class Car {
	private Tier tier;

	public Car(Tier tier) {
		this.tier = tier;
	}

	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

}

class Tier {
	int size;

	public Tier(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

/**
 * 
 * @author wutalk
 */
public class SystemPropertiesTest {
	@Test
	public void testSet() throws Exception {
		String password = "123";
		String key = "io.github.wutalk.password";
		String prePassword = System.setProperty(key, password);
		assertNull(prePassword);
		assertEquals(password, System.getProperty(key));

		String pwd = "456";
		prePassword = System.setProperty(key, pwd);
		assertEquals(password, prePassword);
		assertEquals(pwd, System.getProperty(key));

		Map<Class<?>, Object> comp = new HashMap<Class<?>, Object>();
		Car car = new Car(new Tier(10));
		comp.put(Car.class, car);

		Car c1 = (Car) comp.get(Car.class);
		c1.getTier().setSize(5);
		Car c2 = (Car) comp.get(Car.class);
		assertTrue(c1 == c2);
		assertEquals(5, c2.getTier().getSize());
	}
}
