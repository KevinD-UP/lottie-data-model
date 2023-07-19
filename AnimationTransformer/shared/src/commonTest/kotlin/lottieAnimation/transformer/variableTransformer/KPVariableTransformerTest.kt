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
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 529.04)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 529.04)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 706.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 706.1800000000001)
    }

    @Test
    fun testVariableTransformerAlgiersPeugeot() {
        val sut = setupSUT("ALGIERS-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 347.61)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 347.61)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 887.6099999999999)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 887.6099999999999)
    }

    @Test
    fun testVariableTransformerAlgiersPlane() {
        val sut = setupSUT("ALGIERS-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 507.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 507.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 507.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 507.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 507.03999999999996)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 728.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 728.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 728.1800000000001)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerRectWidth(shapeLayer3, 0, 0, 454.15999999999997)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 0, 820.0000000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 0, 742.3900000000002)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 1, 742.3900000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 1, 742.3900000000002)
        assertShapeLayerPositionKeyframeStartY(shapeLayer3, 2, 742.3900000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer3, 2, 742.3900000000002)
        val textLayer4 = sut.layers[4]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 925.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 925.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 925.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 925.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 925.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 925.0000000000001)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 454.15999999999997)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 0, 847.3900000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 0, 847.3900000000002)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 1, 847.3900000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 1, 847.3900000000002)
        assertShapeLayerPositionKeyframeStartY(shapeLayer5, 2, 847.3900000000002)
        assertShapeLayerPositionKeyframeEndY(shapeLayer5, 2, 847.3900000000002)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 820.0000000000002)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 820.0000000000002)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 820.0000000000002)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 820.0000000000002)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 820.0000000000002)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 820.0000000000002)
    }

    @Test
    fun testVariableTransformerAlgiersSimca() {
        val sut = setupSUT("ALGIERS-SIMCA")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 617.61)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 617.61)
    }

    @Test
    fun testVariableTransformerBaliFord() {
        val sut = setupSUT("BALI-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 617.61)
    }

    @Test
    fun testVariableTransformerBaliPeugeot() {
        val sut = setupSUT("BALI-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 347.61)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 887.6099999999999)
    }

    @Test
    fun testVariableTransformerBaliPlane() {
        val sut = setupSUT("BALI-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 507.03999999999996)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionY(textLayer1, 728.1800000000001)
        val textLayer7 = sut.layers[7]
        assertTextLayerPositionY(textLayer7, 960.0000000000001)
        val textLayer6 = sut.layers[6]
        assertTextLayerPositionY(textLayer6, 798.8600000000001)
        val shapeLayer8 = sut.layers[8]
        assertShapeLayerRectWidth(shapeLayer8, 1, 0, 294.15999999999997)
        assertShapeLayerRectHeight(shapeLayer8, 1, 0, 382.28000000000003)
        assertShapeLayerPositionY(shapeLayer8, 801.8200000000002)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerPositionX(shapeLayer3, 807.9200000000001)
        assertShapeLayerPositionY(shapeLayer3, 1002.9600000000002)
        assertShapeLayerShapeKeyframeValueY(shapeLayer3, 0, 0, 1, -402.28000000000003)
        assertShapeLayerPositionX(shapeLayer3, 807.9200000000001)
        var shapeLayer4 = sut.layers[4]
        assertShapeLayerPositionX(shapeLayer4, 807.9200000000001)
        assertShapeLayerPositionY(shapeLayer4, 605.6800000000002)
        assertShapeLayerShapeKeyframeValueX(shapeLayer4, 0, 0, 1, 304.15999999999997)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerPositionX(shapeLayer2, 1112.08)
        assertShapeLayerPositionY(shapeLayer2, 600.6800000000002)
        assertShapeLayerShapeKeyframeValueY(shapeLayer2, 0, 0, 1, 402.28000000000003)
        val shapeLayer5 = sut.layers[5]
        assertShapeLayerPositionX(shapeLayer5, 1112.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer5, 0, 0, 1, -304.15999999999997)
        assertShapeLayerPositionY(shapeLayer5, 997.9600000000002)
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
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 799.3199999999999)
        val shapeLayer2 = sut.layers[2]
        assertShapeLayerRectHeight(shapeLayer2, 0, 0, 131.14000000000001)
        val textLayer3 = sut.layers[3]
        assertTextLayerPositionKeyframeEndY(textLayer3, 0, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer3, 0, 799.3199999999999)
        val shapeLayer4 = sut.layers[4]
        assertShapeLayerRectHeight(shapeLayer4, 0, 0, 131.14000000000001)
    }

    @Test
    fun testVariableTransformerBerlinPlane() {
        val sut = setupSUT("BERLIN-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 778.8600000000001)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 778.8600000000001)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 778.8600000000001)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 778.8600000000001)

        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 950.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 950.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 950.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 950.0000000000001)

        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 758.75)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 617.61)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 617.61)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer2, 3, 617.61)
        assertTextLayerPositionKeyframeEndY(textLayer2, 3, 758.75)

        val shapeLayer3 = sut.layers[3]
        assertShapeLayerRectHeight(shapeLayer3, 0, 0, 141.14000000000001)
    }

    @Test
    fun testVariableTransformerGenevaFord() {
        val sut = setupSUT("GENEVA-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 617.61)
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 617.61)

        val shapeLayer1 = sut.layers[1]
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 0, -127.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 1, 127.08)
    }

    @Test
    fun testVariableTransformerGenevaPeugeot() {
        val sut = setupSUT("GENEVA-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 513.04)
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 513.04)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 513.04)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 513.04)
        val shapeLayer1 = sut.layers[1]
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 0, -127.08)
        assertShapeLayerShapeKeyframeValueX(shapeLayer1, 0, 0, 1, 127.08)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 722.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 722.1800000000001)
    }

    @Test
    fun testVariableTransformerGenevaPlane() {
        val sut = setupSUT("GENEVA-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer0, 0, 503.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 503.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer0, 1, 503.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer0, 1, 503.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer0, 2, 503.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer0, 2, 503.03999999999996)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 732.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 732.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 732.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 732.1800000000001)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, -30.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, -30.0)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, -30.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, -30.0)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, -30.0)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, -30.0)
        val shapeLayer3 = sut.layers[3]
        assertShapeLayerPositionY(shapeLayer3, -153.18)
        assertShapeLayerShapeKeyframeValueX(shapeLayer3, 0, 0, 0, -476.0)
        assertShapeLayerShapeKeyframeValueX(shapeLayer3, 0, 0, 1, 476.0)
        val textLayer4 = sut.layers[4]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 960.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 960.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 960.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 960.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 960.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 960.0000000000001)
    }

    @Test
    fun testVariableTransformerSeattlePlane() {
        val sut = setupSUT("SEATTLE-PLANE")

        //var0
        val textLayer7 = sut.layers[6]
        assertTextLayerPositionKeyframeStartY(textLayer7, 0, 980.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer7, 0, 980.0000000000001)

        assertTextLayerPositionKeyframeStartY(textLayer7, 1, 980.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer7, 1, 980.0000000000001)

        assertTextLayerPositionKeyframeStartY(textLayer7, 2, 980.0000000000001)
        assertTextLayerPositionKeyframeEndY(textLayer7, 2, 980.0000000000001)

        //var1
        val shapeLayer6 = sut.layers[5]
        assertShapeLayerRectHeight(shapeLayer6, 0, 0, 167.14000000000001)
        val shapeLayer8 = sut.layers[7]
        assertShapeLayerRectHeight(shapeLayer8, 0,0, 167.14000000000001)
        //var2
        val shapeLayer3 = sut.layers[2]
        assertShapeLayerRectWidth(shapeLayer3,0,0,697.0)
        val shapeLayer5 = sut.layers[4]
        assertShapeLayerRectWidth(shapeLayer5, 0, 0, 697.0)
        assertShapeLayerRectWidth(shapeLayer6,0,0,318.15999999999997)
        assertShapeLayerRectWidth(shapeLayer8, 0,0,318.15999999999997)
        //var3
        assertShapeLayerPositionY(shapeLayer6,902.3900000000002)
        assertShapeLayerPositionY(shapeLayer8,902.3900000000002)
        //var4
        assertShapeLayerRectHeight(shapeLayer3,0, 0,185.14000000000001)
        assertShapeLayerRectHeight(shapeLayer5,0,0,185.14000000000001)
        //var5
        val textLayer4 = sut.layers[3]
        assertTextLayerPositionKeyframeStartY(textLayer4, 0, 803.8600000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 0, 803.8600000000001)

        assertTextLayerPositionKeyframeStartY(textLayer4, 1, 803.8600000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 1, 803.8600000000001)

        assertTextLayerPositionKeyframeStartY(textLayer4, 2, 803.8600000000001)
        assertTextLayerPositionKeyframeEndY(textLayer4, 2, 803.8600000000001)
        //var6
        assertShapeLayerPositionY(shapeLayer3,726.2500000000001)
        assertShapeLayerPositionY(shapeLayer5, 726.2500000000001)
        //var7
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 507.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 507.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer1, 1, 507.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 507.03999999999996)
        assertTextLayerPositionKeyframeStartY(textLayer1, 2, 507.03999999999996)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 507.03999999999996)
        //var8
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 728.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer2, 1, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer2, 1, 728.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer2, 2, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer2, 2, 728.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer2, 3, 728.1800000000001)
        assertTextLayerPositionKeyframeEndY(textLayer2, 3, 728.1800000000001)
    }

    @Test
    fun testVariableTransformerSeattlePeugeot() {
        val sut = setupSUT("SEATTLE-PEUGEOT")

        //var0
        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,347.61)
        //var1
        val textLayer2 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer2,0,887.6099999999999)
    }

    @Test
    fun testVariableTransformerSeattleFord() {
        val sut = setupSUT("SEATTLE-FORD")

        val textLayer1 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer1,0,617.61)

        assertTextLayerPositionKeyframeStartY(textLayer1,1,617.61)
        assertTextLayerPositionKeyframeEndY(textLayer1, 1, 617.61)

        assertTextLayerPositionKeyframeStartY(textLayer1,2,617.61)
        assertTextLayerPositionKeyframeEndY(textLayer1, 2, 617.61)
    }

    @Test
    fun testVariableTransformerLosAngelesFord() {
        val sut = setupSUT("LOS_ANGELES-FORD")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 517.04)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 718.1800000000001)
    }

    @Test
    fun testVariableTransformerLosAngelesPeugeot() {
        val sut = setupSUT("LOS_ANGELES-PEUGEOT")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 517.04)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 718.1800000000001)
    }

    @Test
    fun testVariableTransformerLosAngelesPlane() {
        val sut = setupSUT("LOS_ANGELES-PLANE")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionY(textLayer0, 511.03999999999996)
        val textLayer1 = sut.layers[1]
        assertTextLayerPositionKeyframeEndY(textLayer1, 0, 724.1800000000001)
        assertTextLayerPositionKeyframeStartY(textLayer1, 0, 724.1800000000001)
        val textLayer2 = sut.layers[2]
        assertTextLayerPositionKeyframeEndY(textLayer2, 0, 838.8600000000001)
        assertTextLayerPositionKeyframeStartY(textLayer2, 0, 838.8600000000001)
        val textLayer3 = sut.layers[3]
        assertTextLayerPositionKeyframeEndY(textLayer3, 0, 1000.0000000000001)
        assertTextLayerPositionKeyframeStartY(textLayer3, 0, 1000.0000000000001)
        assertTextLayerPositionKeyframeStartX(textLayer3, 0, 1097.08)
        assertTextLayerAnimatePositionX(textLayer3, 137.07999999999998)
        assertTextLayerPositionKeyframeEndX(textLayer3, 0, 1897.08)
        val solidLayer4 = sut.layers[4]
        assertSolidLayerSourceWidth(solidLayer4, 274.15999999999997)
        assertSolidLayerSourceHeight(solidLayer4, 161.14000000000001)
        assertSolidLayerAnimatePositionX(solidLayer4, 274.15999999999997)
        assertSolidLayerPositionKeyframeStartY(solidLayer4, 0, 841.8200000000002)
        assertSolidLayerPositionKeyframeEndY(solidLayer4, 0, 841.8200000000002)
        assertSolidLayerPositionKeyframeStartX(solidLayer4, 0, 1097.08)
        assertSolidLayerPositionKeyframeEndX(solidLayer4, 0, 1897.08)
    }

    @Test
    fun testVariableTransformerLosAngelesSimca() {
        val sut = setupSUT("LOS_ANGELES-SIMCA")

        val textLayer0 = sut.layers[0]
        assertTextLayerPositionKeyframeEndY(textLayer0, 0, 617.61)
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
    println("phets node $node")
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

fun assertTextLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(0)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value?.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun assertTextLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(1)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertShapeLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(0)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value?.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun assertShapeLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPShapeLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(1)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertSolidLayerSourceWidth(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    assertEquals(expectedValue, layer?.sw?.jsonPrimitive?.doubleOrNull)
}

fun assertSolidLayerSourceHeight(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    assertEquals(expectedValue, layer?.sh?.jsonPrimitive?.doubleOrNull)
}

fun assertSolidLayerAnimatePositionX(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(0)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val xValue = node.value?.doubleOrNull
    assertEquals(expectedValue, xValue)
}

fun assertSolidLayerAnimatePositionY(layer: KPLayer, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.a?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(1)
    assertTrue(node is KPMultiDimensionalNodePrimitive)
    val yValue = node.value?.doubleOrNull
    assertEquals(expectedValue, yValue)
}

fun assertSolidLayerPositionKeyframeStartX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertSolidLayerPositionKeyframeStartY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.s?.get(1)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertSolidLayerPositionKeyframeEndX(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
    assertTrue(node is KPMultiDimensionNodeObject)
    val sValue = node.e?.get(0)?.jsonPrimitive?.doubleOrNull
    assertEquals(expectedValue, sValue)
}

fun assertSolidLayerPositionKeyframeEndY(layer: KPLayer, keyframeIndex: Int, expectedValue: Double) {
    assertTrue(layer is KPSolidLayer)
    val kList = layer.ks.p?.k
    assertTrue(kList is KPMultiDimensionalList)
    val node = kList?.values?.get(keyframeIndex)
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
        val ascent = getAscent(text, fontName, fontSize)
        val descent = getLastLineBottom(text, fontName, fontSize)
        return ascent + descent
    }

    override fun getTextLayerWidth(text: String, fontName: String, fontSize: Double): Double {
        return 254.16
    }
}