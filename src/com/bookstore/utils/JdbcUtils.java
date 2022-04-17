package com.bookstore.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtils
 * Description:
 * date: 2022/1/22 15:57
 *
 * @author Ekertree
 * @since JDK 1.8
 */


public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conn = new ThreadLocal<Connection>();

    static {
        Properties properties = new Properties();
        //读取配置文件
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //从流中加载数据
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //创建数据库连接池
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *获取数据库连接池的链接
     * 返回null说明获取连接失败
     */
    public static Connection getConnection(){
        Connection connection = conn.get();
        if(connection==null){
            try {
                connection = dataSource.getConnection();//从数据库连接池获取连接
                conn.set(connection);//保存到threadlocal对象中，供后面的jdbc操作使用
                connection.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  connection;
    }

    public static void rollBackAndClose() {
        Connection connection = conn.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void commitAndClose() {
        Connection connection = conn.get();
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove 操作，否则就会出错。（因为Tomcat 服务器底层使用了线程池技术）
        conn.remove();
    }
}
