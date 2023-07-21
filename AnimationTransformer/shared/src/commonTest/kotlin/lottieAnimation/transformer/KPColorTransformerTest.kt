package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.*
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPColorTransformer
import transformer.hexaStringToRGBAFloatList
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KPColorTransformerTest {
    @Test
    fun testAlgiersFord() {
        val colors = colorsFrom("algiers")
        val sut = setupSUT("ALGIERS-FORD", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "gradientText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "gradientText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val shapeLayer2 = sut.layers[2]
        val expectedLayer2Colors = expectedGradientColors(colors, "gradientLeft", "gradientRight")
        assertShapeLayerGradientColors(shapeLayer2, expectedLayer2Colors)
    }

    @Test
    fun testAlgiersPeugeot() {
        val colors = colorsFrom("algiers")
        val sut = setupSUT("ALGIERS-PEUGEOT", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "slideSplitTopText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "slideSplitBottomText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2SolidColor = expectedSolidColorHexa(colors, "slideSplitTopBackground")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2SolidColor)
        val solidLayer3 = sut.layers[3]
        val expectedLayer3SolidColor = expectedSolidColorHexa(colors, "slideSplitBottomBackground")
        assertSolidLayerSolidColor(solidLayer3, expectedLayer3SolidColor)
    }

    @Test
    fun testAlgiersPlane() {
        val colors = colorsFrom("algiers")
        val sut = setupSUT("ALGIERS-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "subtitleTopText")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val textLayer4 = sut.layers[4]
        val expectedLayer4FillColor = expectedColors(colors, "subtitleBottomText")
        assertTextLayerColor(textLayer4, expectedLayer4FillColor)
        val shapeLayer5 = sut.layers[5]
        val expectedLayer5Color = expectedColors(colors, "subtitleBottomBackground")
        assertShapeLayerFillColor(shapeLayer5, expectedLayer5Color)
        val solidLayer6 = sut.layers[6]
        val expectedLayer6Color = expectedSolidColorHexa(colors, "titleBackground")
        assertSolidLayerSolidColor(solidLayer6, expectedLayer6Color)
    }

    @Test
    fun testAlgiersSimca() {
        val colors = colorsFrom("algiers")
        val sut = setupSUT("ALGIERS-SIMCA", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "gradientText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val shapeLayer1 = sut.layers[1]
        val expectedLayer1Colors = expectedGradientColors(colors, "gradientLeft", "gradientRight")
        assertShapeLayerGradientColors(shapeLayer1, expectedLayer1Colors)
    }

    @Test
    fun testBaliFord() {
        val colors = colorsFrom("bali")
        val sut = setupSUT("BALI-FORD", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val shapeLayer1 = sut.layers[1]
        val expectedLayer1Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer1, expectedLayer1Color)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedSolidColorHexa(colors, "background")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2Color)
    }

    @Test
    fun testBaliPeugeot() {
        val colors = colorsFrom("bali")
        val sut = setupSUT("BALI-PEUGEOT", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val shapeLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer2, expectedLayer2Color)
        val solidLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedSolidColorHexa(colors, "background")
        assertSolidLayerSolidColor(solidLayer3, expectedLayer3Color)
    }

    @Test
    fun testBaliPlane() {
        val colors = colorsFrom("bali")
        val sut = setupSUT("BALI-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer6 = sut.layers[6]
        val expectedLayer6FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer6, expectedLayer6FillColor)
        val textLayer7 = sut.layers[7]
        val expectedLayer7FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer7, expectedLayer7FillColor)
        val shapeLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer2, expectedLayer2Color)
        val shapeLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer3, expectedLayer3Color)
        val shapeLayer4 = sut.layers[4]
        val expectedLayer4Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer4, expectedLayer4Color)
        val shapeLayer5 = sut.layers[5]
        val expectedLayer5Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer5, expectedLayer5Color)
        val shapeLayer8 = sut.layers[8]
        val expectedLayer8Color = expectedColors(colors, "background")
        assertShapeLayerFillColor(shapeLayer8, expectedLayer8Color)
        val solidLayer9 = sut.layers[9]
        val expectedLayer9Color = expectedSolidColorHexa(colors, "titleBackground")
        assertSolidLayerSolidColor(solidLayer9, expectedLayer9Color)
    }

    @Test
    fun testBerlinFord() {
        val colors = colorsFrom("berlin")
        val sut = setupSUT("BERLIN-FORD", colors)
        val shapeLayer1 = sut.layers[1]
        val expectedLayer1Colors = expectedGradientColors(colors, "gradientTop", "gradientBottom")
        assertShapeLayerGradientColors(shapeLayer1, expectedLayer1Colors)
        val shapeLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedColors(colors, "slideBackground")
        assertShapeLayerFillColor(shapeLayer2, expectedLayer2Color)
    }

    @Test
    fun testBerlinPeugeot() {
        val colors = colorsFrom("berlin")
        val sut = setupSUT("BERLIN-PEUGEOT", colors)
        val shapeLayer0 = sut.layers[0]
        val expectedLayer0Colors = expectedGradientColors(colors, "gradientTop", "gradientBottom")
        assertShapeLayerGradientColors(shapeLayer0, expectedLayer0Colors)
        val shapeLayer2 = sut.layers[2]
        val expectedLayer2Colors = expectedGradientColors(colors, "gradientTop", "gradientBottom")
        assertShapeLayerGradientColors(shapeLayer2, expectedLayer2Colors)
        val shapeLayer4 = sut.layers[4]
        val expectedLayer4Colors = expectedGradientColors(colors, "gradientTop", "gradientBottom")
        assertShapeLayerGradientColors(shapeLayer4, expectedLayer4Colors)
        val shapeLayer5 = sut.layers[5]
        val expectedLayer5Color = expectedColors(colors, "slideBackground")
        assertShapeLayerFillColor(shapeLayer5, expectedLayer5Color)
    }

    @Test
    fun testBerlinPlane() {
        val colors = colorsFrom("berlin")
        val sut = setupSUT("BERLIN-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val shapeLayer3 = sut.layers[3]
        val expectedLayer3Colors = expectedGradientColors(colors, "titleGradientTop", "titleGradientBottom")
        assertShapeLayerGradientColors(shapeLayer3, expectedLayer3Colors)
        val shapeLayer10 = sut.layers[10]
        val expectedLayer10Color = expectedColors(colors, "titleBackground")
        assertShapeLayerFillColor(shapeLayer10, expectedLayer10Color)
    }

    @Test
    fun testGenevaFord() {
        val colors = colorsFrom("geneva")
        val sut = setupSUT("GENEVA-FORD", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val shapeLayer1 = sut.layers[1]
        val expectedLayer1Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer1, expectedLayer1Color)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedSolidColorHexa(colors, "background")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2Color)
    }

    @Test
    fun testGenevaPeugeot() {
        val colors = colorsFrom("geneva")
        val sut = setupSUT("GENEVA-PEUGEOT", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val shapeLayer1 = sut.layers[1]
        val expectedLayer1Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer1, expectedLayer1Color)
        val solidLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedSolidColorHexa(colors, "background")
        assertSolidLayerSolidColor(solidLayer3, expectedLayer3Color)
    }

    @Test
    fun testGenevaPlane() {
        val colors = colorsFrom("geneva")
        val sut = setupSUT("GENEVA-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val textLayer4 = sut.layers[4]
        val expectedLayer4FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer4, expectedLayer4FillColor)
        val shapeLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedColors(colors, "shape")
        assertShapeLayerFillColor(shapeLayer3, expectedLayer3Color)
        val solidLayer5 = sut.layers[5]
        val expectedLayer5Color = expectedSolidColorHexa(colors, "titleBackground")
        assertSolidLayerSolidColor(solidLayer5, expectedLayer5Color)
    }

    @Test
    fun testLosAngelesFord() {
        val colors = colorsFrom("los_angeles")
        val sut = setupSUT("LOS_ANGELES-FORD", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "slideText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "slideText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedSolidColorHexa(colors, "slideBackground")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2Color)
    }

    @Test
    fun testLosAngelesPeugeot() {
        val colors = colorsFrom("los_angeles")
        val sut = setupSUT("LOS_ANGELES-PEUGEOT", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "slideText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "slideTextStrong")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedSolidColorHexa(colors, "slideBackground")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2Color)
    }

    @Test
    fun testLosAngelesPlane() {
        val colors = colorsFrom("los_angeles")
        val sut = setupSUT("LOS_ANGELES-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "subtitleTopText")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val textLayer3 = sut.layers[3]
        val expectedLayer3FillColor = expectedColors(colors, "subtitleBottomText")
        assertTextLayerColor(textLayer3, expectedLayer3FillColor)
        val solidLayer4 = sut.layers[4]
        val expectedLayer4Color = expectedSolidColorHexa(colors, "subtitleBackground")
        assertSolidLayerSolidColor(solidLayer4, expectedLayer4Color)
        val solidLayer5 = sut.layers[5]
        val expectedLayer5Color = expectedSolidColorHexa(colors, "titleBackground")
        assertSolidLayerSolidColor(solidLayer5, expectedLayer5Color)
    }

    @Test
    fun testLosAngelesSimca() {
        val colors = colorsFrom("los_angeles")
        val sut = setupSUT("LOS_ANGELES-SIMCA", colors)
        val solidLayer1 = sut.layers[1]
        val expectedLayer1Color = expectedSolidColorHexa(colors, "slideBackground")
        assertSolidLayerSolidColor(solidLayer1, expectedLayer1Color)
    }

    @Test
    fun testSeattleFord() {
        val colors = colorsFrom("seattle")
        val sut = setupSUT("SEATTLE-FORD", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "slideFullText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val solidLayer1 = sut.layers[1]
        val expectedLayer1Color = expectedSolidColorHexa(colors, "slideFullBackground")
        assertSolidLayerSolidColor(solidLayer1, expectedLayer1Color)
    }

    @Test
    fun testSeattlePeugeot() {
        val colors = colorsFrom("seattle")
        val sut = setupSUT("SEATTLE-PEUGEOT", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "slideSplitTopText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "slideSplitBottomText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val solidLayer2 = sut.layers[2]
        val expectedLayer2Color = expectedSolidColorHexa(colors, "slideSplitTopBackground")
        assertSolidLayerSolidColor(solidLayer2, expectedLayer2Color)
        val solidLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedSolidColorHexa(colors, "slideSplitBottomBackground")
        assertSolidLayerSolidColor(solidLayer3, expectedLayer3Color)
    }

    @Test
    fun testSeattlePlane() {
        val colors = colorsFrom("seattle")
        val sut = setupSUT("SEATTLE-PLANE", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "titleText")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer3 = sut.layers[3]
        val expectedLayer3FillColor = expectedColors(colors, "subtitleTopText")
        assertTextLayerColor(textLayer3, expectedLayer3FillColor)
        val textLayer6 = sut.layers[6]
        val expectedLayer6FillColor = expectedColors(colors, "subtitleBottomText")
        assertTextLayerColor(textLayer6, expectedLayer6FillColor)
        val shapeLayer4 = sut.layers[4]
        val expectedLayer4Color = expectedColors(colors, "subtitleTopBackground")
        assertShapeLayerFillColor(shapeLayer4, expectedLayer4Color)
        val shapeLayer7 = sut.layers[7]
        val expectedLayer7Color = expectedColors(colors, "subtitleBottomBackground")
        assertShapeLayerFillColor(shapeLayer7, expectedLayer7Color)
        val solidLayer8 = sut.layers[8]
        val expectedLayer8Color = expectedSolidColorHexa(colors, "titleBackground")
        assertSolidLayerSolidColor(solidLayer8, expectedLayer8Color)
    }

    @Test
    fun testParisButterfly() {
        val colors = colorsFrom("paris")
        val sut = setupSUT("PARIS-BUTTERFLY", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val shapeLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedColors(colors, "background")
        assertShapeLayerFillColor(shapeLayer3, expectedLayer3Color)
        // TODO: need to test layer rule ind 1 fillColorKey
    }

    @Test
    fun testParisFox() {
        val colors = colorsFrom("paris")
        val sut = setupSUT("PARIS-FOX", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val textLayer3 = sut.layers[3]
        val expectedLayer3FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer3, expectedLayer3FillColor)
        val textLayer4 = sut.layers[4]
        val expectedLayer4FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer4, expectedLayer4FillColor)
        val textLayer5 = sut.layers[5]
        val expectedLayer5FillColor = expectedColors(colors, "background")
        assertTextLayerColor(textLayer5, expectedLayer5FillColor)
        val textLayer6 = sut.layers[6]
        val expectedLayer6FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer6, expectedLayer6FillColor)
        val shapeLayer7 = sut.layers[7]
        // TODO: need to test rule with ind 8
        //assertShapeLayerFillColor(shapeLayer7, expectedLayer7Color)
    }

    @Test
    fun testParisOwl() {
        val colors = colorsFrom("paris")
        val sut = setupSUT("PARIS-OWL", colors)
        val textLayer0 = sut.layers[0]
        val expectedLayer0FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer0, expectedLayer0FillColor)
        val textLayer1 = sut.layers[1]
        val expectedLayer1FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer1, expectedLayer1FillColor)
        val textLayer2 = sut.layers[2]
        val expectedLayer2FillColor = expectedColors(colors, "text")
        assertTextLayerColor(textLayer2, expectedLayer2FillColor)
        val shapeLayer3 = sut.layers[3]
        val expectedLayer3Color = expectedColors(colors, "background")
        assertShapeLayerFillColor(shapeLayer3, expectedLayer3Color)
    }

}

