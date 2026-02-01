package com.app.controller.admin;

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

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));

        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("loggedUser");

        try {
            if(userService.deleteUser(userId, user.getId()))
            {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard?message=success");
            }
            else
            {
                req.setAttribute("error","error occurred, please try again");
                req.getRequestDispatcher("/jsp/admin/dashboard.jsp");
            }


        } catch (SQLException e) {
            log("Database error during user deletion", e);
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=Database");
        } catch (IllegalStateException e) {
            log("Self deletion attempt", e);
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=selfDelete");
        }
    }
}
