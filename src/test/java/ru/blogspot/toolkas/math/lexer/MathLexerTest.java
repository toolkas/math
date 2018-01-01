package ru.blogspot.toolkas.math.lexer;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MathLexerTest {
    @Test(expected = NullPointerException.class)
    public void testInitNull() {
        new MathLexer(null);
    }

    @Test
    public void testNumber() throws TokenizeException {
        testToken("123.45", Token.NUMBER);
        testToken("12", Token.NUMBER);
    }

    @Test
    public void testOperators() throws TokenizeException {
        testToken("+", Token.OPERATOR);
        testToken("-", Token.OPERATOR);
        testToken("*", Token.OPERATOR);
        testToken("/", Token.OPERATOR);
    }

    @Test
    public void testBrackets() throws TokenizeException {
        testToken("(", Token.BRACKET);
        testToken(")", Token.BRACKET);
    }

    @Test
    public void testExpression() throws TokenizeException {
        MathLexer lexer = new MathLexer("12 - (1+23) / 56");
        List<Lexem> lexems = lexer.tokenize();

        Assert.assertEquals(lexems.size(), 10);

        Lexem lexem1 = lexems.get(0);
        Assert.assertEquals("12", lexem1.getValue());
        Assert.assertEquals(Token.NUMBER, lexem1.getToken());

        Lexem lexem2 = lexems.get(1);
        Assert.assertEquals("-", lexem2.getValue());
        Assert.assertEquals(Token.OPERATOR, lexem2.getToken());

        Lexem lexem3 = lexems.get(2);
        Assert.assertEquals("(", lexem3.getValue());
        Assert.assertEquals(Token.BRACKET, lexem3.getToken());

        Lexem lexem4 = lexems.get(3);
        Assert.assertEquals("1", lexem4.getValue());
        Assert.assertEquals(Token.NUMBER, lexem4.getToken());

        Lexem lexem5 = lexems.get(4);
        Assert.assertEquals("+", lexem5.getValue());
        Assert.assertEquals(Token.OPERATOR, lexem5.getToken());

        Lexem lexem6 = lexems.get(5);
        Assert.assertEquals("23", lexem6.getValue());
        Assert.assertEquals(Token.NUMBER, lexem6.getToken());

        Lexem lexem7 = lexems.get(6);
        Assert.assertEquals(")", lexem7.getValue());
        Assert.assertEquals(Token.BRACKET, lexem7.getToken());

        Lexem lexem8 = lexems.get(7);
        Assert.assertEquals("/", lexem8.getValue());
        Assert.assertEquals(Token.OPERATOR, lexem8.getToken());

        Lexem lexem9 = lexems.get(8);
        Assert.assertEquals("56", lexem9.getValue());
        Assert.assertEquals(Token.NUMBER, lexem9.getToken());

        Lexem eof = lexems.get(9);
        Assert.assertEquals(null, eof.getValue());
        Assert.assertEquals(Token.EOF, eof.getToken());
    }

    @Test(expected = TokenizeException.class)
    public void testIllegalExpression() throws TokenizeException {
        MathLexer lexer = new MathLexer("12 + a");
        List<Lexem> lexems = lexer.tokenize();
        System.out.println();

    }

    private void testToken(String expression, Token token) throws TokenizeException {
        MathLexer lexer = new MathLexer(expression);
        List<Lexem> lexems = lexer.tokenize();

        Assert.assertEquals(lexems.size(), 2);

        Lexem lexem = lexems.get(0);
        Assert.assertEquals(expression, lexem.getValue());
        Assert.assertEquals(token, lexem.getToken());

        Lexem eof = lexems.get(1);
        Assert.assertEquals(null, eof.getValue());
        Assert.assertEquals(Token.EOF, eof.getToken());
    }
}
