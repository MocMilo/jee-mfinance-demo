package com.infoshareacademy.web.servlets.navigation;

import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.utils.constants.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/panel")
public class AdminMenuServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("/admin/panel.jsp");
        LOGGER.info("User from session:{}", user.getLogin());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(ConstantsProvider.AUTH_USER);
        resp.sendRedirect("/admin/panel.jsp");
        LOGGER.info("User from session:{}", user.getLogin());
    }

}

