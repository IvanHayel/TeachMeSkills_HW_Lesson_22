package by.teachmeskills.calculator.web;

import by.teachmeskills.calculator.model.operation.Operation;
import by.teachmeskills.calculator.model.operation.OperationStorage;
import by.teachmeskills.calculator.service.CalculatorService;
import by.teachmeskills.calculator.service.RealNumberCalculatorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculationServlet extends HttpServlet {
    private static final String OPERAND_PARAMETER = "operand";
    private static final String PRIMARY_VALUE_PARAMETER = "primary-value";
    private static final String SECONDARY_VALUE_PARAMETER = "secondary-value";
    private static final String SESSION_OPERATION_STORAGE_ATTRIBUTE = "session-storage";
    private static final String INCORRECT_OPERATION_MESSAGE = "Incorrect operation!";
    private static final OperationStorage operationStorage = new OperationStorage();

    private CalculatorService calculatorService;

    @Override
    public void init() {
        calculatorService = new RealNumberCalculatorService(operationStorage);
    }

    @Override
    @SneakyThrows(IOException.class)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

        String operand = req.getParameter(OPERAND_PARAMETER).replace(" ", "+");
        String primaryValue = req.getParameter(PRIMARY_VALUE_PARAMETER);
        String secondaryValue = req.getParameter(SECONDARY_VALUE_PARAMETER);

        Operation currentOperation = calculatorService.evaluate(operand, primaryValue, secondaryValue);

        provideRequestedOperationResult(session, writer, currentOperation);
    }

    private void provideRequestedOperationResult(HttpSession session, PrintWriter writer, Operation currentOperation) {
        if (currentOperation != null) {
            writer.println(currentOperation);
            updateSessionOperationStorage(session, currentOperation);
        } else {
            writer.println(INCORRECT_OPERATION_MESSAGE);
        }
    }

    private void updateSessionOperationStorage(HttpSession session, Operation operation) {
        OperationStorage sessionStorage = (OperationStorage) session.getAttribute(SESSION_OPERATION_STORAGE_ATTRIBUTE);
        if (sessionStorage == null) {
            sessionStorage = new OperationStorage();
            session.setAttribute(SESSION_OPERATION_STORAGE_ATTRIBUTE, sessionStorage);
        }
        sessionStorage.offer(operation);
    }
}