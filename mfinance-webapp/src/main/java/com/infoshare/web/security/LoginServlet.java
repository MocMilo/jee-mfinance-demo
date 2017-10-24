package com.infoshare.web.security;

import com.infoshare.web.webconfiguration.AppMode;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "login")
public class LoginServlet extends HttpServlet {

    @Inject
    AppMode appMode;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("appSlaveMode", appMode.isSlave());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        //resp.sendRedirect("login.jsp");
    }
}
