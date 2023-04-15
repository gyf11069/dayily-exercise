package com.gyunf.pool;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-04-28 21:23
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接池
 */
public class MyDataSource extends MyAbstractDataSource {
    /**
     * 空闲连接池
     */
    private final List<ConnectionProxy> idleConnections = new ArrayList<ConnectionProxy>();
    /**
     * 空闲连接池
     */
    private final List<ConnectionProxy> activeConnections = new ArrayList<ConnectionProxy>();
    /**
     * 最大的正在使用的连接数
     */
    private int poolMaxActiveConnections = 10;
    /**
     * 最大空闲的连接数
     */
    private int poolMaxIdleConnections = 5;
    /**
     * 从连接池中获取一个连接最大等待多长时间,单位亳秒
     */
    private int poolTimeToWait = 30000;

    /**
     * 监视器对象
     */
    private final Object monitor = new Object();
    /**
     * 监视器对象
     */
    private final Object watch = new Object();

    /**
     * 覆盖父类的getConnection方法,返回一个动态代理的连接对象
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {

        ConnectionProxy connectionProxy = getConnectionProxy(super.getUser(),super.getPassword());
        //返回代理连接的Connection对象
        return connectionProxy.getProxyConnection();
    }

    /**
     * 获取连接
     * @param username
     * @param password
     * @return
     */
    public ConnectionProxy getConnectionProxy(String username,String password) throws SQLException {

        boolean wait = false;

        ConnectionProxy connectionProxy = null;

        //刚开始没有连接
        while(connectionProxy == null){
            //做一个同步线程（执行完该线程中的任务后才处理其他事务）
            synchronized(monitor){

                if(!idleConnections.isEmpty()){
                    //如果空闲连接池不为空， 那么就可以直接获取到连接(将空闲连接池中的连接对象删除一个)
                    connectionProxy = idleConnections.remove(0);
                }else{
                    //没有空闲连接使用，则创建新的连接
                    if(activeConnections.size() < poolMaxActiveConnections){
                        //如果当前激活的连接数小于我们允许的最大连接数，那么此时可以创建一个新的连接，否则还不能创建
                        connectionProxy = new ConnectionProxy(super.getConnection(),this);
                    }
                    //否则是不能创建新连接的，需要等待 poolTimeTowait = 30000亳秒

                }
            }

            if(!wait){
                //修改wait变量的值为true
                wait = true;
            }

            if(connectionProxy == null){
                try {
                    //连接对象是空，需要等待
                    monitor.wait(poolTimeToWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //如果等待被线程打断，则退出while循环
                    break;
                }
            }
        }

        if(connectionProxy != null){
            //连接对象不为空就是拿到连接了，则将连接对象加入激活连接池中
            activeConnections.add(connectionProxy);
        }
        //返回连接对象
        return connectionProxy;
    }

    /**
     * 关闭连接(不是正真的关闭)，把连接放回连接池
     * @param connectionProxy
     */
    public void closeConnection(ConnectionProxy connectionProxy){
        synchronized(monitor){
            //关闭连接，那么就是把激活状态的连接变成空闲连接
            activeConnections.remove(connectionProxy);

            if(idleConnections.size() < poolMaxIdleConnections){
                idleConnections.add(connectionProxy);
            }

            //通知(唤醒)上面等待获取连接的线程,重新执行while循环
            monitor.notifyAll();
        }
    }
}
