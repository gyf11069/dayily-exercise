package com.gyunf.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-04-28 20:45
 */
public class ConnectionProxy implements InvocationHandler {
    /**
     * 真实的连接对象
     */
    private Connection realConnection;
    /**
     * 代理的连接对象
     */
    private Connection proxyConnection;
    /**
     * 获取数据源对象
     */
    private MyDataSource myDataSource;

    /***
     * 构造方法，初始化对象
     * @param realConnection
     * @param myDataSource
     */
    public ConnectionProxy(Connection realConnection, MyDataSource myDataSource) {
        //初始化真实连接对象
        this.realConnection = realConnection;
        //初始化数据源
        this.myDataSource = myDataSource;
        //初始化代理对象
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader()
                                                        ,new Class<?>[] {Connection.class},this);
    }

    public Connection getRealConnection() {
        return realConnection;
    }

    public void setRealConnection(Connection realConnection) {
        this.realConnection = realConnection;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public void setProxyConnection(Connection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public MyDataSource getMyDataSource() {
        return myDataSource;
    }

    public void setMyDataSource(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    /**
     * 当调用Connection对象里面的方法时，首先会被该 invoke方法拦截
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取当前所调用的Connection对象的方法
        String methodName = method.getName();

        //要拦截的方法
        if(methodName.equalsIgnoreCase("close")){
            //把连接归还到连接池中(最后写)
            myDataSource.closeConnection(this);
            return null;
        }else{
            //不需要拦截的方法放行
            return method.invoke(realConnection,args);
        }
    }
}
