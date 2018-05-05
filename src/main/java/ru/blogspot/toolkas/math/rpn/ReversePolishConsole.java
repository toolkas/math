package ru.blogspot.toolkas.math.rpn;

import java.util.Scanner;

public class ReversePolishConsole {
    public static void main(String[] args) {
        ReversePolishEvaluator evaluator = new ReversePolishEvaluator();
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
