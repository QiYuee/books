package com.erdon.controller2;

import com.erdon.controller.BaseController;
import com.erdon.entity.Cart;
import com.erdon.entity.User;
import com.erdon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/order")
public class OrderController {
    //    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder(HttpServletRequest req, Cart cart) throws ServletException, IOException {
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        String orderId = orderService.saveOrder(cart, loginUser.getId());
        req.getSession().setAttribute("orderId", orderId);
        return "redirect:/pages/cart/checkout.jsp";
    }
}
