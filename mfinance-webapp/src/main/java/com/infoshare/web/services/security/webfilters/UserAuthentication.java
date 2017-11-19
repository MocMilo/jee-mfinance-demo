package com.infoshare.web.services.security.webfilters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "isUserAuthenticated")
public class UserAuthentication implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthentication.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();

        LOGGER.info("login filter request path: {}", path);
        if (path.startsWith("/auth/logout")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (req.getSession().getAttribute("authenticatedUser") == null) {
                req.getRequestDispatcher("/auth/accessdenied.jsp").forward(req, resp);
                LOGGER.warn("Access denied! Not authenticated user request!");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }


    @Override
    public void destroy() {

    }
}
