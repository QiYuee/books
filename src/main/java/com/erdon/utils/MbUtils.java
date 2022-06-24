package com.erdon.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.InputStream;

public class MbUtils extends WebApplicationContextUtils {
//    private static SqlSessionFactory factory = null;
//    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();
//
//    static {
//        String config = "mybatis.xml";
//        try {
//            InputStream in = Resources.getResourceAsStream(config);
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            factory = builder.build(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static SqlSession getSqlSession() {
//
//        SqlSession sqlSession = local.get();
//        if (sqlSession == null) {
//            sqlSession = factory.openSession();
//            local.set(sqlSession);
//            return sqlSession;
//        }
//        return sqlSession;
//    }
//
//    public static void commit() {
//        if (local.get() != null) {
//            try {
//                local.get().commit();
//            } catch (Exception throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
//
//    public static void rollback() {
//        if (local.get() != null) {
//            try {
//                local.get().rollback();
//            } catch (Exception throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
//
//    public static void close() {
//        if (local.get() != null) {
//            local.get().close();
//            local.remove();
//        }
//    }

}
