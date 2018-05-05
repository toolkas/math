package ru.blogspot.toolkas.math.ll.parser;

import ru.blogspot.toolkas.math.ll.lexer.Lexem;
import ru.blogspot.toolkas.math.ll.lexer.Token;
import ru.blogspot.toolkas.math.ll.parser.ast.*;

import java.util.List;

public class MathParser {
    private int position = 0;

    public Expression parse(List<Lexem> lexems) throws MathParseException {
        Expression result = expression(lexems);

        consume(lexems, Token.EOF);

        return result;
    }

    private Expression expression(List<Lexem> lexems) throws MathParseException {
        Expression result = multExpression(lexems);

        while (true) {
            if (check(lexems, Token.OPERATOR, "+")) {
                Expression right = multExpression(lexems);
                result = new Add(result, right);
                continue;
            }

            if (check(lexems, Token.OPERATOR, "-")) {
                Expression right = multExpression(lexems);
                result = new Minus(result, right);
                continue;
            }

            break;
        }
        return result;
    }

    private Expression multExpression(List<Lexem> lexems) throws MathParseException {
        Expression result = atom(lexems);

        while (true) {
            if (check(lexems, Token.OPERATOR, "*")) {
                Expression right = atom(lexems);
                result = new Multiply(result, right);
                continue;
            }

            if (check(lexems, Token.OPERATOR, "/")) {
                Expression right = atom(lexems);
                result = new Divide(result, right);
                continue;
            }

            break;
        }

        return result;
    }

    private Atom atom(List<Lexem> lexems) throws MathParseException {
        if (check(lexems, Token.NUMBER)) {
            double value = Double.parseDouble(prev(lexems).getValue());
            return new NumberAtom(value);
        } else if(check(lexems, Token.BRACKET, "(")) {
            Expression expression = expression(lexems);
            consume(lexems, Token.BRACKET, ")");
            return new ExpressionAtom(expression);
        }

        throw new MathParseException("unexpected atom lexem: " + current(lexems));
    }


    private boolean check(final List<Lexem> lexems, Token type) {
        Lexem token = get(lexems, 0);
        if (token.getToken() != type) {
            return false;
        }
        position++;
        return true;
    }

    private boolean check(final List<Lexem> lexems, Token type, String value) {
        Lexem token = get(lexems, 0);
        if (token.getToken() != type) {
            return false;
        }
        if (!String.valueOf(token.getValue()).equalsIgnoreCase(String.valueOf(value))) {
            return false;
        }
        position++;
        return true;
    }

    private Lexem current(List<Lexem> lexems) {
        return get(lexems, 0);
    }

    private Lexem prev(List<Lexem> lexems) {
        return get(lexems, -1);
    }

    private Lexem get(final List<Lexem> lexems, int offset) {
        if (position + offset < 0) {
            throw new IndexOutOfBoundsException("thte is no lexem at [" + (position + offset) + "] position");
        }

        if (position + offset >= lexems.size()) {
            return new Lexem(Token.EOF, null);
        }

        return lexems.get(position + offset);
    }

    private void consume(final List<Lexem> lexems, Token token) throws MathParseException {
        Lexem lexem = get(lexems, 0);
        if (lexem.getToken() != token) {
            throw new MathParseException("expected " + token + ", but found " + lexem);
        }

        position++;
    }

    private void consume(final List<Lexem> lexems, Token token, String value) throws MathParseException {
        Lexem lexem = get(lexems, 0);
        if (lexem.getToken() != token) {
            throw new MathParseException("expected " + token + ", but found " + lexem);
        }
        if (!lexem.getValue().equalsIgnoreCase(value)) {
            throw new MathParseException("expected " + token + ", but found " + lexem);
        }

        position++;
    }
}
