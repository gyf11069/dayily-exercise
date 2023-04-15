package com.gyunf.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-04-28 20:29
 */
public abstract class MyAbstractDataSource implements MyDataSourceInterface {

    /*获取数据库连接信息*/
    private String url;
    private String driver;
    private String user;  //用户名
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(user,password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return doGetConnection(username,password);
    }

    /**
     * 获取数据库连接
     * @param username
     * @param password
     * @return
     */
    private Connection doGetConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
