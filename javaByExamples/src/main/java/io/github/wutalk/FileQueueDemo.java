/*
 * @(#)	Apr 26, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.File;
import java.io.IOException;

import com.squareup.tape.FileObjectQueue;
import com.squareup.tape.QueueFile;

/**
 * 
 * @author wutalk
 */
public class FileQueueDemo {

	public static void main(String[] args) {
		File f = new File("./queue-file.txt");
		try {
			QueueFile qf = new QueueFile(f);
			qf.add("hello world".getBytes());
			qf.add("hello queue".getBytes());
			qf.add("hello file".getBytes());

			System.out.println(new String(qf.peek()));
			qf.remove();
			System.out.println(new String(qf.peek()));
			qf.remove();
			System.out.println(new String(qf.peek()));
			qf.remove();
			System.out.println(new String(qf.peek()));
			qf.clear();
			qf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
