package com.erdon.service.impl;

import com.erdon.dao.BookDao;
import com.erdon.entity.Book;
import com.erdon.entity.Page;
import com.erdon.service.BookService;
import com.erdon.utils.MbUtils;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public int addBook(Book book) {
       //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        int i = 0;
        try {
            i = bookDao.insertBook(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Book> queryBooks() {
       // bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        List<Book> books = null;
        try {
            books = bookDao.selectBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public int removeBook(Integer id) {
      //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        int i = 0;
        try {
            i = bookDao.deletBook(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public int modifyBook(Integer id, Book book) {
      //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        int i = 0;
        try {
            i = bookDao.updataBook(id, book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Book queryBook(Integer id) {
      //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        Book book = null;
        try {
            book = bookDao.selectBook(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return book;
    }

    @Override
    public Page pageAll(Integer pageNo) {
      //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        Page page = new Page();
        try {

            Integer count = bookDao.pageCount();
            Integer total = count / page.getPageSize();
            if (count / page.getPageSize() > 0) {
                total++;
            }
            if (pageNo > total) {
                pageNo = total;
            } else if (pageNo < 1) {
                pageNo = 1;
            }
            page.setPageNo(pageNo);
            page.setPageTotal(total);
            page.setPageCount(count);
            List<Book> books = bookDao.itemsBook(page);
            page.setItems(books);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return page;
    }

    @Override
    public Boolean existsBook(String bookName) {
     //   bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        Book book = null;
        try {
            book = bookDao.existsBook(bookName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (book == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page pageByPrice(int pageNo, int min, int max) {
      //  bookDao = MbUtils.getSqlSession().getMapper(BookDao.class);
        Page page = new Page();
        int pageCount = 0;
        try {
            pageCount = bookDao.pageCountByPrice( min, max);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            page.setItems(bookDao.pageByPrice( min, max, pageNo, page.getPageSize()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Integer total = pageCount / page.getPageSize();
        if (pageCount % page.getPageSize() > 0) {
            total++;
        }
        page.setPageNo(pageNo);
        page.setPageTotal(total);
        page.setPageCount(pageCount);
        return page;
    }
}
