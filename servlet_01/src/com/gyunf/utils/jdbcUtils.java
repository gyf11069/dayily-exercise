package com.gyunf.utils;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 20:04
 */
public class jdbcUtils {

    private Connection con = null;//类文件属性，可以在类文件中所有的方法中使用
    private PreparedStatement ps=null;//类文件属性，可以在类文件中所有的方法中使用

    //静态语句块 static{}
    //在当前类文件第一次被加载到JVM时，JVM将会自动调用当前类文件静态语句块
    static{
        //1.注册数据库服务器提供的Driver接口实现类

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Driver接口实现类被注册了");
    }

    //封装Connection对象创建细节 不需要考虑使用对象创建细节
    public  Connection  createCon(){
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection对象创建失败。。。。。");
        }
        return con;
    }

    //封装PreparedStatement对象创建细节
    public PreparedStatement createStatement(String sql){

        Connection con = createCon();
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    //封装PreparedStatement对象与Connection对象销毁细节
    public void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //封装PreparedStatement对象与Connection对象与ResultSet对象销毁细节
    public void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();

    }
}

