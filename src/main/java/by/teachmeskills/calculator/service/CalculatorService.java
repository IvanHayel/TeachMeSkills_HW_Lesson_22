package by.teachmeskills.calculator.service;

import by.teachmeskills.calculator.entity.Operation;

public interface CalculatorService<T extends Number> {
    Operation<T> evaluate(String operand, String primaryValue, String secondaryValue);

    Operation<T> evaluate(String operand, String primaryValue);

    Operation<T> evaluate(String operand, T primaryValue, T secondaryValue);

    Operation<T> evaluate(String operand, T primaryValue);
}