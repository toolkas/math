package ru.blogspot.toolkas.math.parser.ast;

public class ExpressionAtom extends Atom {
    private final Expression expression;

    public ExpressionAtom(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double eval() throws Exception {
        return expression.eval();
    }
}
