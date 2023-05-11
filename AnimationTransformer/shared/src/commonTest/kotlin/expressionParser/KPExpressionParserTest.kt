package expressionParser

import expressionParser.function.*
import kotlin.test.Test
import kotlin.test.assertEquals

class KPExpressionParserTest {
    private val functions = mapOf(
        "getTextMeasureHeight" to TextMeasureHeightFunction(),
        "getTestThirtyThree" to TestThirtyThreeFunction(),
        "getComputationFortyTwo" to ComputationFortyTwoFunction(),
        "getComputationBlabla" to ComputationBlablaFunction(),
        "getNoParam" to NoParamFunction(),
    )

    private val sut = KPDefaultExpressionParser(functions)

    @Test
    fun testExpressionRawValue42() {
        val expression = "42"
        val result = sut.parseAndEvaluate(expression)
        val expected = 42
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionRawValue123456() {
        val expression = "12345"
        val result = sut.parseAndEvaluate(expression)
        val expected = 12345
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionAddition1() {
        val expression = "55 + 34"
        val result = sut.parseAndEvaluate(expression)
        val expected = 55 + 34
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionAddition2() {
        val expression = "89 + 77 + 567 + 654"
        val result = sut.parseAndEvaluate(expression)
        val expected = 89 + 77 + 567 + 654
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionSubstraction1() {
        val expression = "90 - 45"
        val result = sut.parseAndEvaluate(expression)
        val expected = 90 - 45
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionSubstraction2() {
        val expression = "123 - 77 - 555 - 98"
        val result = sut.parseAndEvaluate(expression)
        val expected = 123 - 77 - 555 - 98
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionMultiplication1() {
        val expression = "23 * 46"
        val result = sut.parseAndEvaluate(expression)
        val expected = 23 * 46
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionMultiplication2() {
        val expression = "123 * 77 * 555 * 98"
        val result = sut.parseAndEvaluate(expression)
        val expected = 123 * 77 * 555 * 98
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionDivition1() {
        val expression = "23 / 46"
        val result = sut.parseAndEvaluate(expression)
        val expected = 23 / 46.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionDivition2() {
        val expression = "123 / 77 / 555 / 98"
        val result = sut.parseAndEvaluate(expression)
        val expected = 123 / 77.0 / 555.0 / 98.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionOperatorPriority1() {
        val expression = "1 + 2 * 3"
        val result = sut.parseAndEvaluate(expression)
        val expected = 1 + 2 * 3
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionOperatorPriority2() {
        val expression = "1 / 2 * 3"
        val result = sut.parseAndEvaluate(expression)
        val expected = 1 / 2.0 * 3
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionOperatorPriority3() {
        val expression = "1 + 2 * 3 + 456"
        val result = sut.parseAndEvaluate(expression)
        val expected = 1 + 2 * 3 + 456
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionOperatorPriority4() {
        val expression = "10-40-30"
        val result = sut.parseAndEvaluate(expression)
        val expected = 10 - 40 - 30
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionOperatorPriority5() {
        val expression = "10+40+30"
        val result = sut.parseAndEvaluate(expression)
        val expected = 10 + 40 + 30
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionParenthesis1() {
        val expression = "(55 + 34) * 3"
        val result = sut.parseAndEvaluate(expression)
        val expected = (55 + 34) * 3
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionParenthesis2() {
        val expression = "(77 - 12) * 5"
        val result = sut.parseAndEvaluate(expression)
        val expected = (77 - 12) * 5
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionParenthesis3() {
        val expression = "(123 - 342) / 3"
        val result = sut.parseAndEvaluate(expression)
        val expected = (123 - 342) / 3
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionParenthesis4() {
        val expression = "(654 - 123) / (3 * 6)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (654.0 - 123.0) / (3.0 * 6.0)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionParenthesis5() {
        val expression = "(42 + 12) * 3 - 3 * 7"
        val result = sut.parseAndEvaluate(expression)
        val expected = (42 + 12) * 3 - 3 * 7
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction1() {
        val expression = "getTextMeasureHeight(23)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (2 * 23)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction2() {
        val expression = "getTestThirtyThree(55)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (55 + 33)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction3() {
        val expression = "getComputationFortyTwo(58, 34)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (58 + 34) * 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction4() {
        val expression = "getComputationBlabla(1, 2, 3)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (1 + 2 + 3) / 10.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction5() {
        val expression = "getComputationBlabla(1, getTestThirtyThree(676), 3)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (1 + (676 + 33) + 3) / 10.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpressionFunction6() {
        val expression = "getNoParam()"
        val result = sut.parseAndEvaluate(expression)
        val expected = 123.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression1() {
        val expression = "getTextMeasureHeight(7)+40+getTextMeasureHeight(8)+100"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 * 2) + 40 + (8 * 2) + 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression2() {
        val expression = "getComputationBlabla(1, getTestThirtyThree(676), 3)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (1 + (676 + 33) + 3) / 10.0
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression3() {
        val expression = "getTextMeasureHeight(7)-40+getTextMeasureHeight(8)-100"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 * 2) - 40 + (8 * 2) - 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression4() {
        val expression = "getTextMeasureHeight(7) * 40 * getTextMeasureHeight(8) * 100"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 * 2) * 40 * (8 * 2) * 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression5() {
        val expression = "getTestThirtyThree(7)+40+getTextMeasureHeight(8)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 + 33) + 40 + (8 * 2)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression6() {
        val expression = "getTestThirtyThree(7)-40-getTextMeasureHeight(8)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 + 33) - 40 - (8 * 2)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression7() {
        val expression = "getTestThirtyThree(7)-40*getTextMeasureHeight(8)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (7 + 33) - 40 * (8 * 2)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression8() {
        val expression = "getTextMeasureHeight(getTestThirtyThree(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = 2 * (2 + 33)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression9() {
        val expression = "getTextMeasureHeight(getTextMeasureHeight(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = 2 * (2 * 2)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression10() {
        val expression = "getTestThirtyThree(getTextMeasureHeight(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = 33 + (2 * 2)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression11() {
        val expression = "getComputationFortyTwo(2, 3)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (2 + 3) * 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression12() {
        val expression = "getComputationFortyTwo(2, getTestThirtyThree(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = (2 + (2 + 33)) * 100
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression13() {
        val expression = "getComputationFortyTwo(2, getTestThirtyThree(2)) + getTestThirtyThree(33) + getTextMeasureHeight(getTextMeasureHeight(10)) + getComputationFortyTwo(getTextMeasureHeight(getTextMeasureHeight(10)), getTestThirtyThree(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = ((2 + (2 + 33)) * 100) + (33 + 33) + (2 * (2 * 10)) + (100 * ((2 * 2 * 10) + (2 + 33)))
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression14() {
        val expression = "getComputationFortyTwo(2, getTestThirtyThree(2)) - getTestThirtyThree(33) - getTextMeasureHeight(getTextMeasureHeight(10)) + getComputationFortyTwo(getTextMeasureHeight(getTextMeasureHeight(10)), getTestThirtyThree(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = ((2 + (2 + 33)) * 100) - (33 + 33) - (2 * (2 * 10)) + (100 * ((2 * 2 * 10) + (2 + 33)))
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression15() {
        val expression = "(getTestThirtyThree(44) + 123) * getComputationFortyTwo(2, getTestThirtyThree(2)) - getTestThirtyThree(33) - getTextMeasureHeight(getTextMeasureHeight(10)) + getComputationFortyTwo(getTextMeasureHeight(getTextMeasureHeight(10)), getTestThirtyThree(2))"
        val result = sut.parseAndEvaluate(expression)
        val expected = ((44 + 33) + 123) * ((2 + (2 + 33)) * 100) - (33 + 33) - (2 * (2 * 10)) + (100 * ((2 * 2 * 10) + (2 + 33)))
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression16() {
        val expression = "getComputationFortyTwo(2,getTestThirtyThree(2))+(getTestThirtyThree(2)/2)*(getComputationBlabla(1,2,3)-543+12345)"
        val result = sut.parseAndEvaluate(expression)
        val expected = (2 + (2 + 33)) * 100 + ((2 + 33) / 2.0) * (((1 + 2 + 3) / 10.0) - 543 + 12345)
        assertEquals(expected.toDouble(), result)
    }

    @Test
    fun testExpression17() {
        val expression = "getNoParam() * 2 + (getTestThirtyThree(getNoParam()) + 321)"
        val result = sut.parseAndEvaluate(expression)
        val expected = 123.0 * 2 + ((123.0 + 33) + 321)
        assertEquals(expected.toDouble(), result)
    }
}

