package com.erdon.dao;

import com.erdon.entity.Book;
import com.erdon.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public int insertBook(Book book) throws SQLException;

    public List<Book> selectBooks() throws SQLException;

    public int updataBook(@Param("id") Integer id,@Param("book") Book book) throws SQLException;

    public int deletBook(Integer id) throws SQLException;

    public Book selectBook(Integer id) throws SQLException;

    public Integer pageCount() throws SQLException;

    public List<Book> itemsBook( Page page) throws SQLException;

    public Book existsBook(String bookName) throws SQLException;

    int pageCountByPrice(@Param("min") int min,@Param("max") int max) throws SQLException;

    List<Book> pageByPrice(@Param("min") int min,@Param("max") int max,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize) throws SQLException;
}
