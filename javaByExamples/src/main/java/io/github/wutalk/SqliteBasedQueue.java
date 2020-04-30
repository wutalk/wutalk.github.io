/*
 * @(#)	May 4, 2015
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author wutalk
 */
public class SqliteBasedQueue implements BigQueue {

    private static Logger LOG = LoggerFactory.getLogger(SqliteBasedQueue.class);
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

    public SqliteBasedQueue(String file) throws IOException {
        dbFile = new File(file);
        if (dbFile.exists()) {
            throw new IOException("file " + file + " already exist");
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + file);
            statement = connection.createStatement();
            statement.executeUpdate("drop table if exists object_dn");
            statement
                    .executeUpdate("create table object_dn (seq INTEGER PRIMARY KEY AUTOINCREMENT, dn string)");
        } catch (SQLException e) {
            throw new IOException("init database fails", e);
        }
    }

    @Override
    public boolean offer(String dn) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("insert into object_dn (dn) values (?)");
            ps.setString(1, dn);
            int update = ps.executeUpdate();
            if (update == 1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAll(List<String> dnList) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("insert into object_dn (dn) values (?)");
            int size = dnList.size();
            for (int i = 0; i < size; i++) {
                ps.setString(1, dnList.get(i));
                ps.addBatch();
                // if (i % 100 == 0) {
                // ps.executeBatch();
                // ps.clearParameters();
                // }
            }
            int[] batchCount = ps.executeBatch();
            if (batchCount.length == size) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String poll() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select seq, dn from object_dn");
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public int drainTo(Collection<String> c, int maxElements) {
        return -1;
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#remove()
     */
    @Override
    public String remove() throws NoSuchElementException {
        return "";
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#peek()
     */
    @Override
    public String peek() {
        return "";
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#size()
     */
    @Override
    public int size() {
        return -1;
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#clear()
     */
    @Override
    public void clear() {
        //
    }

    /*
     * (non-Javadoc)
     *
     * @see io.github.wutalk.BigQueue#release()
     */
    @Override
    public void release() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (dbFile != null && dbFile.exists()) {
            if (!dbFile.delete()) {
                LOG.error("fail to delete file " + dbFile.getPath());
            }
        }
    }

}
