package ru.blogspot.toolkas.math.ll.parser.ast;

public class Add implements Expression {
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public double eval() throws Exception {
        return left.eval() + right.eval();
    }
}
