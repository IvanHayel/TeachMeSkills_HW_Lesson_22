package by.teachmeskills.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class RealNumberCalculator implements Calculator<Double>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Double evaluate(String operand, Double... values) {
        switch (operand.toLowerCase()) {
            case "+":
            case "add":
                return add(values[FIRST_VALUE_INDEX], values[SECOND_VALUE_INDEX]);
            case "-":
            case "subtract":
                return subtract(values[FIRST_VALUE_INDEX], values[SECOND_VALUE_INDEX]);
            case "*":
            case "multiply":
                return multiply(values[FIRST_VALUE_INDEX], values[SECOND_VALUE_INDEX]);
            case "/":
            case "divide":
                return divide(values[FIRST_VALUE_INDEX], values[SECOND_VALUE_INDEX]);
            case "**":
            case "pow":
                return pow(values[FIRST_VALUE_INDEX], values[SECOND_VALUE_INDEX]);
            case "sqrt":
                return sqrt(values[FIRST_VALUE_INDEX]);
            default:
                return Double.NaN;
        }
    }

    @Override
    public Double add(Double firstTerm, Double secondTerm) {
        return firstTerm + secondTerm;
    }

    @Override
    public Double subtract(Double minuend, Double subtrahend) {
        return minuend - subtrahend;
    }

    @Override
    public Double multiply(Double firstMultiplier, Double secondMultiplier) {
        return firstMultiplier * secondMultiplier;
    }

    @Override
    public Double divide(Double divisor, Double dividend) {
        return divisor / dividend;
    }

    @Override
    public Double pow(Double value, Double power) {
        return Math.pow(value, power);
    }

    @Override
    public Double sqrt(Double value) {
        return Math.sqrt(value);
    }
}