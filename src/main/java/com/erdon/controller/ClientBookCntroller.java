package com.erdon.controller;

import com.erdon.entity.Page;
import com.erdon.service.BookService;
import com.erdon.service.impl.BookServiceImpl;
import com.erdon.utils.WebUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookCntroller extends BaseController{
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private BookService bookService = (BookService) ac.getBean("bookService");
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        if (req.getParameter("pageNo")!=null) {
            pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        }
        Page page = bookService.pageAll(pageNo);
        page.setUrl("client/bookController?action=page");
        req.setAttribute("Page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        if (req.getParameter("pageNo")!=null) {
            pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        }
        int min =0;
        StringBuilder sb = new StringBuilder("client/bookController?action=pageByPrice");
        if (req.getParameter("min")!=null&&req.getParameter("min")!=""){
             min = WebUtils.parseInt(req.getParameter("min"), 0);
            sb.append("&min=").append(min);
        }
        if (req.getParameter("max")!=null&&req.getParameter("max")!=""){
            int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
            Page page = bookService.pageByPrice(pageNo,min,max);
            sb.append("&max=").append(max);
            page.setUrl(sb.toString());
            req.setAttribute("Page",page);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath()+"/client/bookController?action=page");
        }

    }
}