fun KPColorTransformerTest.expectedGradientColors(colors: Map<String, String>, gradientFromKey: String, gradientToKey: String): List<Float> {
    val color1 = colors[gradientFromKey]
    assertNotNull(color1)
    val color2 = colors[gradientToKey]
    assertNotNull(color2)
    val expectedColors1 = hexaStringToRGBAFloatList(color1)
    val expectedColors2 = hexaStringToRGBAFloatList(color2)
    assertNotNull(expectedColors1)
    assertNotNull(expectedColors2)
    return listOf(0.0f) + expectedColors1 + listOf(1.0f) + expectedColors2
}

fun KPColorTransformerTest.assertShapeLayerGradientColors(layer: KPLayer, expectedValue: List<Float>) {
    assertTrue(layer is KPShapeLayer)
    val shapeGroup = layer.shapes?.firstOrNull()
    assertTrue(shapeGroup is KPShapeGroup)
    val gradientFill = shapeGroup.it.firstOrNull { it is KPShapeGFill }
    assertTrue(gradientFill is KPShapeGFill)
    val gradientColors = gradientFill.g.k.k
    assertTrue(gradientColors is KPMultiDimensionalList)
    val gradient0 = gradientColors.values[0]
    val gradient1 = gradientColors.values[1]
    val gradient2 = gradientColors.values[2]
    val gradient3 = gradientColors.values[3]
    val gradient4 = gradientColors.values[4]
    val gradient5 = gradientColors.values[5]
    val gradient6 = gradientColors.values[6]
    val gradient7 = gradientColors.values[7]
    assertTrue(gradient0 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient1 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient2 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient3 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient4 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient5 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient6 is KPMultiDimensionalNodePrimitive)
    assertTrue(gradient7 is KPMultiDimensionalNodePrimitive)
    assertEquals(expectedValue[0], gradient0.value.floatOrNull)
    assertEquals(expectedValue[1], gradient1.value.floatOrNull)
    assertEquals(expectedValue[2], gradient2.value.floatOrNull)
    assertEquals(expectedValue[3], gradient3.value.floatOrNull)
    assertEquals(expectedValue[4], gradient4.value.floatOrNull)
    assertEquals(expectedValue[5], gradient5.value.floatOrNull)
    assertEquals(expectedValue[6], gradient6.value.floatOrNull)
    assertEquals(expectedValue[7], gradient7.value.floatOrNull)
}

