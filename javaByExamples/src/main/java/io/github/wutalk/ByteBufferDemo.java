/*
 * @(#)	Apr 25, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * 
 * @author wutalk
 */
public class ByteBufferDemo {

	// 1k, normally 4k will better map to OS page
	private static final int BSIZE = 1024;

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		bb.putInt(1);
		bb.putInt(200);
		bb.putInt(3);
		bb.putInt(4);

		bb.flip();
		while (bb.hasRemaining()) {
			System.out.print(bb.getInt());
			// System.out.print(' ');
		}
		System.out.println();

		bb.putInt(2, 100);
		bb.flip();
		System.out.print(bb.getInt(2));
		RandomAccessFile raf = null;
		try {
			int mapSize = 10 * 1024 * 1024;// 10M
			raf = new RandomAccessFile("F:/devlab/bigqueue/testdata/mapped_file.dat", "rw");
			FileChannel channel = raf.getChannel();
			MappedByteBuffer mbb = channel.map(MapMode.READ_WRITE, 0, mapSize);
			mbb.put((byte) 05);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
