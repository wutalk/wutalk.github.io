/*
 * @(#)	2015年4月24日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * 
 * @author wutalk
 */
public class FileMapping {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("/tmp/example.dat", "rw");
			FileChannel channel = raf.getChannel();
			MappedByteBuffer buf = channel.map(MapMode.READ_WRITE, 8L, 16);
			
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
