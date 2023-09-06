package com.ra.controller;

import com.ra.model.User;
import com.ra.service.UserService;
import com.ra.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEditServlet", value = "/edit-user")
public class UserEditServlet extends HttpServlet {
    private UserService userService;

    public UserEditServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.findId(id);

        request.setAttribute("user", user);

        request.getRequestDispatcher("WEB-INF/views/edit-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
