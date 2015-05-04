/*
 * @(#)	May 4, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @author wutalk
 */
public interface BigQueue {
	
	boolean offer(String dn);

	boolean addAll(List<String> dnList);

	String poll();

	int drainTo(Collection<String> c, int maxElements);

	String remove() throws NoSuchElementException;

	String peek();

	int size();

	boolean isEmpty();

	void clear();

	void release();
}