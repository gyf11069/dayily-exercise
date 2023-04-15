package com.gyunf.dao;

import com.gyunf.domain.EmPloyees;
import com.gyunf.utils.jdbcUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 20:01
 */
public class AddEmpDao {

    private jdbcUtils util = new jdbcUtils();
    public int add(EmPloyees employees) {
        String sql ="insert into employees(e_name,e_gender,e_education,e_hoby)" +
                " values(?,?,?,?)";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1, employees.getE_name());
            ps.setString(2, employees.getE_gender());
            ps.setString(3, employees.getE_education());
            ps.setString(4, employees.getE_hoby());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }



}
