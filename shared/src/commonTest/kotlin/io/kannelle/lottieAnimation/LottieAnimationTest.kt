package io.kannelle.lottieAnimation

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import lottieAnimation.LottieAnimation
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LottieAnimationTest {
    @Test
    fun testDecode() {
        val animationsToTest = listOf<String>(
            "src/commonTest/resources/lottiefiles-carnival-archery.json",
            "src/commonTest/resources/lottiefiles-cube-shape-animation.json",
            "src/commonTest/resources/lottiefiles-wine-is-love.json",
            "src/commonTest/resources/lottiefiles-scale-loop-animation.json",
            "src/commonTest/resources/lottiefiles-car-rides.json"
        )

        animationsToTest.forEach {filename ->
            val path = filename.toPath()
            val animationJson = FileSystem.SYSTEM.read(path) {
                readUtf8()
            }
            val json = Json{
                explicitNulls = false
                encodeDefaults = true
            }
            val animationElement = json.parseToJsonElement(animationJson)
            val lottieAnimation = json.decodeFromString<LottieAnimation?>(animationJson)
            assertNotNull(lottieAnimation)
            val outputJson = json.encodeToString(lottieAnimation)
            val outputAnimationElement = json.parseToJsonElement(outputJson)
            assertEquals(outputAnimationElement, animationElement)
        }
    }
}

