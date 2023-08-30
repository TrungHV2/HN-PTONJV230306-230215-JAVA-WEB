package com.ra.web.Session4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý abc -> xyz
        // Lấy dữ liệu từ request
        String id = request.getParameter("id");
        // Truyền dữ liệu
        request.setAttribute("data", "TrungHV | id = " + id);
        request.getRequestDispatcher("views/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
