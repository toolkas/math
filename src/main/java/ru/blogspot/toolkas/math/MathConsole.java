package ru.blogspot.toolkas.math;

import ru.blogspot.toolkas.math.evaluator.MathEvaluator;

import java.util.Scanner;

public class MathConsole {
    public static void main(String[] args) throws Exception {
        MathEvaluator evaluator = new MathEvaluator();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("expr>>");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line == null) {
                    continue;
                }

                if ("exit".equals(line.trim())) {
                    break;
                }

                double value = evaluator.eval(line);
                System.out.println(value);
                System.out.println("expr>>");
            }
        }
    }
}
