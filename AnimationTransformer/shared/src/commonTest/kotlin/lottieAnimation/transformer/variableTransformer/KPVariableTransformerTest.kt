package lottieAnimation.transformer.variableTransformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.KPMultiDimensionNodeObject
import lottieAnimation.layer.properties.KPMultiDimensionalList
import lottieAnimation.layer.properties.KPMultiDimensionalNodePrimitive
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPAnimationTransformerFunctionsDelegate
import transformer.KPVariableTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KPVariableTransformerTest {
    @Test
    fun testVariableTransformerAlgiersFord() {
        val sut = setupSUT("ALGIERS-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 482.0)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 482.0)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 707.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 707.0)
    }

    @Test
    fun testVariableTransformerAlgiersPeugeot() {
        val sut = setupSUT("ALGIERS-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 347.5)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 347.5)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 887.5)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 887.5)
    }

    @Test
    fun testVariableTransformerAlgiersPlane() {
        val sut = setupSUT("ALGIERS-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 436.0)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 436.0)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 436.0)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 436.0)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 436.0)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 837.0)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 837.0)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 837.0)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 837.0)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerRectWidth(shapeLayer3, 0, 0, 907.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 0, 823.5)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 0, 789.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 1, 789.0)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 1, 789.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 2, 789.0)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 2, 789.0)
        val textLayer4 = sut.layers[4]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 925.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 925.0)
        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 925.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 925.0)
        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 925.0)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 925.0)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 907.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 0, 894.0)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 0, 894.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 1, 894.0)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 1, 894.0)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 2, 894.0)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 2, 894.0)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 823.5)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 823.5)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 823.5)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 823.5)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 823.5)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 823.5)
    }

    @Test
    fun testVariableTransformerAlgiersSimca() {
        val sut = setupSUT("ALGIERS-SIMCA")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 594.5)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 594.5)
    }

    @Test
    fun testVariableTransformerBaliFord() {
        val sut = setupSUT("BALI-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 582.0)
    }

    @Test
    fun testVariableTransformerBaliPeugeot() {
        val sut = setupSUT("BALI-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 347.5)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 887.5)
    }

    @Test
    fun testVariableTransformerBaliPlane() {
        val sut = setupSUT("BALI-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 436.0)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 837.0)
        val textLayer7 = sut.layers[7]
        assertTextLayerPositionY(textLayer7, 960.0)
        val textLayer6 = sut.layers[6]
        assertTextLayerPositionY(textLayer6, 830.0)
        val shapeLayer8 = sut.layers[8]
        assertShapeLayerRectWidth(shapeLayer8, 1, 0, 747.0)
        assertShapeLayerRectHeight(shapeLayer8, 1, 0, 334.0)
        assertShapeLayerPositionY(shapeLayer8, 870.0)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerPositionX(shapeLayer3, 581.5)
        assertShapeLayerPositionY(shapeLayer3, 1047.0)
        assertShapeLayerShapeKeyframeValueY(shapeLayer3, 0, 0, 1, -354.0)
        assertShapeLayerPositionX(shapeLayer3, 581.5)
        var shapeLayer4 = sut.layers[4]
        assertShapeLayerPositionX(shapeLayer4, 581.5)
        assertShapeLayerPositionY(shapeLayer4, 698.0)
        assertShapeLayerShapeKeyframeValueX(shapeLayer4, 0, 0, 1, 757.0)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerPositionX(shapeLayer2, 1338.5)
        assertShapeLayerPositionY(shapeLayer2, 693.0)
        assertShapeLayerShapeKeyframeValueY(shapeLayer2, 0, 0, 1, 354.0)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerPositionX(shapeLayer5, 1338.5)
        assertShapeLayerShapeKeyframeValueX(shapeLayer5, 0, 0, 1, -757.0)
        assertShapeLayerPositionY(shapeLayer5, 1042.0)
    }

    @Test
    fun testVariableTransformerBerlinFord() {
        val sut = setupSUT("BERLIN-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerOp(textLayer0, 60.0)
        val shapeLayer1 = sut.layers[1]
        assertShapeLayerOp(shapeLayer1, 60.0)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerOp(shapeLayer2, 60.0)
    }
}

fun setupSUT(
    animationName: String
): KPLottieAnimation {
    val pathToAnimation = "src/commonTest/resources/animations/$animationName.json"
    val pathToAnimationRules =
        "src/commonTest/resources/animations/$animationName-rules.json"
    val functionsDelegateMock = AnimationTransformerFunctionsDelegateMock()
    val variableTransformer = KPVariableTransformer(functionsDelegateMock)
    val animationJson = FileSystem.SYSTEM.read(pathToAnimation.toPath()) {
        readUtf8()
    }
    val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRules.toPath()) {
        readUtf8()
    }
    val json = Json {
        explicitNulls = false
        encodeDefaults = true
    }
    val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
    val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
    return variableTransformer.transformVariables(lottieAnimation, animationRules)
}

fun assertTextLayerPositionKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertTextLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertTextLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertTextLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val eValue = node.e?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, eValue)
}

