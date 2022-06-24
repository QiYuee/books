package com.erdon.controller;

import com.erdon.entity.User;
import com.erdon.service.UserService;
import com.erdon.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegisterController extends HttpServlet {
    UserService service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if ("0000".equals(code)){
            if (service.existsUser(username)){
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

            }else {
                int i = service.addUser(new User(null, username, password, email));
                if (i!=0){
                    System.out.println("用户注册成功！");
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }else {
                    req.setAttribute("msg","注册失败!");
                    req.setAttribute("username",username);
                    req.setAttribute("email",email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }
            }
        }else {
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.setAttribute("msg","验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }


    }
}
