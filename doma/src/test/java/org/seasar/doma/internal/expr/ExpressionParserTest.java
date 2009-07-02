/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.expr;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.seasar.doma.internal.expr.node.ExpressionNode;
import org.seasar.doma.message.MessageCode;

/**
 * @author taedium
 * 
 */
public class ExpressionParserTest extends TestCase {

    public void testTrue() throws Exception {
        ExpressionParser parser = new ExpressionParser("true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testFalse() throws Exception {
        ExpressionParser parser = new ExpressionParser("false");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testNot() throws Exception {
        ExpressionParser parser = new ExpressionParser("!true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testNot2() throws Exception {
        ExpressionParser parser = new ExpressionParser("!false");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testAnd() throws Exception {
        ExpressionParser parser = new ExpressionParser("!false && !false");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testAnd2() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "(true || false) && (true || false)");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testAnd3() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "(true || false ) && !( true || false)");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testAnd4() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "(true || false ) && true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testOr() throws Exception {
        ExpressionParser parser = new ExpressionParser("false || true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testOr2() throws Exception {
        ExpressionParser parser = new ExpressionParser("false || false");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testOr44() throws Exception {
        ExpressionParser parser = new ExpressionParser("false || true && false");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testOr3() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "true && true || true && true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testOr4() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "true && false || true && true");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNoParamMethod() throws Exception {
        ExpressionParser parser = new ExpressionParser("hoge.length");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", "aaa");
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals(new Integer(3), evaluationResult.getValue());
    }

    public void testMethod() throws Exception {
        ExpressionParser parser = new ExpressionParser("hoge.length()");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", "aaa");
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals(new Integer(3), evaluationResult.getValue());
    }

    public void testMethod2() throws Exception {
        ExpressionParser parser = new ExpressionParser("hoge.substring(2, 4)");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", "abcdef");
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals("cd", evaluationResult.getValue());
    }

    public void testMethod3() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "hoge.foo.substring(2, 4)");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", new Hoge());
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals("cd", evaluationResult.getValue());
    }

    public void testMethod4() throws Exception {
        ExpressionParser parser = new ExpressionParser("hoge.bar(2, 4).length");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", new Hoge());
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals(new Integer(2), evaluationResult.getValue());
    }

    public void testMethod5() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "hoge.bar(hoge.bar(2, 4).length, 4).length");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.add("hoge", new Hoge());
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals(new Integer(2), evaluationResult.getValue());
    }