fun assertTextLayerPositionKeyframeTime(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val tValue = node.t?.doubleOrNull
    assertEquals(expectedValue, tValue)
}

fun assertShapeLayerPositionKeyframeTime(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val tValue = node.t?.doubleOrNull
    assertEquals(expectedValue, tValue)
}

fun assertTextLayerPositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(0)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value?.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun assertTextLayerPositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(1)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertShapeLayerPositonKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertShapeLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertShapeLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertShapeLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertShapeLayerPositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(0)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value?.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun assertShapeLayerPositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(1)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertShapeLayerRectWidth(layer: KPLayer, shapeIndex: Int, itemIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeRect)
    val nodeK = it.s?.k
    assertTrue(nodeK is KPMultiDimensionalList)
    val nodeWidth = nodeK.values?.get(0)
    assertTrue(nodeWidth is KPMultiDimensionalNodePrimitive)
    val widthValue = nodeWidth?.value?.doubleOrNull
    assertEquals(expectedValue, widthValue)
}

fun assertShapeLayerRectHeight(layer: KPLayer, shapeIndex: Int, itemIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeRect)
    val nodeK = it.s?.k
    assertTrue(nodeK is KPMultiDimensionalList)
    val nodeWidth = nodeK.values?.get(1)
    assertTrue(nodeWidth is KPMultiDimensionalNodePrimitive)
    val widthValue = nodeWidth?.value?.doubleOrNull
    assertEquals(expectedValue, widthValue)
}

fun assertShapeLayerShapeKeyframeValueX(layer: KPLayer, shapeIndex: Int, itemIndex: Int, valueIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeShape)
    val yValue = it.ks?.jsonObject?.get("k")?.jsonObject?.get("v")?.jsonArray?.get(valueIndex)?.jsonArray?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertShapeLayerShapeKeyframeValueY(layer: KPLayer, shapeIndex: Int, itemIndex: Int, valueIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeShape)
    val yValue = it.ks?.jsonObject?.get("k")?.jsonObject?.get("v")?.jsonArray?.get(valueIndex)?.jsonArray?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertTextLayerOp(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    assertEquals(expectedValue, layer?.op?.jsonPrimitive?.doubleOrNull)
}

fun assertShapeLayerOp(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    assertEquals(expectedValue, layer?.op?.jsonPrimitive?.doubleOrNull)
}

/**
 * Do not modify, it is assumptions for variable transformer test (mock)
 */
class AnimationTransformerFunctionsDelegateMock: KPAnimationTransformerFunctionsDelegate {
    override fun getAscent(text: String, fontName: String, fontSize: Double): Double {
        return 138.18
    }

    override fun getDescent(text: String, fontName: String, fontSize: Double): Double {
        TODO("Not yet implemented")
    }

    override fun getLastLineBottom(text: String, fontName: String, fontSize: Double): Double {
        return -17.04
    }

    override fun getTextMeasureHeight(
        text: String,
        fontName: String,
        fontSize: Double,
        layerSize: List<Double>?,
        layerLineHeight: Double,
        layerTracking: Double
    ): Double {
        return 129.6
    }

    override fun getTextLayerWidth(text: String, fontName: String, fontSize: Double): Double {
        return 254.16
    }
}