fun KPColorTransformerTest.expectedColors(colors: Map<String, String>, colorKey: String): List<Float> {
    val color1 = colors[colorKey]
    assertNotNull(color1)
    val expectedColors1 = hexaStringToRGBAFloatList(color1)
    assertNotNull(expectedColors1)
    return expectedColors1
}

fun KPColorTransformerTest.expectedSolidColorHexa(colors: Map<String, String>, solidColorKey: String): String {
    val color1 = colors[solidColorKey]
    assertNotNull(color1)
    return color1
}

fun KPColorTransformerTest.assertTextLayerColor(layer: KPLayer, expectedValue: List<Float>) {
    assertTrue(layer is KPTextLayer)
    val k = layer.t.d.k.firstOrNull()
    val colorList = k?.s?.fc
    assertNotNull(colorList)
    assertEquals(expectedValue[0], colorList[0].jsonPrimitive.floatOrNull)
    assertEquals(expectedValue[1], colorList[1].jsonPrimitive.floatOrNull)
    assertEquals(expectedValue[2], colorList[2].jsonPrimitive.floatOrNull)
}

fun KPColorTransformerTest.assertSolidLayerSolidColor(layer: KPLayer, expectedValue: String) {
    assertTrue(layer is KPSolidLayer)
    assertEquals(expectedValue, layer.sc)
}

