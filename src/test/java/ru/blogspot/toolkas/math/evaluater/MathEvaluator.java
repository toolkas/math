package ru.blogspot.toolkas.math.evaluater;

import ru.blogspot.toolkas.math.lexer.Lexem;
import ru.blogspot.toolkas.math.lexer.MathLexer;
import ru.blogspot.toolkas.math.parser.MathParser;
import ru.blogspot.toolkas.math.parser.ast.Expression;

import java.util.List;

public class MathEvaluator {
    public double eval(String line) throws Exception {
        MathLexer lexer = new MathLexer(line);
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        return expression.eval();
    }
}
