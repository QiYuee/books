package com.erdon.controller;

import com.erdon.entity.Book;
import com.erdon.entity.Page;
import com.erdon.entity.User;
import com.erdon.service.BookService;
import com.erdon.service.impl.BookServiceImpl;
import com.erdon.utils.WebUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class BookController extends BaseController{
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private BookService bookService = (BookService) ac.getBean("bookService");
    public void queryBooks(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        if (req.getParameter("pageNo")!=null) {
             pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        }
        Page page = bookService.pageAll(pageNo);
        page.setUrl("manager/bookController?action=queryBooks");
        req.setAttribute("Page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        Boolean aBoolean = bookService.existsBook(book.getName());
        if (!aBoolean){
            int i = bookService.addBook(book);
        }
        int pageLast = WebUtils.parseInt(request.getParameter("pageLast"), 1);
        response.sendRedirect(request.getContextPath()+"/manager/bookController?action=queryBooks&pageNo="+pageLast);
    }
    public void removeBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int i = bookService.removeBook(id);
        resp.sendRedirect(req.getContextPath()+"/manager/bookController?action=queryBooks&pageNo="+req.getParameter("pageNo"));
    }
    public void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBook(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?method=modifyBook&pageNo="+req.getParameter("pageNo")).forward(req,resp);
    }
    public void modifyBook(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int i = bookService.modifyBook(id,book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookController?action=queryBooks&pageNo="+req.getParameter("pageNo"));
    }
}
