package com.gyunf.servlet;

import com.gyunf.domain.EmPloyees;
import com.gyunf.service.AddEmpService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 19:17
 */
public class addEmpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("addEmp的GET请求");
        int result = 0;
        PrintWriter out = null;

        String e_name = req.getParameter("e_name");
        String e_gender = req.getParameter("e_gender");
        String e_education = req.getParameter("e_education");
        String e_hoby = req.getParameter("e_hoby");

        EmPloyees employees = new EmPloyees();
        employees.setE_name(e_name);
        employees.setE_gender(e_gender);
        employees.setE_education(e_education);
        employees.setE_hoby(e_hoby);

        AddEmpService addempservice = new AddEmpService();
        result = addempservice.add(employees);
        //调用响应对象将处理结果以二进制形式写入到响应体
        resp.setContentType("text/html;charset=utf-8");
        out = resp.getWriter();
        if(result ==1){
            out.print("<font style='color:green;font-size:40'>添加成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>添加失败</font>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //super.doPost(req, resp);
//        System.out.println("HelloServlet02的POST请求");
    }
}