fun KPColorTransformerTest.assertShapeLayerOpacity(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val o = layer.ks.o
    assertTrue(o is KPMultiDimensionalSimple)
    val animated = o.a?.int == 1
    if (!animated) {
        val k = o.k
        assertTrue(k is KPMultiDimensionalPrimitive)
        assertEquals(expectedValue, k.value.doubleOrNull)
    } else {
        val k = o.k
        assertTrue(k is KPMultiDimensionalList)
        k.values.forEach {
            assertTrue(it is KPMultiDimensionNodeObject)
           // assertEquals(expectedValue, it.s)
        }
    }
}

private fun KPColorTransformerTest.assertShapeLayerStrokeColor(layer: KPLayer, expectedValue: List<Float>) {
    assertTrue(layer is KPShapeLayer)
    val shapes = layer.shapes?.filter { it.ty == "st" }
    assertNotNull(shapes)
    shapes.forEach {
        assertTrue(it is KPShapeStroke)
        val colors = it.c?.k
        assertNotNull(colors)
        when (colors) {
            is KPMultiDimensionalList -> {
                when (colors.values.firstOrNull()) {
                    is KPMultiDimensionalNodePrimitive -> {
                        assertTrue(colors.values[0] is KPMultiDimensionalNodePrimitive)
                        assertTrue(colors.values[1] is KPMultiDimensionalNodePrimitive)
                        assertTrue(colors.values[2] is KPMultiDimensionalNodePrimitive)
                        val r = colors.values[0] as KPMultiDimensionalNodePrimitive
                        val g = colors.values[1] as KPMultiDimensionalNodePrimitive
                        val b = colors.values[2] as KPMultiDimensionalNodePrimitive
                        val rColor = r.value.floatOrNull
                        val gColor = g.value.floatOrNull
                        val bColor = b.value.floatOrNull
                        assertEquals(expectedValue[0], rColor)
                        assertEquals(expectedValue[1], gColor)
                        assertEquals(expectedValue[2], bColor)
                    }
                    else -> { }
                }
            }
            else -> { }
        }
    }
}

