package com.ra.controller;

import com.ra.model.User;
import com.ra.service.UserService;
import com.ra.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
    private UserService userService;

    public UsersServlet() {
        userService = new UserServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAll();

        request.setAttribute("data", users);

        request.getRequestDispatcher("WEB-INF/views/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
