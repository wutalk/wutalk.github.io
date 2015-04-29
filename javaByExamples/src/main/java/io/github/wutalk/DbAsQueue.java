/*
 * @(#)	2015年4月29日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

/**
 * 10k DN == 280k file, 10M DN == 280M file
 * 
 * @author wutalk
 */
public class DbAsQueue {

	private Connection connection;
	private Statement statement;

	public DbAsQueue(String file) {
		
	}

	public boolean offer(String dn) {
		return false;
	}

	public String poll() {
		return "";
	}

	public String remove() throws NoSuchElementException {
		return "";
	}

	public String peek() {
		return "";
	}

	public int size() {
		return -1;
	}

	public boolean isEmpty() {
		return false;
	}

	public void clear() {
		//
	}

	public static void main(String[] args) throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// create a database connection
			String dbFile = "D:/wutalk/tmp/dn_sqlite.db";
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

			statement.executeUpdate("drop table if exists person");
			statement
					.executeUpdate("create table object_dn (seq INTEGER PRIMARY KEY AUTOINCREMENT, dn string)");

			int num = 10;
			System.out.println("start insert " + num + " dn...");
			// statement.executeUpdate("insert into object_dn values('PLMN-PLMN/MRBTS-1')");
			// statement.executeUpdate("insert into object_dn values('PLMN-PLMN/MRBTS-2')");
			long from = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				statement.executeUpdate("insert into object_dn (dn) values('PLMN-PLMN/MRBTS-" + i
						+ "')");
			}
			long to = System.currentTimeMillis();
			long duration = (to - from) / 1000;
			System.out.println("insert " + num + " dn in " + duration + " seconds");

			// delete from t where rowid < (select rowid from t limit 2,1);
			ResultSet rs = statement.executeQuery("select * from object_dn where seq in (1,3,5)");
			int count = 0;
			while (rs.next()) {
				// read the result set
				System.out
						.println(rs.getInt("seq") + ": get and delete dn = " + rs.getString("dn"));
				// rs.deleteRow();
				if (count++ > 5) {
					break;
				}
			}
			rs.close();

			statement.executeUpdate("DELETE FROM object_dn where seq in (1,3,5)");
			// DELETE FROM COMPANY WHERE ID = 7;

			rs = statement.executeQuery("select * from object_dn where seq == 2");
			System.out.println("remaining DN");
			count = 0;
			while (rs.next()) {
				// read the result set
				System.out.println(rs.getInt("seq") + ": dn = " + rs.getString("dn"));
				if (count++ > 5) {
					break;
				}
			}
			rs = statement.executeQuery("select count(*) from object_dn");
			System.out.println("count: " + rs.getInt(1));

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