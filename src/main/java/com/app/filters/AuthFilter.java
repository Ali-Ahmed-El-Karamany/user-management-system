package com.app.filters;

import com.app.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns= {"/jsp/dashboard.jsp", "/jsp/profile.jsp",
                        "/profile", "/profile/*", "/jsp/changePassword.jsp"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse= (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession(false);

        User user = (session != null) ? (User)session.getAttribute("loggedUser") : null;

        if(user == null)
        {
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/jsp/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
