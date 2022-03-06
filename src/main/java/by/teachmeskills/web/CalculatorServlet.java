package by.teachmeskills.web;

import by.teachmeskills.model.Calculator;
import by.teachmeskills.model.RealNumberCalculator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/evaluate")
public class CalculatorServlet extends HttpServlet {
    private static final String OPERAND_PARAMETER = "operand";
    private static final String FIRST_VALUE_PARAMETER = "first-value";
    private static final String SECOND_VALUE_PARAMETER = "second-value";
    private static final String CALCULATOR_HISTORY_ATTRIBUTE = "calculator-history";
    private static final String FORMAT_CALCULATION_RESULT = "\t%f %s %f = %f";

    private Calculator<Double> calculator;

    @Override
    public void init() {
        calculator = new RealNumberCalculator();
    }

    @Override
    @SneakyThrows(IOException.class)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        provideCalculatorHistory(session, writer);
    }

    @Override
    @SneakyThrows(IOException.class)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        @NonNull String operandParameter = req.getParameter(OPERAND_PARAMETER);
        String operand = operandParameter.equals(" ") ? "+" : operandParameter;
        @NonNull Double firstValue = Double.parseDouble(req.getParameter(FIRST_VALUE_PARAMETER));
        @NonNull Double secondValue = Double.parseDouble(req.getParameter(SECOND_VALUE_PARAMETER));

        Double calculationResult = calculator.evaluate(operand, firstValue, secondValue);
        String currentCalculation =
                String.format(FORMAT_CALCULATION_RESULT, firstValue, operand, secondValue, calculationResult);

        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

        provideCalculationResult(writer, currentCalculation);
        provideCalculatorHistory(session, writer);
        updateCalculatorHistory(session, currentCalculation);
    }

    @SuppressWarnings("unchecked")
    private void provideCalculatorHistory(@NonNull HttpSession session, @NonNull PrintWriter writer) {
        List<String> attributes;
        if (session.isNew()) {
            attributes = new ArrayList<>();
            session.setAttribute(CALCULATOR_HISTORY_ATTRIBUTE, attributes);
        } else {
            attributes = (List<String>) session.getAttribute(CALCULATOR_HISTORY_ATTRIBUTE);
        }
        writer.println("History of session calculations:");
        attributes.forEach(writer::println);
    }

    private void provideCalculationResult(@NonNull PrintWriter writer, @NonNull String currentCalculation) {
        writer.println("Current calculation:");
        writer.println(currentCalculation);
        writer.println();
    }

    @SuppressWarnings("unchecked")
    private void updateCalculatorHistory(@NonNull HttpSession session, @NonNull String currentCalculation) {
        List<String> calculatorHistory = (List<String>) session.getAttribute(CALCULATOR_HISTORY_ATTRIBUTE);
        calculatorHistory.add(currentCalculation);
    }
}