package com.gyunf.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-04-28 19:55
 */
public interface MyDataSourceInterface extends DataSource {
    @Override
    default Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    default Connection getConnection(String username, String password) throws SQLException {
        return null;
    }


    //下面的方法可以不用实现
    @Override
    default PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    default void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    default void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    default int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    default <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    default Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
