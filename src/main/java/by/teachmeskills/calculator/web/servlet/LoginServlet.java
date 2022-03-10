package by.teachmeskills.calculator.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String USER_NAME_ATTRIBUTE = "user-name";
    private static final String USER_PASSWORD_ATTRIBUTE = "user-password";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    @Override
    @SneakyThrows(IOException.class)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.getWriter().println("LOGIN PAGE HERE");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        boolean isNotAuthenticated =
                session.getAttribute(USER_NAME_ATTRIBUTE) == null &&
                session.getAttribute(USER_PASSWORD_ATTRIBUTE) == null;
        if (isNotAuthenticated) {
            String login = req.getParameter(LOGIN_PARAMETER);
            String password = req.getParameter(PASSWORD_PARAMETER);
            session.setAttribute(USER_NAME_ATTRIBUTE, login);
            session.setAttribute(USER_PASSWORD_ATTRIBUTE, password);
        }
    }
}