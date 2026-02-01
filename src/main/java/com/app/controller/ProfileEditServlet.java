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

@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsp/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        HttpSession session = req.getSession(false);
        User loggedUser = (session != null) ? (User)session.getAttribute("loggedUser") : null;

        if(loggedUser != null)
            id = loggedUser.getId();
        else {
            req.setAttribute("error", "UnAuthenticated access, please Login");
            req.getRequestDispatcher("/login").forward(req,resp);
            return;
        }
        String newName = req.getParameter("newName");
        String newEmail = req.getParameter("newEmail");

        if(newName == null || newName.isBlank() ||
                newEmail == null || newEmail.isBlank()){
            req.setAttribute("error", "Please fill the name and email");
            req.getRequestDispatcher("/profile/edit").forward(req, resp);
            return;
        }

        try {
            if(userService.updateProfile(id, newName, newEmail))
            {
                loggedUser.setName(newName);
                loggedUser.setEmail(newEmail);
                session.setAttribute("loggedUser", loggedUser);
                resp.sendRedirect(req.getContextPath() + "/profile");
            }
            else
            {
                req.setAttribute("error", "Failed to update your profile, Try again later");
                req.getRequestDispatcher("/profile/edit").forward(req, resp);
            }
        } catch (SQLException e) {
            log("Database error during login", e);
            req.setAttribute("error", "Something went wrong. Please try again later.");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } catch(SecurityException e) {
            log("User Not Found", e);
            req.setAttribute("error", "User not found, please login");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } catch (IllegalStateException e) {
            log("Email Already exists, please try another email", e);
            req.setAttribute("error", "Email Already Exists, try another email");
            req.getRequestDispatcher("/jsp/editProfile.jsp").forward(req, resp);
        }
    }
}
