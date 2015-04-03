/*
 * @(#)	2015-1-7
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author wutalk
 */
public class RandomSelection {

	private static List<String> index_pool;
	private static int selectSize = 5;
	private static String indexFilePath;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java -jar RandomSelection.jar selectSize indexFilePath");
			System.exit(0);
		} else {
			selectSize = Integer.parseInt(args[0]);
			indexFilePath = args[1];
		}

		index_pool = initIndexPool(indexFilePath);
		System.out.println(index_pool.size() + " indexes initialized from " + indexFilePath);

		// String seed = genSeed();
		// System.out.println("seed: " + seed);

		Random r = new Random();
		// Random r = new Random(Long.parseLong(seed));

		System.out.println(selectSize + " selection: ");
		for (int i = 0; i < selectSize; i++) {
			int nextInt = r.nextInt(index_pool.size());
			System.out.println(index_pool.get(nextInt));
		}
	}

	private static String genSeed() {
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String seed = df.format(date);
		return seed;
	}

	/**
	 * @param index_300
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static List<String> initIndexPool(String indexPath) throws FileNotFoundException,
			IOException {
		List<String> pool = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(indexPath));
		String tmp = "";
		while ((tmp = br.readLine()) != null) {
			pool.add(tmp.trim());
		}
		br.close();
		return pool;
	}

}
