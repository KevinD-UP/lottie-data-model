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
import transformer.KPScaleTransformer
import transformer.Scale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KPScaleTransformerTest {

	@Test
	fun slideLeftRight_In_Out() {
		val sut = setupSUT("SlideLeftRight_In-Out")
		val controlPanelLayer = sut.layers[0] // control_panel is always at position 0
		assertScale(controlPanelLayer, KPMultiDimensionalList(
			listOf(
				KPMultiDimensionalNodePrimitive(JsonPrimitive(200)),
				KPMultiDimensionalNodePrimitive(JsonPrimitive(200)),
				KPMultiDimensionalNodePrimitive(JsonPrimitive(100))
			)
		))
	}
}

private fun KPScaleTransformerTest.assertScale(layer: KPLayer, expectedValue: KPMultiDimensionalListOrPrimitive) {
	assertTrue(layer is KPNullLayer)
	val k = layer.ks.s?.k
	assertEquals(expectedValue, k)
}

@OptIn(ExperimentalSerializationApi::class)
fun KPScaleTransformerTest.setupSUT(animatedText: String): KPLottieAnimation {
	val pathToAnimation = "src/commonTest/resources/animations/animatedText/$animatedText.json"
	val scaleTransformer = KPScaleTransformer()
	val animationJson = FileSystem.SYSTEM.read(pathToAnimation.toPath()) {
		readUtf8()
	}
	val json = Json {
		explicitNulls = false
		encodeDefaults = true
		ignoreUnknownKeys = true
	}
	val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
	return scaleTransformer.transformScale(lottieAnimation, Scale(200, 200, null))
}