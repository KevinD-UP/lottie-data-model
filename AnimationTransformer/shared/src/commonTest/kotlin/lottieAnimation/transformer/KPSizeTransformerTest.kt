package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPNullLayer
import lottieAnimation.layer.properties.KPMultiDimensionalList
import lottieAnimation.layer.properties.KPMultiDimensionalListOrPrimitive
import lottieAnimation.layer.properties.KPMultiDimensionalNodePrimitive
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.AnimationSize
import transformer.KPSizeTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KPSizeTransformerTest {
	@Test
	fun slideLeftRight_In_Out() {
		val sut = setupSUT("SlideLeftRight_In-Out")
		val controlPanelLayer = sut.layers[0] // control_panel is always at position 0
		assertControlPanelPosition(controlPanelLayer, KPMultiDimensionalList(
			listOf(
				KPMultiDimensionalNodePrimitive(JsonPrimitive(540)),
				KPMultiDimensionalNodePrimitive(JsonPrimitive(540)),
				KPMultiDimensionalNodePrimitive(JsonPrimitive(0))
				)
			)
		)
		assertSize(sut, AnimationSize(1080, 1080))
	}
}

private fun KPSizeTransformerTest.assertSize(animation: KPLottieAnimation, expectedValue: AnimationSize) {
	assertEquals(animation.w, expectedValue.width)
	assertEquals(animation.h, expectedValue.height)
}

private fun KPSizeTransformerTest.assertControlPanelPosition(layer: KPLayer, expectedValue: KPMultiDimensionalListOrPrimitive) {
	assertTrue(layer is KPNullLayer)
	val k = layer.ks.p?.k
	assertEquals(expectedValue, k)
}

@OptIn(ExperimentalSerializationApi::class)
fun KPSizeTransformerTest.setupSUT(animatedText: String): KPLottieAnimation {
	val pathToAnimation = "src/commonTest/resources/animations/animatedText/$animatedText.json"
	val sizeTransformer = KPSizeTransformer()
	val animationJson = FileSystem.SYSTEM.read(pathToAnimation.toPath()) {
		readUtf8()
	}
	val json = Json {
		explicitNulls = false
		encodeDefaults = true
		ignoreUnknownKeys = true
	}
	val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
	return sizeTransformer.transformSize(lottieAnimation, AnimationSize(1080, 1080))
}