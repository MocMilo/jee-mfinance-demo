package com.infoshareacademy.web.servlets.navigation;

import com.infoshareacademy.web.model.session.SessionContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main")
public class UserMenuServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuServlet.class);
    @Inject
    private SessionContainer sessionContainer;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("main.jsp");
        LOGGER.info("user from session:{}", sessionContainer.getUser().getLogin());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("main.jsp");
        LOGGER.info("user from session:{}", sessionContainer.getUser().getLogin());
    }
}
