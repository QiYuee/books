package com.erdon.controller;

import com.erdon.entity.User;
import com.erdon.service.BookService;
import com.erdon.service.UserService;
import com.erdon.service.impl.UserServiceImpl;
import com.erdon.utils.WebUtils;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends BaseController {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    private UserService userService = (UserService) ac.getBean("userService");

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");

//        user.setUsername(username);
//        user.setPassword(password);
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User login = userService.login(user);
        if (login != null) {
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            req.setAttribute("msg", "");
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
//        }else if (login==2){
//            req.setAttribute("msg","密码错误，请重新输入！");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误,请重新输入！");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUser(user.getUsername())) {
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else {
                int i = userService.addUser(user);
                if (i != 0) {
                    System.out.println("用户注册成功！");
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                } else {
                    req.setAttribute("msg", "注册失败!");
                    req.setAttribute("username", user.getUsername());
                    req.setAttribute("email", user.getEmail());
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("msg", "验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    public void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    public void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        Boolean aBoolean = userService.existsUser(username);
        Map<String, Object> map = new HashMap<>();
        map.put("aBoolean", aBoolean);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
