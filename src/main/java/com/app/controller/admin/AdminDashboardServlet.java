package com.app.controller.admin;

import com.app.model.User;
import com.app.service.UserService;
import com.app.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        String error = req.getParameter("error");

        if("selfDelete".equals(error))
            req.setAttribute("error", "Self deletion is not permitted");

        if("Database".equals(error))
            req.setAttribute("error", "Something went wrong. Please try again later");

        try {
            users = userService.getAllUsers();

            req.setAttribute("usersList", users);
            req.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            log("Error while displaying users", e);
            req.setAttribute("error", "Fetching users failed");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
