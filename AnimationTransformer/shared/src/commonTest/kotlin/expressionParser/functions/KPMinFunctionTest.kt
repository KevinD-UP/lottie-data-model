package expressionParser.functions

import expressionParser.functions.common.KPMinFunction
import kotlin.test.Test
import kotlin.test.assertEquals


class KPMinFunctionTest {
    @Test
    fun testMinInvalidArgs1() {
        val sut = KPMinFunction()
        val results = sut.execute(emptyList())
        assertEquals(0.0, results)
    }

    @Test
    fun testMinInvalidArgs2() {
        val sut = KPMinFunction()
        val args = listOf(10.0, 20.0, 30.0)
        val results = sut.execute(args)
        assertEquals(0.0, results)
    }

    @Test
    fun testMin1() {
        val sut = KPMinFunction()
        val args = listOf(10.0, 20.0)
        val results = sut.execute(args)
        assertEquals(10.0, results)
    }

    @Test
    fun testMin2() {
        val sut = KPMinFunction()
        val args = listOf(40.0, 30.0)
        val results = sut.execute(args)
        assertEquals(30.0, results)
    }

    @Test
    fun testMin3() {
        val sut = KPMinFunction()
        val args = listOf(-10.0, -30.0)
        val results = sut.execute(args)
        assertEquals(-30.0, results)
    }

    @Test
    fun testMin4() {
        val sut = KPMinFunction()
        val args = listOf(-66.0, 30.0)
        val results = sut.execute(args)
        assertEquals(-66.0, results)
    }
}