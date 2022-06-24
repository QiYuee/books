package com.erdon.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
       if (ServletFileUpload.isMultipartContent(req)){
           FileItemFactory factory =new DiskFileItemFactory();
           ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
           try {
               List<FileItem> fileItems = servletFileUpload.parseRequest(req);
               for (FileItem item:fileItems) {
                   if (item.isFormField()){
                       System.out.println("表单项name属性："+item.getFieldName());
                       System.out.println("表单项value属性值："+item.getString("utf-8"));
                   }else {
                       System.out.println("表单项name属性："+item.getFieldName());
                       System.out.println("上传的文件名:"+item.getName());
                        item.write(new File("C:\\Users\\Administrator\\Desktop\\javaweb\\"+item.getName()));
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }
}
