package com.erdon.controller2;

import com.erdon.controller.BaseController;
import com.erdon.entity.Book;
import com.erdon.entity.Cart;
import com.erdon.entity.CartItems;
import com.erdon.service.BookService;
import com.erdon.utils.WebUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
    //    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    private BookService bookService;

    @RequestMapping("/addCart")
    public String addCart(HttpServletRequest req, Integer id) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBook(id);
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItems);
        req.getSession().setAttribute("cartLastName", cartItems.getgName());
        return "redirect:" + req.getHeader("Referer");
    }

    @RequestMapping("/deletCartItem")
    public String deletCartItem(HttpServletRequest req, Integer id) throws IOException {
//        String id = req.getParameter("id");
//        int i = WebUtils.parseInt(id, 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deletItem(id);
        }
        return "redirect:/pages/cart/cart.jsp";
    }

    @RequestMapping("/clearCart")
    public String closeCart(HttpServletRequest req) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.delectAllItem();
        return "redirect:/pages/cart/cart.jsp";
    }

    @RequestMapping("/updateCartItem")
    public String updateCartItem(Integer id, Integer countItem, Cart cart) throws IOException {
//        String id = req.getParameter("id");
//        String countItem = req.getParameter("countItem");
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        int i = WebUtils.parseInt(id, 0);
//        int count = WebUtils.parseInt(countItem, 1);
        if (cart != null) {
            if (countItem == 0) {
                cart.deletItem(id);
            } else {
                cart.updateItemNumber(id, countItem);
            }
        }
        return "/pages/cart/cart.jsp";
    }
    @ResponseBody
    @RequestMapping("/ajaxAddCart")
    public Map ajaxAddCart(HttpServletRequest req,Integer id) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBook(id);
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
//        Gson gson = new Gson();
//        String s = gson.toJson(map);
//        resp.getWriter().write(s);
        return map;

    }
}
