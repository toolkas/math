package ru.blogspot.toolkas.math.rpn;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Objects;
import java.util.Stack;

public class ReversePolishEvaluator {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";

    public double eval(String expression) {
        String[] values = Objects.requireNonNull(expression).split(" ");

        Stack<String> stack = new Stack<>();
        for (String value : values) {
            if (StringUtils.isBlank(value)) {
                continue;
            }

            if (NumberUtils.isNumber(value)) {
                stack.push(value);
                continue;
            } else if (PLUS.equals(value)) {
                String val2 = stack.pop();
                String val1 = stack.pop();

                double result = Double.parseDouble(val1) + Double.parseDouble(val2);
                stack.push(String.valueOf(result));
            } else if (MINUS.equals(value)) {
                String val2 = stack.pop();
                String val1 = stack.pop();

                double result = Double.parseDouble(val1) - Double.parseDouble(val2);
                stack.push(String.valueOf(result));
            } else if (DIVIDE.equals(value)) {
                String val2 = stack.pop();
                String val1 = stack.pop();

                double result = Double.parseDouble(val1) / Double.parseDouble(val2);
                stack.push(String.valueOf(result));
            } else if (MULTIPLY.equals(value)) {
                String val2 = stack.pop();
                String val1 = stack.pop();

                double result = Double.parseDouble(val1) * Double.parseDouble(val2);
                stack.push(String.valueOf(result));
            } else {
                throw new IllegalArgumentException("unsupported value: " + value);
            }
        }

        return Double.parseDouble(stack.pop());
    }
}
