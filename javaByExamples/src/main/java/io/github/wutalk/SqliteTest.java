/*
 * @(#)	May 3, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author wutalk
 */
public class SqliteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			// create a database connection
			String dbFile = "F:/temp/test_sqlite.db";
			File dbf = new File(dbFile);
			if (dbf.exists()) {
				if (!dbf.delete()) {
					System.err.println("failed to delete file: " + dbFile);
					return;
				}
			}
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			System.out.println("connected to db " + dbFile);
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists object_dn");
			statement
					.executeUpdate("create table object_dn (seq INTEGER PRIMARY KEY AUTOINCREMENT, dn string)");

			System.out.println("insert start");
			statement.executeUpdate("insert into object_dn (dn) values('PLMN-PLMN/MRBTS-1')");
			System.out.println("insert end");

			ResultSet rs = statement.executeQuery("select * from object_dn");
			while (rs.next()) {
				System.out
						.println(rs.getInt("seq") + ": get and delete dn = " + rs.getString("dn"));
			}
			rs.close();

			statement.executeUpdate("DELETE FROM object_dn");

			// count
			rs = statement.executeQuery("select count(*) from object_dn");
			System.out.println("count: " + rs.getInt(1));
			rs.close();

			// PRAGMA
			rs = statement.executeQuery("PRAGMA max_page_count");
			System.out.println("max_page_count: " + rs.getInt(1));
			rs = statement.executeQuery("PRAGMA page_size");
			System.out.println("page_size: " + rs.getInt(1));
			rs = statement.executeQuery("PRAGMA page_count");
			System.out.println("page_count: " + rs.getInt(1));
			rs = statement.executeQuery("PRAGMA mmap_size");
			System.out.println("memory-mapped mmap_size: " + rs.getInt(1));

			// change PRAGMA setting
			int mmap_size = 1024 * 1024; // 1m
			rs = statement.executeQuery("PRAGMA mmap_size=" + mmap_size);
			System.out.println("mmap_size set to " + mmap_size);

			rs = statement.executeQuery("PRAGMA mmap_size");
			System.out.println("memory-mapped mmap_size: " + rs.getInt(1));

			/**
			 * http://sqlite.org/limits.html Maximum Number Of Rows In A Table: 264
			 * (18446744073709551616 or about 1.8e+19) <br/>
			 * Maximum Database Size: 140 terabytes, or 128 tebibytes
			 */

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
			// System.err.println(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
	}

}