package com.erdon.service;

import com.erdon.entity.Book;
import com.erdon.entity.Page;

import java.util.List;

public interface BookService {
    public int addBook(Book book);
    public List<Book> queryBooks();
    public int removeBook(Integer id);
    public int modifyBook(Integer id, Book book);
    public Book queryBook(Integer id);
    public Page pageAll(Integer pageNo);
    public Boolean existsBook(String bookName);

    Page pageByPrice(int pageNo,int min,int max);
}