private fun KPColorTransformerTest.assertShapeLayerFillColor(layer: KPLayer, expectedValue: List<Float>) {
    assertTrue(layer is KPShapeLayer)
    val shapes = layer.shapes?.filter { it.ty == "gr" }
    assertNotNull(shapes)
    shapes.forEach { shape ->
        assertTrue(shape is KPShapeGroup)
        val itShapes = shape.it.filter { it.ty == "fl" }
        itShapes.forEach {
            assertTrue(it is KPShapeFill)
            val colors = it.c.k
            assertTrue(colors is KPMultiDimensionalList)
            val rColor = colors.values[0]
            val gColor = colors.values[1]
            val bColor = colors.values[2]
            assertTrue(rColor is KPMultiDimensionalNodePrimitive)
            assertTrue(gColor is KPMultiDimensionalNodePrimitive)
            assertTrue(bColor is KPMultiDimensionalNodePrimitive)
            assertEquals(expectedValue[0], rColor.value.floatOrNull)
            assertEquals(expectedValue[1], gColor.value.floatOrNull)
            assertEquals(expectedValue[2], bColor.value.floatOrNull)
        }
    }

}

private fun KPColorTransformerTest.colorSetBTS(): Map<String, Map<String, String>> {
    fun getRandomHexColor(): String {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        return "#${red.toString(16).padStart(2, '0')}${green.toString(16).padStart(2, '0')}${blue.toString(16).padStart(2, '0')}".toUpperCase()
    }

    val bogotaColors = mapOf(
        "coverTextOpacity" to "0.50",
        "shapeShadow" to getRandomHexColor(),
        "negativeBarBottom" to getRandomHexColor(),
        "coverText" to getRandomHexColor(),
        "backgroundOpacity" to "1.00",
        "titleBlinking" to getRandomHexColor(),
        "source" to getRandomHexColor(),
        "title" to getRandomHexColor(),
        "positiveBarTop" to getRandomHexColor(),
        "positiveBarBottom" to getRandomHexColor(),
        "shapeNeutral" to getRandomHexColor(),
        "cta" to getRandomHexColor(),
        "negativeBarTop" to getRandomHexColor(),
        "ctaTitle" to getRandomHexColor(),
        "transparentBackground" to getRandomHexColor(),
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "backgroundBarOpacity" to "0.20",
        "textShadow" to getRandomHexColor(),
        "figure" to getRandomHexColor(),
        "shapeCosmeticOpacity" to "0.70",
        "titleBottom" to getRandomHexColor(),
        "shapeCosmetic" to getRandomHexColor(),
        "chartEmpty" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "shapeSecondary" to getRandomHexColor(),
        "textShadowOpacity" to "0.70",
        "chartFill" to getRandomHexColor(),
        "shapeMain" to getRandomHexColor(),
        "sourceNeutral" to getRandomHexColor(),
        "figureBlink" to getRandomHexColor(),
        "shapeShadowOpacity" to "0.70",
        "ctaTitleBlinking" to getRandomHexColor(),
    )
    val berlinColors = mapOf(
        "gradientTop" to getRandomHexColor(),
        "neutralGradientTop" to getRandomHexColor(),
        "titleGradientTop" to getRandomHexColor(),
        "textBackgroundOpacity" to "1.00",
        "shapeSecond" to getRandomHexColor(),
        "neutralGradientBottom" to getRandomHexColor(),
        "shapeNeutral" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "titleGradientBottom" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.80",
        "slideBackground" to getRandomHexColor(),
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "textBackground" to getRandomHexColor(),
        "gradientBottom" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "shapeMain" to getRandomHexColor(),
        "titleBackground" to getRandomHexColor(),
    )
    val genevaColors = mapOf(
        "shape" to getRandomHexColor(),
        "shadow" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "titleText" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.50",
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "shadowOpacity" to "0.70",
        "titleBackground" to getRandomHexColor(),
    )
    val losAngelesColors = mapOf(
        "shadow" to getRandomHexColor(),
        "slideTextStrong" to getRandomHexColor(),
        "subtitleBottomText" to getRandomHexColor(),
        "subtitleTopText" to getRandomHexColor(),
        "shadowOpacity" to "0.50",
        "background" to getRandomHexColor(),
        "titleText" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.70",
        "slideBackground" to getRandomHexColor(),
        "floatingBackground" to getRandomHexColor(),
        "slideText" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "maskGradientBottom" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "floatingText" to getRandomHexColor(),
        "subtitleBackground" to getRandomHexColor(),
        "titleBackground" to getRandomHexColor(),
    )
    val seattleColors = mapOf(
        "subtitleBottomBackground" to getRandomHexColor(),
        "shape" to getRandomHexColor(),
        "slideFullBackground" to getRandomHexColor(),
        "slideSplitBottomText" to getRandomHexColor(),
        "subtitleBottomText" to getRandomHexColor(),
        "slideSplitBottomBackground" to getRandomHexColor(),
        "subtitleTopText" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "slideSplitTopText" to getRandomHexColor(),
        "slideFullText" to getRandomHexColor(),
        "titleText" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.80",
        "floatingBackground" to getRandomHexColor(),
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "slideSplitTopBackground" to getRandomHexColor(),
        "floatingText" to getRandomHexColor(),
        "subtitleTopBackground" to getRandomHexColor(),
        "titleBackground" to getRandomHexColor(),
    )
    val baliColors = mapOf(
        "shape" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "titleText" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.70",
        "backgroundOpacity" to "0.50",
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "titleBackground" to getRandomHexColor(),
    )

    val algiersColors = mapOf(
        "subtitleBottomBackground" to getRandomHexColor(),
        "slideSplitBottomText" to getRandomHexColor(),
        "subtitleBottomText" to getRandomHexColor(),
        "gradientRight" to getRandomHexColor(),
        "slideSplitBottomBackground" to getRandomHexColor(),
        "subtitleTopText" to getRandomHexColor(),
        "gradientText" to getRandomHexColor(),
        "background" to getRandomHexColor(),
        "slideSplitTopText" to getRandomHexColor(),
        "titleText" to getRandomHexColor(),
        "titleBackgroundOpacity" to "0.70",
        "maskGradientBottom" to getRandomHexColor(),
        "maskGradientTop" to getRandomHexColor(),
        "text" to getRandomHexColor(),
        "gradientLeft" to getRandomHexColor(),
        "slideSplitTopBackground" to getRandomHexColor(),
        "subtitleOpacity" to "0.80",
        "subtitleTopBackground" to getRandomHexColor(),
        "titleBackground" to getRandomHexColor()
    )
    val parisColors = mapOf(
        "background" to getRandomHexColor(),
        "text" to getRandomHexColor(),
    )
    return mapOf(
        "bogota" to bogotaColors,
        "algiers" to algiersColors,
        "bali" to baliColors,
        "geneva" to genevaColors,
        "los_angeles" to losAngelesColors,
        "seattle" to seattleColors,
        "berlin" to berlinColors,
        "paris" to parisColors,
    )
}

