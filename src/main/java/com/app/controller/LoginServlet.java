package com.app.controller;

import com.app.model.User;
import com.app.service.UserService;
import com.app.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email == null || email.isBlank() || password == null || password.isBlank())
        {
            req.setAttribute("error", "email and password are required");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        try {
            User user = userService.authenticate(email,password);
            if (user == null)
            {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
                return;
            }
            else {
                HttpSession session = req.getSession(true);
                session.setAttribute("loggedUser", user);
                resp.sendRedirect(req.getContextPath()+"/jsp/dashboard.jsp");
            }
        } catch (SQLException e) {
            log("Database error during login", e);
            req.setAttribute("error", "Something went wrong. Please try again later.");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
