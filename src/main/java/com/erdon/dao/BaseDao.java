package com.erdon.dao;

import com.erdon.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();
    //delete,update,insert
    public int update(String sql,Object...args){
//        Connection conn = JdbcUtils.getConnection();
//        try {
//            return queryRunner.update(conn,sql,args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException(throwables);
//        }
        return 0;
    }
    public <T>List<T> queryForList(Class<T> type, String sql, Object...args){
//        Connection conn = JdbcUtils.getConnection();
//        try {
//            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException(throwables);
//        }
        return null;
    }
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
//        Connection conn = JdbcUtils.getConnection();
//        try {
//            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException(throwables);
//        }
        return null;
    }
    public Object queryForSingleValue(String sql,Object...args){
////        Connection conn = JdbcUtils.getConnection();
//        try {
////            return queryRunner.query(conn,sql,new ScalarHandler(),args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException(throwables);
//        }
        return null;
    }
}
