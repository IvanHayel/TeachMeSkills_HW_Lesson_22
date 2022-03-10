package by.teachmeskills.calculator.entity;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class Operation<T extends Number> implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull String operand;
    @NonNull T primaryValue;
    T secondaryValue;
    @NonNull T result;
}