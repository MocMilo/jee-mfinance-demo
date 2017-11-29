package com.infoshareacademy.web.servlets.administration;

import com.infoshareacademy.web.services.persistence.user.IUserService;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/users")
public class UserMenagementServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenagementServlet.class);

    @Inject
    IUserService userService;

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
