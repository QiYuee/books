package com.erdon.controller2;

import com.erdon.entity.Page;
import com.erdon.service.BookService;
import com.erdon.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/client")
public class ClientBookCntroller2 {
    //    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/page")
    public ModelAndView page(HttpServletRequest req, Integer pageNo) {
//        WebApplicationContext wac = (WebApplicationContext) new ClassPathXmlApplicationContext("applicationContext.xml");
//        bookService = (BookService) wac.getBean("bookService");
        ModelAndView mv = new ModelAndView();
        if (pageNo == null) {
            pageNo = 1;
        }
        Page page = bookService.pageAll(pageNo);
        page.setUrl("client/page");
//        req.setAttribute("Page", page);
        mv.addObject("Page",page);
        mv.setViewName("/pages/client/index.jsp");
//        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        return mv;
    }

    @RequestMapping("/pageByPrice")
    public ModelAndView pageByPrice(HttpServletRequest req, Integer pageNo) {
        ModelAndView mv = new ModelAndView();
        if (pageNo == null) {
            pageNo = 1;
        }
        int min = 0;
        StringBuilder sb = new StringBuilder("client/pageByPrice");
        if (req.getParameter("min") != null && req.getParameter("min") != "") {
            min = WebUtils.parseInt(req.getParameter("min"), 0);
            sb.append("?min=").append(min);
        }
        if (req.getParameter("max") != null && req.getParameter("max") != "") {
            int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
            Page page = bookService.pageByPrice(pageNo, min, max);
            sb.append("&max=").append(max);
            page.setUrl(sb.toString());
            mv.addObject("Page",page);
            mv.setViewName("/pages/client/index.jsp");
//            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } else {
            mv.setViewName("redirect:/client/page");
//            resp.sendRedirect(req.getContextPath()+"/client/bookController?action=page");
        }
        return mv;
    }
}
