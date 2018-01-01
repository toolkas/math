package ru.blogspot.toolkas.math.lexer;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MathLexer {
    private TokenizeState state = TokenizeState.DEFAULT;
    private String expression;
    private String value = "";
    private int position = -1;

    public MathLexer(String expression) {
        Objects.requireNonNull(expression, "expression can't be null");
        this.expression = expression;
    }

    public List<Lexem> tokenize() throws TokenizeException {
        List<Lexem> lexems = new ArrayList<>();

        int ch;
        while ((ch = get()) != -1) {
            Lexem lexem = nextLexem(ch);
            if (lexem != null) {
                lexems.add(lexem);
            }
        }

        if (state != TokenizeState.DEFAULT) {
            Lexem lexem = nextLexem(ch);
            if (lexem != null) {
                lexems.add(lexem);
            }
        }

        if (StringUtils.isNotBlank(value)) {
            throw new TokenizeException("can't parse '" + value + "'");
        }

        lexems.add(new Lexem(Token.EOF, null));

        return lexems;
    }

    private Lexem nextLexem(int b) throws TokenizeException {
        char ch = (char) b;

        switch (state) {
            case DEFAULT:
                if (Character.isDigit(ch)) {
                    state = TokenizeState.NUMBER_START;
                    position -= 1;
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    state = TokenizeState.OPERATOR;
                    position -= 1;
                } else if (ch == '(' || ch == ')') {
                    state = TokenizeState.BRACKET;
                    position -= 1;
                } else if (Character.isWhitespace(ch)) {
                    return null;
                } else {
                    throw new TokenizeException(
                            "unexpected character '" + ch + "' in state " + state
                    );
                }
                break;
            case NUMBER_START:
                if (Character.isDigit(ch)) {
                    value += ch;
                } else if (ch == '.') {
                    value += ch;
                    state = TokenizeState.NUMBER_END;
                } else {
                    return createToken(Token.NUMBER, true);
                }
                break;
            case NUMBER_END:
                if (Character.isDigit(ch)) {
                    value += ch;
                } else {
                    return createToken(Token.NUMBER, true);
                }
                break;
            case OPERATOR:
                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    value += ch;
                    return createToken(Token.OPERATOR, false);
                }

                break;
            case BRACKET:
                if (ch == '(' || ch == ')') {
                    value += ch;
                    return createToken(Token.BRACKET, false);
                }

                break;
        }
        return null;
    }

    private int get() {
        position += 1;
        return position < expression.length() ?
                expression.charAt(position) : -1;
    }

    private Lexem createToken(Token token, boolean back) {
        Lexem lexem = new Lexem(token, value);
        value = "";

        state = TokenizeState.DEFAULT;
        if (back) {
            position -= 1;
        }
        return lexem;
    }

    private enum TokenizeState {
        DEFAULT, NUMBER_START, NUMBER_END, OPERATOR, BRACKET;
    }
}
