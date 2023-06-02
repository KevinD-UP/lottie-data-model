package lottieAnimation.transformer.variableTransformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.layer.properties.KPMultiDimensionNodeObject
import lottieAnimation.layer.properties.KPMultiDimensionalList
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
        assertTextLayerNodeS_y(textLayer0, 482.0)
        assertTextLayerNodeE_y(textLayer0, 482.0)

        val textLayer1 = sut.layers[1]
        assertTextLayerNodeS_y(textLayer1, 707.0)
        assertTextLayerNodeE_y(textLayer1, 707.0)
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

    fun assertTextLayerNodeS_x(layer: KPLayer, expectedValue: Double) {
        assertTrue(layer is KPTextLayer)
        val kList = layer.ks.p?.k
        assertTrue(kList is KPMultiDimensionalList)
        val first = kList?.values?.firstOrNull()
        assertTrue(first is KPMultiDimensionNodeObject)
        val sValue = first.s?.get(0)?.jsonPrimitive?.doubleOrNull
        assertEquals(expectedValue, sValue)
    }

    fun assertTextLayerNodeS_y(layer: KPLayer, expectedValue: Double) {
        assertTrue(layer is KPTextLayer)
        val kList = layer.ks.p?.k
        assertTrue(kList is KPMultiDimensionalList)
        val first = kList?.values?.firstOrNull()
        assertTrue(first is KPMultiDimensionNodeObject)
        val sValue = first.s?.get(1)?.jsonPrimitive?.doubleOrNull
        assertEquals(expectedValue, sValue)
    }

    fun assertTextLayerNodeE_x(layer: KPLayer, expectedValue: Double) {
        assertTrue(layer is KPTextLayer)
        val kList = layer.ks.p?.k
        assertTrue(kList is KPMultiDimensionalList)
        val first = kList?.values?.firstOrNull()
        assertTrue(first is KPMultiDimensionNodeObject)
        val sValue = first.e?.get(0)?.jsonPrimitive?.doubleOrNull
        assertEquals(expectedValue, sValue)
    }

    fun assertTextLayerNodeE_y(layer: KPLayer, expectedValue: Double) {
        assertTrue(layer is KPTextLayer)
        val kList = layer.ks.p?.k
        assertTrue(kList is KPMultiDimensionalList)
        val first = kList?.values?.firstOrNull()
        assertTrue(first is KPMultiDimensionNodeObject)
        val sValue = first.e?.get(1)?.jsonPrimitive?.doubleOrNull
        assertEquals(expectedValue, sValue)
    }
}

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