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

	@Test
	public void pet() {
		insert();
		orderlyFindAndDelete();
	}

	void insert() {
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into object_dn (dn) values (?)");
			int count = 100;

			System.out.println();
			System.out.println("insert " + count + " DNs...");
			long from = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				ps.setString(1, "PLMN-PLMN/MRBTS-" + i);
				ps.addBatch();
				// if (i % 100 == 0) {
				// ps.executeBatch();
				// ps.clearParameters();
				// }
			}
			int[] batchCount = ps.executeBatch();
			long to = System.currentTimeMillis();

			double dur = (to - from) / 1000.0;
			double speed = count / dur;
			System.out.format("used time: %.2fs insert speed: %.2f DN/s\r\n", dur, speed);

			assertEquals(count, batchCount.length);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void orderlyFindAndDelete() {
		try {
			int count = 100;

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