    public void testNew() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new java.lang.Integer(10)");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertEquals(new Integer(10), evaluationResult.getValue());
    }

    public void testEq() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 == 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotEq() throws Exception {
        ExpressionParser parser = new ExpressionParser("11 == 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testEq_domain() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new org.seasar.doma.domain.IntegerDomain(new java.lang.Integer(10)) == 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotEq_domain() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new org.seasar.doma.domain.IntegerDomain(new java.lang.Integer(11)) == 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testEq_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null == null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("null == 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("1 == null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testNe() throws Exception {
        ExpressionParser parser = new ExpressionParser("1 != 2");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotNe() throws Exception {
        ExpressionParser parser = new ExpressionParser("11 != 11");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testNe_domain() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new org.seasar.doma.domain.IntegerDomain(new java.lang.Integer(10)) != 11");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotNe_domain() throws Exception {
        ExpressionParser parser = new ExpressionParser(
                "new org.seasar.doma.domain.IntegerDomain(new java.lang.Integer(10)) != 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testNe_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null != null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("null != 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("1 != null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testGe() throws Exception {
        ExpressionParser parser = new ExpressionParser("11 >= 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
        parser = new ExpressionParser("10 >= 10");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotGe() throws Exception {
        ExpressionParser parser = new ExpressionParser("9 >= 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testGe_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null >= null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("null >= 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("1 >= null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }
    }

    public void testLe() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 <= 11");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
        parser = new ExpressionParser("10 <= 10");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotLe() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 <= 9");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testLe_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null <= null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();

        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("null <= 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();

        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("1 <= null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();

        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }
    }

    public void testGt() throws Exception {
        ExpressionParser parser = new ExpressionParser("11 > 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());
    }

    public void testNotGt() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 > 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("9 > 10");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testGt_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null > null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("null > 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();

        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("1 > null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();

        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }
    }

    public void testLt() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 < 11");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertTrue(evaluationResult.getBooleanValue());

    }

    public void testNotLt() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 < 10");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());

        parser = new ExpressionParser("10 < 9");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        evaluationResult = evaluator.evaluate(expression);
        assertFalse(evaluationResult.getBooleanValue());
    }

    public void testLt_null() throws Exception {
        ExpressionParser parser = new ExpressionParser("null < null");
        ExpressionNode expression = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("null < 1");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }

        parser = new ExpressionParser("1 < null");
        expression = parser.parse();
        evaluator = new ExpressionEvaluator();
        try {
            evaluator.evaluate(expression);
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3009, expected.getMessageCode());
        }
    }

    public void testUnsupportedToken() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 @ 5");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3011, expected.getMessageCode());
        }
    }

    public void testIllegalNumberLiteral() throws Exception {
        ExpressionParser parser = new ExpressionParser("2.length");
        try {
            parser.parse();
            fail();
        } catch (ExpressionException expected) {
            assertEquals(MessageCode.DOMA3012, expected.getMessageCode());
        }
    }

    public void testInt() throws Exception {
        ExpressionParser parser = new ExpressionParser("2");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Integer(2), result.getValue());

        parser = new ExpressionParser("+2");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Integer(2), result.getValue());

        parser = new ExpressionParser("-2");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Integer(-2), result.getValue());
    }

    public void testLong() throws Exception {
        ExpressionParser parser = new ExpressionParser("2L");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Long(2), result.getValue());

        parser = new ExpressionParser("+2L");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Long(2), result.getValue());

        parser = new ExpressionParser("-2L");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Long(-2), result.getValue());
    }

    public void testFloat() throws Exception {
        ExpressionParser parser = new ExpressionParser("2.5F");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Float(2.5), result.getValue());

        parser = new ExpressionParser("+2.5F");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Float(2.5), result.getValue());

        parser = new ExpressionParser("-2.5F");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Float(-2.5), result.getValue());
    }

    public void testDouble() throws Exception {
        ExpressionParser parser = new ExpressionParser("2.5D");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Double(2.5), result.getValue());

        parser = new ExpressionParser("+2.5D");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Double(2.5), result.getValue());

        parser = new ExpressionParser("-2.5D");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new Double(-2.5), result.getValue());
    }

    public void testBigDecimal() throws Exception {
        ExpressionParser parser = new ExpressionParser("2.5B");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(2.5), result.getValue());

        parser = new ExpressionParser("+2.5B");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(2.5), result.getValue());

        parser = new ExpressionParser("-2.5B");
        node = parser.parse();
        evaluator = new ExpressionEvaluator();
        result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(-2.5), result.getValue());
    }

    public void testChar() throws Exception {
        ExpressionParser parser = new ExpressionParser("'a'");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Character('a'), result.getValue());
    }

    public void testAdd() throws Exception {
        ExpressionParser parser = new ExpressionParser("1 + 1B");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(2), result.getValue());
    }

    public void testSubtract() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 - 2B");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(8), result.getValue());
    }

    public void testMultiply() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 * 2B");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(20), result.getValue());
    }

    public void testDivide() throws Exception {
        ExpressionParser parser = new ExpressionParser("10 / 2B");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new BigDecimal(5), result.getValue());
    }

    public void testArithmeticOperators() throws Exception {
        ExpressionParser parser = new ExpressionParser("5 + 3 * 4 - 9 / 3");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Integer(14), result.getValue());
    }

    public void testArithmeticOperators2() throws Exception {
        ExpressionParser parser = new ExpressionParser("5+3*4-9/3");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Integer(14), result.getValue());
    }

    public void testArithmeticOperators3() throws Exception {
        ExpressionParser parser = new ExpressionParser("1+-2*+2");
        ExpressionNode node = parser.parse();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        EvaluationResult result = evaluator.evaluate(node);
        assertEquals(new Integer(-3), result.getValue());
    }

    public class Hoge {

        private String foo = "abcdef";

        public String foo() {
            return foo;
        }

        public String bar(int start, int end) {
            return foo.substring(start, end);
        }

    }

}