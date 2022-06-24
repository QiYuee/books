package com.erdon.jsonandajax;

import com.erdon.controller.BaseController;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseController {
    public void ajaxRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("ajax请求进来了!");
        resp.setContentType("text/html; charset=utf-8");
        Person person = new Person("熊二",12);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    public void jqueryAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("jqueryAjax请求进来了!");
        resp.setContentType("text/html; charset=utf-8");
        Person person = new Person("熊二",12);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    public void jqueryGetAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("jqueryGetAjax请求进来了!");
        resp.setContentType("text/html; charset=utf-8");
        Person person = new Person("熊二",12);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    public void jqueryPostAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("jqueryPostAjax请求进来了!");
        resp.setContentType("text/html; charset=utf-8");
        Person person = new Person("熊二",12);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
}
