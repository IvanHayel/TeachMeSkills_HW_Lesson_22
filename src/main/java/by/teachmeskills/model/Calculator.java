package by.teachmeskills.model;

public interface Calculator<T extends Number> {
    int FIRST_VALUE_INDEX = 0;
    int SECOND_VALUE_INDEX = 1;

    T evaluate(String operand, T... values);

    T add(T firstTerm, T secondTerm);

    T subtract(T minuend, T subtrahend);

    T multiply(T firstMultiplier, T secondMultiplier);

    T divide(T divisor, T dividend);

    T pow(T value, T power);

    T sqrt(T value);
}