package com.erdon.cookie;

import com.erdon.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieController extends BaseController {

    public void setCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("key","value");
        resp.setContentType("text/html;charset=utf-8");
        resp.addCookie(cookie);
        try {
            resp.getWriter().write("cookie创建成功! <br/> 121414");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        resp.setContentType("text/html;charset=utf-8");
        for (Cookie cookie : cookies) {
            resp.getWriter().write("cookie["+cookie.getName()+"="+cookie.getValue()+"]");
            resp.getWriter().write("<br/>");
        }
    }
    public void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie cookie = new Cookie("key","value1");
        cookie.setMaxAge(5);
        resp.addCookie(cookie);

        resp.getWriter().write("cookie-key被修改了！");
    }

}
