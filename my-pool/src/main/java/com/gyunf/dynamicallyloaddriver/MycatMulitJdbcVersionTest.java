package com.gyunf.dynamicallyloaddriver;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author gyf
 * @date 2023-07-05 13:53
 *
 * 动态切换不同版本的MySQL数据库驱动
 *
 */
public class MycatMulitJdbcVersionTest {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/myblog?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Map<String, String> jdbcVersionMap = new HashMap<String, String>();
    private static final Map<String, Driver> tmpDriverMap = new HashMap<String, Driver>();

    // 动态加载jdbc驱动
    private static void dynamicLoadJdbc(String mysqlJdbcFile) throws Exception {
        File jarPath = new File("E:\\IdeaProjects\\train\\my-pool\\src\\main\\resources\\jdbcdriver\\" + mysqlJdbcFile);
        URL u = jarPath.toURI().toURL();
        String classname = jdbcVersionMap.get(mysqlJdbcFile);
        URLClassLoader ucl = new URLClassLoader(new URL[]{u},Thread.currentThread().getContextClassLoader());
        //ucl.loadClass(classname).newInstance();
        Driver d = (Driver) ucl.loadClass(classname).newInstance();
        DriverShim driver = new DriverShim(d);
        DriverManager.registerDriver(driver);
        tmpDriverMap.put(mysqlJdbcFile, driver);
    }

    // 每一次测试完卸载对应版本的jdbc驱动
    private static void dynamicUnLoadJdbc(String mysqlJdbcFile) throws SQLException {
        DriverManager.deregisterDriver(tmpDriverMap.get(mysqlJdbcFile));
    }

    // 进行一次测试
    private static void testOneVersion(String mysqlJdbcFile) {

        System.out.println("start test mysql jdbc version : " + mysqlJdbcFile);

        try {
            dynamicLoadJdbc(mysqlJdbcFile);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println(conn.getMetaData().getDriverVersion());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            dynamicUnLoadJdbc(mysqlJdbcFile);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("end !!!");
        System.out.println();
    }

    public static void main(String[] args) {

        jdbcVersionMap.put("mysql-connector-java-5.1.38.jar", "com.mysql.jdbc.Driver");
        jdbcVersionMap.put("mysql-connector-java-8.0.28.jar", "com.mysql.cj.jdbc.Driver");

        for (String mysqlJdbcFile : jdbcVersionMap.keySet()) {
            testOneVersion(mysqlJdbcFile);
        }

    }


}

class DriverShim implements Driver {
    private Driver driver;

    DriverShim(Driver d) {
        this.driver = d;
    }

    public boolean acceptsURL(String u) throws SQLException {
        return this.driver.acceptsURL(u);
    }

    public Connection connect(String u, Properties p) throws SQLException {
        return this.driver.connect(u, p);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return this.driver.getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.driver.getParentLogger();
    }
}
