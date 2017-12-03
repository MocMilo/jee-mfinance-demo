package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.persistence.user.UserService;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/users")
public class UserMenagementServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ConstantsProvider.ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ConstantsProvider.ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }
}
