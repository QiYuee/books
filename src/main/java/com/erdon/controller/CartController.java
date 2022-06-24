package com.erdon.controller;

import com.erdon.entity.Book;
import com.erdon.entity.Cart;
import com.erdon.entity.CartItems;
import com.erdon.service.BookService;
import com.erdon.service.impl.BookServiceImpl;
import com.erdon.utils.WebUtils;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartController extends BaseController {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private BookService bookService = (BookService) ac.getBean("bookService");

    public void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBook(i);
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItems);
        req.getSession().setAttribute("cartLastName", cartItems.getgName());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void deletCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deletItem(i);
        }
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    public void closeCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.delectAllItem();
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    public void updateCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String countItem = req.getParameter("countItem");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int i = WebUtils.parseInt(id, 0);
        int count = WebUtils.parseInt(countItem, 1);
        if (cart != null) {
            if (count == 0) {
                cart.deletItem(i);
            } else {
                cart.updateItemNumber(i, count);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    public void ajaxAddCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBook(i);
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItems);
        req.getSession().setAttribute("cartLastName", cartItems.getgName());
//        resp.sendRedirect(req.getHeader("Referer"));
        Map<String, Object> map = new HashMap<>();
        map.put("ajaxItemCount", cart.getCount());
        map.put("ajaxItemLast", cartItems.getgName());
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);

    }
}
