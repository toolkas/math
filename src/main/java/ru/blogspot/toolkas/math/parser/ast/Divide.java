package ru.blogspot.toolkas.math.parser.ast;

public class Divide implements Expression {
    private final Expression left;
    private final Expression right;

    public Divide(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval() throws Exception {
        return left.eval() / right.eval();
    }
}
