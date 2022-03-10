package by.teachmeskills.calculator.service;

import by.teachmeskills.calculator.entity.Operation;
import by.teachmeskills.calculator.storage.OperationStorage;
import lombok.NonNull;
import lombok.Value;

@Value
public class RealNumberCalculatorService implements CalculatorService<Double> {
    private static final String INCREMENT_OPERAND = "++";
    private static final String DECREMENT_OPERAND = "--";
    private static final String SQUARE_ROOT_OPERAND = "sqrt";
    private static final String ADD_OPERAND = "+";
    private static final String SUBTRACT_OPERAND = "-";
    private static final String MULTIPLICATION_OPERAND = "*";
    private static final String DIVISION_OPERAND = "/";
    private static final String EXPONENTIATION_OPERAND = "**";

    @NonNull OperationStorage storage;

    @Override
    public Operation<Double> evaluate(@NonNull String operand, @NonNull String primaryValue, String secondaryValue) {
        if (secondaryValue == null || isEmptyOrBlank(secondaryValue)) return evaluate(operand, primaryValue);
        if (isEmptyOrBlank(operand, primaryValue, secondaryValue)) return null;
        return evaluate(operand, Double.parseDouble(primaryValue), Double.parseDouble(secondaryValue));
    }

    @Override
    public Operation<Double> evaluate(@NonNull String operand, @NonNull String primaryValue) {
        if (isEmptyOrBlank(operand, primaryValue)) return null;
        return evaluate(operand, Double.parseDouble(primaryValue));
    }

    @Override
    public Operation<Double> evaluate(@NonNull String operand, @NonNull Double primaryValue) {
        if (isEmptyOrBlank(operand)) return null;
        Double result;
        switch (operand) {
            case INCREMENT_OPERAND:
                result = increment(primaryValue);
                break;
            case DECREMENT_OPERAND:
                result = decrement(primaryValue);
                break;
            case SQUARE_ROOT_OPERAND:
                result = sqrt(primaryValue);
                break;
            default:
                return null;
        }
        Operation<Double> operation = new Operation<>(operand, primaryValue, null, result);
        storage.offer(operation);
        return operation;
    }

    @Override
    public Operation<Double> evaluate(@NonNull String operand, @NonNull Double primaryValue, @NonNull Double secondaryValue) {
        if (isEmptyOrBlank(operand)) return null;
        Double result;
        switch (operand) {
            case ADD_OPERAND:
                result = add(primaryValue, secondaryValue);
                break;
            case SUBTRACT_OPERAND:
                result = subtract(primaryValue, secondaryValue);
                break;
            case MULTIPLICATION_OPERAND:
                result = multiply(primaryValue, secondaryValue);
                break;
            case DIVISION_OPERAND:
                result = divide(primaryValue, secondaryValue);
                break;
            case EXPONENTIATION_OPERAND:
                result = pow(primaryValue, secondaryValue);
                break;
            default:
                return null;
        }
        Operation<Double> operation = new Operation<>(operand, primaryValue, secondaryValue, result);
        storage.offer(operation);
        return operation;
    }

    private boolean isEmptyOrBlank(@NonNull String... values) {
        for (String value : values)
            if (value.trim().isEmpty()) return true;
        return false;
    }

    private Double add(Double firstTerm, Double secondTerm) {
        return firstTerm + secondTerm;
    }

    private Double subtract(Double minuend, Double subtrahend) {
        return minuend - subtrahend;
    }

    private Double multiply(Double firstMultiplier, Double secondMultiplier) {
        return firstMultiplier * secondMultiplier;
    }

    private Double divide(Double divisor, Double dividend) {
        return divisor / dividend;
    }

    private Double pow(Double value, Double power) {
        return Math.pow(value, power);
    }

    private Double increment(Double value) {
        return ++value;
    }

    private Double decrement(Double value) {
        return --value;
    }

    private Double sqrt(Double value) {
        return Math.sqrt(value);
    }
}