private fun KPColorTransformerTest.colorsFrom(theme: String): Map<String, String> {
    val themeColors = colorSetBTS()
    val colors = themeColors[theme]
    assertNotNull(colors)
    return colors
}

@OptIn(ExperimentalSerializationApi::class)
fun KPColorTransformerTest.setupSUT(
    animationName: String,
    colors: Map<String, String>
): KPLottieAnimation {
    val pathToAnimation = "src/commonTest/resources/animations/$animationName.json"
    val pathToAnimationRules =
        "src/commonTest/resources/animations/$animationName-rules.json"
    val colorTransformer = KPColorTransformer()
    val animationJson = FileSystem.SYSTEM.read(pathToAnimation.toPath()) {
        readUtf8()
    }
    val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRules.toPath()) {
        readUtf8()
    }
    val json = Json {
        explicitNulls = false
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
    val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
    val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
    return colorTransformer.transformColor(lottieAnimation, animationRules, colors)
}

@OptIn(ExperimentalSerializationApi::class)
fun KPColorTransformerTest.setupSUT0(
    animationName: String
): KPLottieAnimation {
    val pathToAnimation = "src/commonTest/resources/animations/$animationName.json"
    val pathToAnimationRules =
        "src/commonTest/resources/animations/$animationName-rules.json"
    val animationJson = FileSystem.SYSTEM.read(pathToAnimation.toPath()) {
        readUtf8()
    }
    val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRules.toPath()) {
        readUtf8()
    }
    val json = Json {
        explicitNulls = false
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
    return json.decodeFromString<KPLottieAnimation>(animationJson)
}