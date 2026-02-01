package com.app.controller;

import com.app.service.UserService;
import com.app.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(name == null || name.isBlank() ||
            email == null || email.isBlank() ||
                password == null || password.isBlank()){

            req.setAttribute("error", "All fields are required");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
            return;
        }

        try {
            if(!userService.register(name, email, password)){
                req.setAttribute("error", "Email is already registered");
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
                return;
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            }
        } catch (SQLException e) {
            log("Error during registration", e);
            req.setAttribute("error", "Registration failed, Try again later");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }
    }
}
