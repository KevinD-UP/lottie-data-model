package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
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
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 244.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 244.21999999999997)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 429.82)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 429.82)
    }

    @Test
    fun testVariableTransformerAlgiersPeugeot() {
        val sut = setupSUT("ALGIERS-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 67.01999999999998)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 67.01999999999998)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 607.02)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 607.02)
    }

    @Test
    fun testVariableTransformerAlgiersPlane() {
        val sut = setupSUT("ALGIERS-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 222.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 222.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 222.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 222.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 222.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 451.82)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 451.82)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 451.82)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerRectWidth(shapeLayer3, 0, 0, 454.15999999999997)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 0, 305.3599999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 0, 508.3399999999999)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 1, 508.3399999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 1, 508.3399999999999)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 2, 508.3399999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 2, 508.3399999999999)
        val textLayer4 = sut.layers[4]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 410.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 410.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 410.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 410.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 410.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 410.35999999999996)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 454.15999999999997)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 0, 613.3399999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 0, 613.3399999999999)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 1, 613.3399999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 1, 613.3399999999999)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 2, 613.3399999999999)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 2, 613.3399999999999)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 305.3599999999999)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 305.3599999999999)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 305.3599999999999)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 305.3599999999999)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 305.3599999999999)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 305.3599999999999)
    }

    @Test
    fun testVariableTransformerAlgiersSimca() {
        val sut = setupSUT("ALGIERS-SIMCA")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 337.02)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 337.02)
    }

    @Test
    fun testVariableTransformerBaliFord() {
        val sut = setupSUT("BALI-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 337.02)
    }

    @Test
    fun testVariableTransformerBaliPeugeot() {
        val sut = setupSUT("BALI-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 67.01999999999998)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 607.02)
    }

    @Test
    fun testVariableTransformerBaliPlane() {
        val sut = setupSUT("BALI-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 222.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 451.82)
        val textLayer7 = sut.layers[7]
        assertTextLayerPositionY(textLayer7, 390.35999999999996)
        val textLayer6 = sut.layers[6]
        assertTextLayerPositionY(textLayer6, 220.75999999999993)
        val shapeLayer8 = sut.layers[8]
        assertShapeLayerRectWidth(shapeLayer8, 1, 0, 294.15999999999997)
        assertShapeLayerRectHeight(shapeLayer8, 1, 0, 399.2)
        assertShapeLayerPositionY(shapeLayer8, 508.53999999999996)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerPositionX(shapeLayer3, 807.9200000000001)
        assertShapeLayerPositionY(shapeLayer3, 718.14)
        assertShapeLayerShapeKeyframeValueY(shapeLayer3, 0, 0, 1, -419.2)
        assertShapeLayerPositionX(shapeLayer3, 807.9200000000001)
        val shapeLayer4 = sut.layers[4]
        assertShapeLayerPositionX(shapeLayer4, 807.9200000000001)
        assertShapeLayerPositionY(shapeLayer4, 303.93999999999994)
        assertShapeLayerShapeKeyframeValueX(shapeLayer4, 0, 0, 1, 304.15999999999997)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerPositionX(shapeLayer2, 1112.08)
        assertShapeLayerPositionY(shapeLayer2, 298.93999999999994)
        assertShapeLayerShapeKeyframeValueY(shapeLayer2, 0, 0, 1, 419.2)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerPositionX(shapeLayer5, 1112.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer5, 0, 0, 1, -304.15999999999997)
        assertShapeLayerPositionY(shapeLayer5, 713.14)
    }

    @Test
    fun testVariableTransformerBerlinFord() {
        val sut = setupSUT("BERLIN-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerOp(textLayer0, 59.9400024414062)
        assertTextLayerPositionKeyframeTime(textLayer0, 3, 58.9400024414062)
        val shapeLayer1 = sut.layers[1]
        assertShapeLayerOp(shapeLayer1, 59.9400024414062)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerOp(shapeLayer2, 59.9400024414062)
    }

    @Test
    fun testVariableTransformerBerlinPeugeot() {
        val sut = setupSUT("BERLIN-PEUGEOT")

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 531.4200000000001)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerRectHeight(shapeLayer2, 0, 0, 139.6)
        val textLayer3 = sut.layers[3]
        assertTextLayerPositionKeyframeEndY(textLayer3, 0, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer3, 0, 531.4200000000001)
        val shapeLayer4 = sut.layers[4]
        assertShapeLayerRectHeight(shapeLayer4, 0, 0, 139.6)
    }

    @Test
    fun testVariableTransformerBerlinPlane() {
        val sut = setupSUT("BERLIN-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 280.75999999999993)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 280.75999999999993)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 280.75999999999993)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 280.75999999999993)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 460.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 460.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 460.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 460.35999999999996)

        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 486.61999999999995)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 337.02)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 337.02)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer2, 3, 337.02)
        assertTextLayerPositionKeyframeEndY(textLayer2, 3, 486.61999999999995)

        val shapeLayer3 = sut.layers[3]
        assertShapeLayerRectHeight(shapeLayer3, 0, 0, 149.6)
    }

    @Test
    fun testVariableTransformerGenevaFord() {
        val sut = setupSUT("GENEVA-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 337.02)
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 337.02)

        val shapeLayer1 = sut.layers[1]
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 0, -127.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 1, 127.08)
    }

    @Test
    fun testVariableTransformerGenevaPeugeot() {
        val sut = setupSUT("GENEVA-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 228.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 228.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 228.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 228.21999999999997)
        val shapeLayer1 = sut.layers[1]
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 0, -127.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 1, 127.08)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 445.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 445.82)
    }

    @Test
    fun testVariableTransformerGenevaPlane() {
        val sut = setupSUT("GENEVA-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 218.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 218.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 218.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 218.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 218.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer0, 2, 218.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 455.82)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 455.82)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 455.82)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 455.82)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, -314.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, -314.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, -314.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, -314.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, -314.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, -314.82)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerPositionY(shapeLayer3, 123.18)
        assertShapeLayerShapeKeyframeValueX(shapeLayer3, 0, 0, 0, -476.0)
        assertShapeLayerShapeKeyframeValueX(shapeLayer3, 0, 0, 1, 476.0)
        val textLayer4 = sut.layers[4]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 510.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 510.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 510.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 510.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 510.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 510.35999999999996)
    }

    @Test
    fun testVariableTransformerSeattlePlane() {
        val sut = setupSUT("SEATTLE-PLANE")

        //var0
        val textLayer7 = sut.layers[6]
        assertTextLayerPositionKeyframeStartY(textLayer7, 0, 460.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer7, 0, 460.35999999999996)

        assertTextLayerPositionKeyframeStartY(textLayer7, 1, 460.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer7, 1, 460.35999999999996)

        assertTextLayerPositionKeyframeStartY(textLayer7, 2, 460.35999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer7, 2, 460.35999999999996)

        //var1
        val shapeLayer6 = sut.layers[5]
        assertShapeLayerRectHeight(shapeLayer6, 0, 0, 175.6)
        val shapeLayer8 = sut.layers[7]
        assertShapeLayerRectHeight(shapeLayer8, 0,0, 175.6)
        //var2
        val shapeLayer3 = sut.layers[2]
        assertShapeLayerRectWidth(shapeLayer3,0,0,697.0)
        val shapeLayer5 = sut.layers[4]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 697.0)
        assertShapeLayerRectWidth(shapeLayer6,0,0,318.15999999999997)
        assertShapeLayerRectWidth(shapeLayer8, 0,0,318.15999999999997)
        //var3
        assertShapeLayerPositionY(shapeLayer6,663.3399999999999)
        assertShapeLayerPositionY(shapeLayer8,663.3399999999999)
        //var4
        assertShapeLayerRectHeight(shapeLayer3,0, 0,193.6)
        assertShapeLayerRectHeight(shapeLayer5,0,0,193.6)
        //var5
        val textLayer4 = sut.layers[3]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 275.75999999999993)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 275.75999999999993)

        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 275.75999999999993)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 275.75999999999993)

        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 275.75999999999993)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 275.75999999999993)
        //var6
        assertShapeLayerPositionY(shapeLayer3,478.73999999999995)
        assertShapeLayerPositionY(shapeLayer5, 478.73999999999995)
        //var7
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 222.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 222.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 222.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 222.21999999999997)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 222.21999999999997)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 222.21999999999997)
        //var8
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 451.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 451.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 451.82)
        assertTextLayerPositionKeyframeStartY(textLayer2, 3, 451.82)
        assertTextLayerPositionKeyframeEndY(textLayer2, 3, 451.82)
    }

    @Test
    fun testVariableTransformerSeattlePeugeot() {
        val sut = setupSUT("SEATTLE-PEUGEOT")

        //var0
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,67.01999999999998)
        //var1
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer2,0,607.02)
    }

    @Test
    fun testVariableTransformerSeattleFord() {
        val sut = setupSUT("SEATTLE-FORD")

        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,337.02)

        assertTextLayerPositionKeyframeStartY(textLayer1,1,337.02)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 337.02)

        assertTextLayerPositionKeyframeStartY(textLayer1,2,337.02)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 337.02)
    }

    @Test
    fun testVariableTransformerLosAngelesFord() {
        val sut = setupSUT("LOS_ANGELES-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 232.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 441.82)
    }

    @Test
    fun testVariableTransformerLosAngelesPeugeot() {
        val sut = setupSUT("LOS_ANGELES-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 232.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 441.82)
    }

    @Test
    fun testVariableTransformerLosAngelesPlane() {
        val sut = setupSUT("LOS_ANGELES-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 226.21999999999997)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 447.82)
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 447.82)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 320.75999999999993)
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 320.75999999999993)
        val textLayer3 = sut.layers[3]
        assertTextLayerPositionKeyframeEndY(textLayer3, 0, 490.35999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer3, 0, 490.35999999999996)
        assertTextLayerPositionKeyframeStartX(textLayer3, 0, 1097.08)
        assertTextLayerAnimatePositionX(textLayer3, 137.07999999999998)
        assertTextLayerPositionKeyframeEndX(textLayer3, 0, 1897.08)
        val solidLayer4 = sut.layers[4]
        assertSolidLayerSourceWidth(solidLayer4, 274.15999999999997)
        assertSolidLayerSourceHeight(solidLayer4, 169.6)
        assertSolidLayerAnimatePositionX(solidLayer4, 274.15999999999997)
        assertSolidLayerPositionKeyframeStartY(solidLayer4, 0, 608.54)
        assertSolidLayerPositionKeyframeEndY(solidLayer4, 0, 608.54)
        assertSolidLayerPositionKeyframeStartX(solidLayer4, 0, 1097.08)
        assertSolidLayerPositionKeyframeEndX(solidLayer4, 0, 1897.08)
    }

    @Test
    fun testVariableTransformerLosAngelesSimca() {
        val sut = setupSUT("LOS_ANGELES-SIMCA")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 337.02)
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun KPVariableTransformerTest.setupSUT(
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

fun KPVariableTransformerTest.assertTextLayerPositionKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val eValue = node.e?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, eValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionKeyframeTime(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val tValue = node.t?.doubleOrNull
    assertEquals(expectedValue, tValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionKeyframeTime(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val tValue = node.t?.doubleOrNull
    assertEquals(expectedValue, tValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values.firstOrNull()
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun KPVariableTransformerTest.assertTextLayerPositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[1]
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositonKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values.firstOrNull()
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun KPVariableTransformerTest.assertShapeLayerPositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[1]
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertShapeLayerRectWidth(layer: KPLayer, shapeIndex: Int, itemIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeRect)
    val nodeK = it.s?.k
    assertTrue(nodeK is KPMultiDimensionalList)
    val nodeWidth = nodeK.values.firstOrNull()
    assertTrue(nodeWidth is KPMultiDimensionalNodePrimitive)
    val widthValue = nodeWidth.value.doubleOrNull
    assertEquals(expectedValue, widthValue)
}

fun KPVariableTransformerTest.assertShapeLayerRectHeight(layer: KPLayer, shapeIndex: Int, itemIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeRect)
    val nodeK = it.s?.k
    assertTrue(nodeK is KPMultiDimensionalList)
    val nodeWidth = nodeK.values[1]
    assertTrue(nodeWidth is KPMultiDimensionalNodePrimitive)
    val widthValue = nodeWidth.value.doubleOrNull
    assertEquals(expectedValue, widthValue)
}

fun KPVariableTransformerTest.assertShapeLayerShapeKeyframeValueX(layer: KPLayer, shapeIndex: Int, itemIndex: Int, valueIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeShape)
    val yValue = it.ks?.jsonObject?.get("k")?.jsonObject?.get("v")?.jsonArray?.get(valueIndex)?.jsonArray?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertShapeLayerShapeKeyframeValueY(layer: KPLayer, shapeIndex: Int, itemIndex: Int, valueIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val shape = layer.shapes?.get(shapeIndex)
    assertTrue(shape is KPShapeGroup)
    val it = shape.it[itemIndex]
    assertTrue(it is KPShapeShape)
    val yValue = it.ks?.jsonObject?.get("k")?.jsonObject?.get("v")?.jsonArray?.get(valueIndex)?.jsonArray?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertTextLayerOp(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    assertEquals(expectedValue, layer.op.jsonPrimitive.doubleOrNull)
}

fun KPVariableTransformerTest.assertShapeLayerOp(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    assertEquals(expectedValue, layer.op.jsonPrimitive.doubleOrNull)
}

fun KPVariableTransformerTest.assertTextLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values.firstOrNull()
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun KPVariableTransformerTest.assertTextLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[1]
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertShapeLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values.firstOrNull()
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun KPVariableTransformerTest.assertShapeLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[1]
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertSolidLayerSourceWidth(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    assertEquals(expectedValue, layer.sw.jsonPrimitive.doubleOrNull)
}

fun KPVariableTransformerTest.assertSolidLayerSourceHeight(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    assertEquals(expectedValue, layer.sh.jsonPrimitive.doubleOrNull)
}

fun KPVariableTransformerTest.assertSolidLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values.firstOrNull()
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun KPVariableTransformerTest.assertSolidLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[1]
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun KPVariableTransformerTest.assertSolidLayerPositionKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertSolidLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertSolidLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun KPVariableTransformerTest.assertSolidLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList.values[keyframeIndex]
    assertTrue(node is KPMultiDimensionNodeObject)
    val eValue = node.e?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, eValue)
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