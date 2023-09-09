package com.ra.web.controller;

import com.ra.web.model.Category;
import com.ra.web.service.CategoryService;
import com.ra.web.service.impl.CategoryServiceImpl;
import com.ra.web.util.MySqlConnection;

import java.io.*;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
//        Connection conn = MySqlConnection.open();
//        if (conn != null)
//            System.out.println("[INFO] Connect successfully!");
//        else
//            System.out.println("[ERROR] Connect failed!");
        CategoryService service = new CategoryServiceImpl();

//        List<Category> data = service.findAll();
        List<Category> data = service.findByName("Điện");




        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>Danh sách danh mục sản phẩm</h1>");
        out.println("<ul>");
        for (Category c : data) {
            out.println("<li>" + c.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}