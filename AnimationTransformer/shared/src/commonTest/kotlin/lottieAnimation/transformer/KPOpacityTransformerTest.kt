package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPShapeLayer
import lottieAnimation.layer.properties.KPMultiDimensionalListOrPrimitive
import lottieAnimation.layer.properties.KPMultiDimensionalPrimitive
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPOpacityTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class KPOpacityTransformerTest {

	val colors = mapOf("background" to "#ffffff50")

	@Test
	fun testPopIn() {
		val sut = setupSUT("Popin-In-Out-BG")
		val shapeLayer = sut.layers[1] // ShapeLayer is at index 1
		assertShapeLayerOpacity(shapeLayer, KPMultiDimensionalPrimitive(JsonPrimitive(50)))
	}

	@Test
	fun testZoomIn() {
		val sut = setupSUT("Zoom-In-Out-BG")
		val shapeLayer = sut.layers[1] // ShapeLayer is at index 1
		assertShapeLayerOpacity(shapeLayer, KPMultiDimensionalPrimitive(JsonPrimitive(50)))
	}

	@Test
	fun testFadeIn() {
		val sut = setupSUT("Fade-In-Out-BG")
		val shapeLayer = sut.layers[1] // ShapeLayer is at index 1
		assertShapeLayerOpacity(shapeLayer, KPMultiDimensionalPrimitive(JsonPrimitive(50)))
	}

}

private fun KPOpacityTransformerTest.assertShapeLayerOpacity(layer: KPLayer, expectedValue: KPMultiDimensionalListOrPrimitive) {
	assertTrue(layer is KPShapeLayer)
	val k = layer.ks.o?.k
	assertEquals(expectedValue, k)
}

@OptIn(ExperimentalSerializationApi::class)
fun KPOpacityTransformerTest.setupSUT(animatedText: String): KPLottieAnimation {
	val pathToAnimation = "src/commonTest/resources/animations/animatedText/$animatedText.json"
	val pathToAnimationRules =
		"src/commonTest/resources/animations/animatedText/Popin-in-out-rules.json"
	val opacityTransformer = KPOpacityTransformer()
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
	return opacityTransformer.transformOpacity(lottieAnimation, animationRules, colors)
}