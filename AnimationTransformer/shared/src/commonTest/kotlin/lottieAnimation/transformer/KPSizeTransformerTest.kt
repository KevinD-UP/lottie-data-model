package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.AnimationSize
import transformer.KPSizeTransformer
import kotlin.test.Test
import kotlin.test.assertEquals

class KPSizeTransformerTest {
	@Test
	fun slideLeftRight_In_Out() {
		val sut = setupSUT("SlideLeftRight_In-Out")
		assertSize(sut, AnimationSize(1080, 1080)
		)
	}
}

private fun KPSizeTransformerTest.assertSize(animation: KPLottieAnimation, expectedValue: AnimationSize) {
	assertEquals(animation.w, expectedValue.width)
	assertEquals(animation.h, expectedValue.height)
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