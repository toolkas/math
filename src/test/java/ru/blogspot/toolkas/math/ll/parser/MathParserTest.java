package ru.blogspot.toolkas.math.ll.parser;

import org.junit.Assert;
import org.junit.Test;
import ru.blogspot.toolkas.math.ll.lexer.Lexem;
import ru.blogspot.toolkas.math.ll.lexer.MathLexer;
import ru.blogspot.toolkas.math.ll.lexer.Token;
import ru.blogspot.toolkas.math.ll.lexer.TokenizeException;
import ru.blogspot.toolkas.math.ll.parser.ast.Add;
import ru.blogspot.toolkas.math.ll.parser.ast.Expression;
import ru.blogspot.toolkas.math.ll.parser.ast.Minus;
import ru.blogspot.toolkas.math.ll.parser.ast.NumberAtom;

import java.util.ArrayList;
import java.util.List;

public class MathParserTest {
    @Test(expected = NullPointerException.class)
    public void testParseNull() throws Exception {
        MathParser parser = new MathParser();
        parser.parse(null);
    }

    @Test(expected = MathParseException.class)
    public void testParseEmpty() throws Exception {
        MathParser parser = new MathParser();
        parser.parse(new ArrayList<>());
    }

    @Test(expected = MathParseException.class)
    public void testParseEOF() throws Exception {
        MathParser parser = new MathParser();
        parser.parse(new ArrayList<Lexem>() {{
            add(new Lexem(Token.EOF, null));
        }});
    }

    @Test
    public void testParseNumber() throws TokenizeException, MathParseException {
        MathLexer lexer = new MathLexer("123");
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        Assert.assertTrue(expression instanceof NumberAtom);

        NumberAtom atom = (NumberAtom) expression;

        Assert.assertEquals(123., atom.getValue(), 0);
    }

    @Test
    public void testParseNumberV2() throws TokenizeException, MathParseException {
        MathLexer lexer = new MathLexer("123.");
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        Assert.assertTrue(expression instanceof NumberAtom);

        NumberAtom atom = (NumberAtom) expression;

        Assert.assertEquals(123., atom.getValue(), 0);
    }

    @Test
    public void testParseNumberV3() throws TokenizeException, MathParseException {
        MathLexer lexer = new MathLexer("123.111");
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        Assert.assertTrue(expression instanceof NumberAtom);

        NumberAtom atom = (NumberAtom) expression;

        Assert.assertEquals(123.111, atom.getValue(), 0);
    }

    @Test
    public void testParseAdd() throws TokenizeException, MathParseException {
        MathLexer lexer = new MathLexer("12 + 23");
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        Assert.assertTrue(expression instanceof Add);

        Add add = (Add) expression;

        Assert.assertTrue(add.getLeft() instanceof NumberAtom);
        Assert.assertEquals(12., ((NumberAtom) add.getLeft()).getValue(), 0);

        Assert.assertTrue(add.getRight() instanceof NumberAtom);
        Assert.assertEquals(23, ((NumberAtom) add.getRight()).getValue(), 0);
    }

    @Test
    public void testParseMinus() throws TokenizeException, MathParseException {
        MathLexer lexer = new MathLexer("111 -  90");
        List<Lexem> lexems = lexer.tokenize();

        MathParser parser = new MathParser();
        Expression expression = parser.parse(lexems);

        Assert.assertTrue(expression instanceof Minus);

        Minus minus = (Minus) expression;

        Assert.assertTrue(minus.getLeft() instanceof NumberAtom);
        Assert.assertEquals(111, ((NumberAtom) minus.getLeft()).getValue(), 0);

        Assert.assertTrue(minus.getRight() instanceof NumberAtom);
        Assert.assertEquals(90, ((NumberAtom) minus.getRight()).getValue(), 0);
    }
}
