package com.gyunf.service;

import com.gyunf.dao.AddEmpDao;
import com.gyunf.domain.EmPloyees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 19:49
 */
public class AddEmpService{

    static final String jdbcUrl = "jdbc:mysql://localhost/learnjdbc?useSSL=false&characterEncoding=utf8";
    static final String jdbcUsername = "learn";
    static final String jdbcPassword = "learnpassword";

    private AddEmpDao addEmpDao = new AddEmpDao();

    public int add(EmPloyees employees) {
        int result = addEmpDao.add(employees);
        return result;//返回给servlet
    }

    static void batchInsertStudents(List<String> names, boolean gender, int grade, int score) throws SQLException {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            boolean isAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false); // 关闭自动提交事务
            try (PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
                for (String name : names) {
                    ps.setString(1, name);
                    ps.setBoolean(2, gender);
                    ps.setInt(3, grade);
                    ps.setInt(4, score);
                    ps.addBatch(); // 添加到batch
                }
                int[] ns = ps.executeBatch(); // 执行batch
                for (int n : ns) {
                    System.out.println(n + " inserted."); // batch中每个SQL执行的结果数量
                }
            }
            conn.commit();
            conn.setAutoCommit(isAutoCommit); // 恢复AutoCommit设置
        }
    }
}
