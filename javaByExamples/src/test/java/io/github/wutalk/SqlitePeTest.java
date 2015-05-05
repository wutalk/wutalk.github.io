/*
 * @(#)	May 4, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author wutalk
 */
public class SqlitePeTest {

	private Connection connection;
	private Statement statement;
	private File dbFile;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		try {
			String tempDir = System.getenv("TEMP");
			if (!tempDir.endsWith(File.separator)) {
				tempDir += File.separator;
			}
			String file = tempDir + "test_sqlite_data_" + System.currentTimeMillis() + ".db";
			dbFile = new File(file);
			if (dbFile.exists()) {
				dbFile.delete();
			}
			connection = DriverManager.getConnection("jdbc:sqlite:" + file);
			System.out.println("connected to db: " + file);
			statement = connection.createStatement();
			statement.executeUpdate("drop table if exists object_dn");
			statement
					.executeUpdate("create table object_dn (seq INTEGER PRIMARY KEY AUTOINCREMENT, dn string)");
		} catch (SQLException e) {
			throw new IOException("init database fails", e);
		}
		System.out.println("setUp done");
	}

	@After
	public void tearDown() throws Exception {
		statement.close();
		connection.close();
		if (dbFile.exists()) {
			if (!dbFile.delete()) {
				System.err.println("fail to delete " + dbFile.getPath());
			}
			System.out.println("deleted file " + dbFile.getPath());
		}
		System.out.println("tearDown done");
	}

	int count = 100;

	/**
	 * <pre>
	 * size: sqlite-jdbc-3.8.7.jar	3,959,324 bytes
	 * size: bigqueue-0.7.1.jar		   40,302 bytes
	 * 
	 * on workPC
	 * insert 10000 DNs...
	 * used time: 86.01s insert speed: 116.27 DN/s
	 * 
	 * poll(find and delete) 10000 DNs...
	 * used time: 91.46s poll speed: 109.33 DN/s
	 * 
	 * 
	 * insert 100000 DNs...
	 * used time: 0.35s insert speed: 285714.29 DN/s 2400X
	 * 
	 * poll 100000 DNs...
	 * used time: 0.20s insert speed: 497512.44 DN/s 4500X
	 * 
	 * 
	 * JDK in-memory queue
	 * 
	 * insert 100000 DNs...
	 * used time: 0.06s insert speed: 1818181.82 DN/s 6X
	 * 
	 * poll 100000 DNs...
	 * used time: 0.02s insert speed: 5555555.56 DN/s 11X
	 * </pre>
	 */
	@Test
	public void pet() {
		insert();
		orderlyFindAndDelete();

		setmmap_size();

		insert();
		orderlyFindAndDelete();
	}

	void setmmap_size() {
		ResultSet rs;
		try {
			rs = statement.executeQuery("PRAGMA mmap_size");

			System.out.println("memory-mapped mmap_size: " + rs.getInt(1) + " bytes");

			// change PRAGMA setting
			int mmap_size = 1 * 1024 * 1024; // 1m
			rs = statement.executeQuery("PRAGMA mmap_size=" + mmap_size);
			System.out.println("mmap_size set to " + mmap_size + " bytes");

			rs = statement.executeQuery("PRAGMA mmap_size");
			System.out.println("memory-mapped mmap_size: " + rs.getInt(1) + " bytes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void insert() {
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into object_dn (dn) values (?)");

			System.out.println();
			System.out.println("insert " + count + " DNs...");
			int inserted = 0;
			long from = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				ps.setString(1, "PLMN-PLMN/MRBTS-" + i);
				ps.addBatch();
				if (i % 100 == 0) {
					int[] batchCount = ps.executeBatch();
					inserted += batchCount.length;
					ps.clearParameters();
				}
			}
			int[] batchCount = ps.executeBatch();
			long to = System.currentTimeMillis();
			inserted += batchCount.length;

			double dur = (to - from) / 1000.0;
			double speed = count / dur;
			System.out.format("used time: %.2fs insert speed: %.2f DN/s\r\n", dur, speed);

			assertEquals(count, inserted);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void orderlyFindAndDelete() {
		try {

			System.out.println();
			System.out.println("poll(find and delete) " + count + " DNs...");
			long from = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				ResultSet rs = statement.executeQuery("select seq, dn from object_dn");
				int seq = -1;
				if (rs.next()) {
					rs.getString(2);
					seq = rs.getInt(1);
				}
				PreparedStatement ps = connection
						.prepareStatement("DELETE FROM object_dn where seq = ?");
				ps.setInt(1, seq);
				ps.executeUpdate();
			}
			long to = System.currentTimeMillis();

			double dur = (to - from) / 1000.0;
			double speed = count / dur;
			System.out.format("used time: %.2fs poll speed: %.2f DN/s\r\n", dur, speed);

			ResultSet rs = statement.executeQuery("select count(*) from object_dn");
			assertEquals(0, rs.getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
