package com.app.controller;

import com.app.dao.UserDAO;
import com.app.dao.UserDAOImpl;

import com.app.model.User;
import com.app.util.PasswordUtil;
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
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
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
            User user = userDAO.getUserByEmail(email);
            if (user == null)
            {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
                return;
            }
            else if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
                return;
            }
            else {
                HttpSession session = req.getSession(true);
                session.setAttribute("loggedUser", user);
                resp.sendRedirect(req.getContextPath()+"/jsp/dashboard.jsp");
                return;
            }
        } catch (SQLException e) {
            log("Database error during login", e);
            req.setAttribute("error", "Something went wrong. Please try again later.");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
