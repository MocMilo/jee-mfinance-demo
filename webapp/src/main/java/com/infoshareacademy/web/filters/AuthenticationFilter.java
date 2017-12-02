package com.infoshareacademy.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter")
public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
            if (req.getSession().getAttribute("authenticatedUser") == null) {
                req.getRequestDispatcher("/accessdenied.jsp").forward(req, resp);
                LOGGER.warn("Access denied! Not authenticated request!");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
    }

    @Override
    public void destroy() {
    }
}
