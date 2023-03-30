package com.gyunf.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-08-17 11:26
 */
public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 专门用来处理请求和响应
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("访问了service方法");
        //将ServletRequest类型转换为子类型HttpServletRequest调用getMethod()方法
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        String method = httpServletRequest.getMethod();
        //根据不同的访问方法分发处理请求
        if("POST".equals(method)){
            doPOST();
        }else if("GET".equals(method)){
            doGET();
        }


    }

    public void doPOST(){
        System.out.println("POST请求");
    }

    public void doGET(){
        System.out.println("GET请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
