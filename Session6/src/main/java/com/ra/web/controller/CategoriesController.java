package com.ra.web.controller;

import com.ra.web.model.Category;
import com.ra.web.service.CategoryService;
import com.ra.web.service.impl.CategoryServiceProcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CategoriesController", value = "/categories")
public class CategoriesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CategoryService service = new CategoryServiceProcImpl();
        if (action == null || action.equals("index")) {
            List<Category> data = service.findAll();
            req.setAttribute("data", data);
            req.getRequestDispatcher("WEB-INF/views/categories/index.jsp").forward(req, resp);
        }
        if (action != null && action.equals("create")) {
            List<Category> data = service.findAll();
            req.setAttribute("data", data);
            req.getRequestDispatcher("WEB-INF/views/categories/create.jsp").forward(req, resp);
        }
        if (action != null && action.equals("edit")) {
            String id = req.getParameter("id");
            Category category = service.findId(id);
            List<Category> data = service.findAll();
            req.setAttribute("data", data);
            req.setAttribute("category", category);
            req.getRequestDispatcher("WEB-INF/views/categories/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        // Lấy thông tin từ form
        String txtName = req.getParameter("txtName");
        String txtParentId = req.getParameter("txtParentId");
        boolean txtStatus = Boolean.parseBoolean(req.getParameter("txtStatus"));
        // Tạo đối tượng
        Category category = new Category();
        category.setName(txtName);
        category.setParentId(txtParentId);
        category.setStatus(txtStatus);
        // Xủ lý dữ liệu
        CategoryService service = new CategoryServiceProcImpl();
        if (action != null && action.equals("edit")) {
            String txtId = req.getParameter("txtId");
            category.setId(txtId);
            service.edit(category);
        }
        if (action != null && action.equals("create")) {
            category.setId(UUID.randomUUID().toString());
            service.add(category);
        }
        resp.sendRedirect("/categories");
    }
}
