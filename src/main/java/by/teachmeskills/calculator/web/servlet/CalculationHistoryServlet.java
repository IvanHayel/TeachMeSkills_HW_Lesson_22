package by.teachmeskills.calculator.web.servlet;

import by.teachmeskills.calculator.entity.Operation;
import by.teachmeskills.calculator.storage.OperationStorage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/my-calculations")
public class CalculationHistoryServlet extends HttpServlet {
    private static final String SESSION_OPERATION_STORAGE_ATTRIBUTE = "session-storage";

    @Override
    @SneakyThrows(IOException.class)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        OperationStorage sessionStorage = (OperationStorage) session.getAttribute(SESSION_OPERATION_STORAGE_ATTRIBUTE);
        if (sessionStorage == null) {
            sessionStorage = new OperationStorage();
            session.setAttribute(SESSION_OPERATION_STORAGE_ATTRIBUTE, sessionStorage);
        }
        List<Operation> operations = sessionStorage.getAll();
        operations.forEach(writer::println);
    }
}