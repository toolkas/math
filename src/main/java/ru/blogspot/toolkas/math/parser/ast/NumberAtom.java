package ru.blogspot.toolkas.math.parser.ast;

public class NumberAtom extends Atom {
    private final double value;

    public NumberAtom(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double eval() throws Exception {
        return value;
    }
}
