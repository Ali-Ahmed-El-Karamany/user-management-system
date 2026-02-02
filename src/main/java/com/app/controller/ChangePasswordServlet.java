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

@WebServlet("/profile/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/changePassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword;
        String newPassword;
        String confirmPassword;

        HttpSession session = req.getSession(false);

        User loggedUser = (session != null) ? (User)session.getAttribute("loggedUser") : null;

        if(loggedUser == null)
        {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        oldPassword = req.getParameter("oldPassword");
        newPassword = req.getParameter("newPassword");
        confirmPassword = req.getParameter("confirmPassword");

        if(oldPassword == null || oldPassword.isBlank() ||
                newPassword == null || newPassword.isBlank() ||
                confirmPassword == null || confirmPassword.isBlank())
        {
            req.setAttribute("error", "All password fields are required");
            req.getRequestDispatcher("/jsp/changePassword.jsp").forward(req, resp);
            return;
        }

        if(!userService.confirmPassword(newPassword,confirmPassword))
        {
            req.setAttribute("error", "Password mismatch");
            req.getRequestDispatcher("/jsp/changePassword.jsp").forward(req, resp);
            return;
        }

        try {
            if(!userService.changePassword(loggedUser, oldPassword, newPassword))
            {
                req.setAttribute("error", "Something went wrong, please try again later");
                req.getRequestDispatcher("/profile").forward(req, resp);
                return;
            }

            resp.sendRedirect(req.getContextPath() + "/logout");

        } catch (SQLException e) {
            log("Database error during login", e);
            req.setAttribute("error", "Something went wrong. Please try again later.");
            req.getRequestDispatcher("/login").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "New password must be different from the current password");
            req.getRequestDispatcher("/jsp/changePassword.jsp").forward(req, resp);
        } catch (IllegalStateException e) {
            req.setAttribute("error", "Current password is incorrect");
            req.getRequestDispatcher("/jsp/changePassword.jsp").forward(req, resp);
        } catch (SecurityException e) {
            log("User Not Found", e);
            req.setAttribute("error", "User not found, please login");
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
