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
        //разбиваем строку пробелами
        String[] values = Objects.requireNonNull(expression).split(" ");

        Stack<Double> stack = new Stack<>();
        for (String value : values) {
            if (StringUtils.isBlank(value)) {
                //если встретился пробел - пропускаем и идем дальше
                continue;
            }

            if (NumberUtils.isNumber(value)) {
                //встретилось число - переносим в стек
                stack.push(Double.parseDouble(value));
            } else if (PLUS.equals(value)) {
                //Встретился знак сложения.
                //Читаем последнее число
                double val2 = stack.pop();
                //Читаем предпоследнее число
                double val1 = stack.pop();

                //Складываем числа
                double result = val1 + val2;
                //Результат сложения переносим в стек
                stack.push(result);
            } else if (MINUS.equals(value)) {
                //Встретился знак вычитания.
                //Читаем последнее число
                double val2 = stack.pop();
                //Читаем предпоследнее число
                double val1 = stack.pop();

                //Из предпоследнего числа вычитаем последнее
                double result = val1 - val2;
                //Результат вычитания переносим в стек
                stack.push(result);
            } else if (DIVIDE.equals(value)) {
                //Встретился знак деления.
                //Читаем последнее число
                double val2 = stack.pop();
                //Читаем предпоследнее число
                double val1 = stack.pop();

                //Делим предпоследнее число на последнее
                double result = val1 / val2;
                //Результат деления переносим в стек
                stack.push(result);
            } else if (MULTIPLY.equals(value)) {
                //Встретился знак умножения.
                //Читаем последнее число
                double val2 = stack.pop();
                //Читаем предпоследнее число
                double val1 = stack.pop();

                //Перемножаем числа
                double result = val1 * val2;
                //Результат перемножения переносим в стек
                stack.push(result);
            } else {
                throw new IllegalArgumentException("unsupported value: " + value);
            }
        }

        //Читаем результат вычисления из стека
        double result = stack.pop();

        //Если в стеке еще что-то осталось после вычислений - значит исходное выражение было некорректно. Ошибка
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("expression[" + expression + "] is incorrect");
        }

        return result;
    }
}
