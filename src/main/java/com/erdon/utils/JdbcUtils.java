package com.erdon.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class  JdbcUtils {
//    private static DruidDataSource dataSource;
//    private static ThreadLocal<Connection> connt = new ThreadLocal<Connection>();
//    static {
//        Properties properties = new Properties();
//        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbcConfig.properties");
//        try {
//            properties.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static Connection getConnection(){
//        Connection conn = connt.get();
//        if (conn==null){
//            try {
//                conn = dataSource.getConnection();
//                conn.setAutoCommit(false);
//                connt.set(conn);
//                return conn;
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return conn;
//    }
//
//    public static void commitAndClose(){
//        if (connt.get()!=null){
//            try {
//                connt.get().commit();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }finally {
//                try {
//                    connt.get().close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            connt.remove();
//        }
//    }
//    public static void rollbackAndClose(){
//        if (connt.get()!=null){
//            try {
//                connt.get().rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }finally {
//                try {
//                    connt.get().close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            connt.remove();
//        }
//    }
//

//    public static void closeConnection(Connection connection) {
//        try {
//            connection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        ;
//    }
}
