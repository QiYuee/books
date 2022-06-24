package com.erdon.controller2;

import com.erdon.controller.BaseController;
import com.erdon.entity.Book;
import com.erdon.entity.Page;
import com.erdon.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/manager")
public class BookController extends BaseController {
    //    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    private BookService bookService;

    @RequestMapping("/queryBooks")
    public ModelAndView queryBooks(Integer pageNo) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        if (pageNo == null) {
            pageNo = 1;
        }
        Page page = bookService.pageAll(pageNo);
        page.setUrl("manager/queryBooks");
        mv.addObject("Page", page);
        mv.setViewName("/pages/manager/book_manager.jsp");
        return mv;
    }

    @RequestMapping("/addBook")
    public String addBook(HttpServletRequest request, Book book, Integer pageLast) throws IOException {
//        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        Boolean aBoolean = bookService.existsBook(book.getName());
        if (!aBoolean) {
            int i = bookService.addBook(book);
        }
        if (pageLast == null) {
            pageLast = 1;
        }
        return "redirect:/manager/queryBooks?pageNo=" + pageLast;
    }

    @RequestMapping("/removeBook")
    public String removeBook(HttpServletRequest req, Integer pageNo, Integer id) throws ServletException, IOException {
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int i = bookService.removeBook(id);
        return "redirect:/manager/queryBooks?pageNo=" + pageNo;
    }

    @RequestMapping("/getBook")
    public ModelAndView getBook(Integer id, Integer pageNo) throws ServletException, IOException {
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        ModelAndView mv = new ModelAndView();
        Book book = bookService.queryBook(id);
        mv.addObject("book", book);
        mv.setViewName("/pages/manager/book_edit.jsp?method=modifyBook&pageNo=" + pageNo);
        return mv;
    }

    @RequestMapping("/modifyBook")
    public String modifyBook(HttpServletRequest req,Book book,Integer id,Integer pageNo) throws IOException {
//        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int i = bookService.modifyBook(id, book);
        return "redirect:/manager/queryBooks?pageNo=" + pageNo;
    }
}
