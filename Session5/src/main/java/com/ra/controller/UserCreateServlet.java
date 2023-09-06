package com.ra.controller;

import com.ra.model.User;
import com.ra.service.UserService;
import com.ra.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UserCreateServlet", value = "/create-user")
public class UserCreateServlet extends HttpServlet {
    private UserService userService;

    public UserCreateServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/create-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));
        double balance = Double.parseDouble(request.getParameter("balance"));
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = frm.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean stt = Boolean.parseBoolean(request.getParameter("status"));

        System.out.println(id);
        System.out.println(username);
        System.out.println(password);
        System.out.println(role);
        System.out.println(balance);
        System.out.println(birthday);
        System.out.println(stt);

        userService.add(new User(id, username, password, stt, role, balance, birthday));

        response.sendRedirect("/UsersServlet");
    }
}
