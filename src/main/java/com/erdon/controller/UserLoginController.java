package com.erdon.controller;

import com.erdon.entity.User;
import com.erdon.service.UserService;
import com.erdon.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie cookie1 = new Cookie("username",username);
        cookie1.setPath("/books");
        cookie1.setMaxAge(60*60*24*7);
        resp.addCookie(cookie1);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        if (login!=null){
            req.setAttribute("msg","");
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24*7);
            resp.addCookie(cookie);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        }else if (login==2){
//            req.setAttribute("msg","密码错误，请重新输入！");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            req.setAttribute("msg","用户名或密码错误,请重新输入！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
}
