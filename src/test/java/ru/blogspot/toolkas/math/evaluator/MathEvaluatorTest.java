package ru.blogspot.toolkas.math.evaluator;

import org.junit.Assert;
import org.junit.Test;

public class MathEvaluatorTest {
    private final MathEvaluator evaluator = new MathEvaluator();

    @Test
    public void testSum() throws Exception {
        Assert.assertEquals(10, evaluator.eval("5.3 + 4.7"), 0);
    }

    @Test
    public void testMinus() throws Exception {
        Assert.assertEquals(0.6, evaluator.eval("5.3 - 4.7"), 0.001);
    }

    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(25, evaluator.eval("5 * 5"), 0);
    }

    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(5, evaluator.eval("60 / 12"), 0);
    }

    @Test
    public void testExpression() throws Exception {
        Assert.assertEquals(-7.2, evaluator.eval("1 + 2.3 * (8 - 90) /23"), 0.001);
    }
}
