package com.erdon.controller;

import com.erdon.entity.Cart;
import com.erdon.entity.User;
import com.erdon.service.BookService;
import com.erdon.service.OrderService;
import com.erdon.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends BaseController{
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private OrderService orderService = (OrderService) ac.getBean("orderService");
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        String orderId = orderService.saveOrder(cart, loginUser.getId());
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
