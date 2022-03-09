package by.teachmeskills.calculator.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    private static final String USER_NAME_ATTRIBUTE = "user-name";
    private static final String USER_PASSWORD_ATTRIBUTE = "user-password";
    private static final String LOGIN_PATH = "/login";

    @Override
    @SneakyThrows({IOException.class, ServletException.class})
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + LOGIN_PATH;

        boolean isAuthenticated = session != null &&
                session.getAttribute(USER_NAME_ATTRIBUTE) != null &&
                session.getAttribute(USER_PASSWORD_ATTRIBUTE) != null;
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);

        if (isAuthenticated || isLoginRequest) filterChain.doFilter(request, response);
        else response.sendRedirect(loginURI);
    }
}