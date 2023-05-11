package expressionParser.functions

import kotlin.test.Test
import kotlin.test.assertEquals

class KPMaxFunctionTest {
    @Test
    fun testMaxInvalidArgs1() {
        val sut = KPMaxFunction()
        val results = sut.execute(emptyList())
        assertEquals(0.0, results)
    }

    @Test
    fun testMaxInvalidArgs2() {
        val sut = KPMaxFunction()
        val args = listOf(10.0, 20.0, 30.0)
        val results = sut.execute(args)
        assertEquals(0.0, results)
    }

    @Test
    fun testMax1() {
        val sut = KPMaxFunction()
        val args = listOf(10.0, 20.0)
        val results = sut.execute(args)
        assertEquals(20.0, results)
    }

    @Test
    fun testMax2() {
        val sut = KPMaxFunction()
        val args = listOf(40.0, 30.0)
        val results = sut.execute(args)
        assertEquals(40.0, results)
    }

    @Test
    fun testMax3() {
        val sut = KPMaxFunction()
        val args = listOf(-10.0, -30.0)
        val results = sut.execute(args)
        assertEquals(-10.0, results)
    }

    @Test
    fun testMax4() {
        val sut = KPMaxFunction()
        val args = listOf(-66.0, 30.0)
        val results = sut.execute(args)
        assertEquals(30.0, results)
    }
}