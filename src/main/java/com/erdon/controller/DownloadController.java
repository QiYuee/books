package com.erdon.controller;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取要下载的文件名
        String filename = "11.jpg";
//        2、通过servletContext对象读取文件内容
        ServletContext servletContext =  getServletContext();
        String mimeType = servletContext.getMimeType("/static/img/" + filename);
//        3、响应浏览器数据类型
        System.out.println(mimeType);
        resp.setContentType(mimeType);
//        4、告诉浏览器文件用于下载
        resp.setHeader("Content-disposition","attachment;filename="+filename);
//        5、向浏览器响应下载文件
//        5.1获取文件输入流
        InputStream resourceAsStream = servletContext.getResourceAsStream("/static/img/" + filename+"");
//        5.2获取响应输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        IOUtils.copy(resourceAsStream,outputStream);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
