package lottieAnimation

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalSerializationApi
class KPLottieAnimationTest {
    private fun animationsFor(theme: String): List<String> {
        return listOf(
            "src/commonTest/resources/animations/$theme/a.json",
            "src/commonTest/resources/animations/$theme/b.json",
            "src/commonTest/resources/animations/$theme/c.json",
            "src/commonTest/resources/animations/$theme/c2.json",
            "src/commonTest/resources/animations/$theme/d.json",
            "src/commonTest/resources/animations/$theme/e.json",
            "src/commonTest/resources/animations/$theme/f.json",
            "src/commonTest/resources/animations/$theme/h.json",
            "src/commonTest/resources/animations/$theme/i.json",
            "src/commonTest/resources/animations/$theme/j.json",
            "src/commonTest/resources/animations/$theme/k.json",
            "src/commonTest/resources/animations/$theme/l.json",
            "src/commonTest/resources/animations/$theme/m.json",
            "src/commonTest/resources/animations/$theme/n.json",
            "src/commonTest/resources/animations/$theme/o.json",
            "src/commonTest/resources/animations/$theme/p.json",
            "src/commonTest/resources/animations/$theme/q.json",
            "src/commonTest/resources/animations/$theme/r.json",
            "src/commonTest/resources/animations/$theme/s.json",
            "src/commonTest/resources/animations/$theme/t.json",
        )
    }

    private fun animationsToTest(animationsToTest: List<String>) {
        animationsToTest.forEach {filename ->
            val path = filename.toPath()
            println("path = $path")
            val animationJson = FileSystem.SYSTEM.read(path) {
                readUtf8()
            }
            val json = Json {
                explicitNulls = false
                encodeDefaults = true
            }
            val animationElement = json.parseToJsonElement(animationJson)
            val lottieAnimation = json.decodeFromString<KPLottieAnimation?>(animationJson)
            assertNotNull(lottieAnimation)
            val outputJson = json.encodeToString(lottieAnimation)
            val outputAnimationElement = json.parseToJsonElement(outputJson)
            println()
            println()
            println()
            println("outputAnimationElement = $outputAnimationElement")
            println()
            println()
            println()
            println("animationElement = $animationElement")
            println()
            println()
            println()
            assertEquals(outputAnimationElement, animationElement)
            println("path done = $path")
        }
    }

    @Test
    fun testDecodeAlgiers() {
        animationsToTest(animationsFor(theme = "algiers"))
    }

    @Test
    fun testDecodeBali() {
        animationsToTest(animationsFor(theme = "bali"))
    }

    @Test
    fun testDecodeBerlin() {
        animationsToTest(animationsFor(theme = "berlin"))
    }

    @Test
    fun testDecodeGeneva() {
        animationsToTest(animationsFor(theme = "geneva"))
    }

    @Test
    fun testDecodeLosAngeles() {
        animationsToTest(animationsFor(theme = "losAngeles"))
    }

    @Test
    fun testDecodeSeattle() {
        animationsToTest(animationsFor(theme = "seattle"))
    }

    @Test
    fun testDecodeKpTransformKey() {
        val animationsToTest = listOf<String>(
            "src/commonTest/resources/BALI-PLANE-kp_transform_key.json"
        )

        animationsToTest(animationsToTest)
    }

    @Test
    fun testDecode() {
        val animationsToTest = listOf<String>(
            "src/commonTest/resources/lottiefiles-carnival-archery.json",
            "src/commonTest/resources/lottiefiles-cube-shape-animation.json",
            "src/commonTest/resources/lottiefiles-wine-is-love.json",
            "src/commonTest/resources/lottiefiles-scale-loop-animation.json",
            "src/commonTest/resources/lottiefiles-car-rides.json"
        )

        animationsToTest(animationsToTest)
    }

    @Test
    fun testDecodeMarketplace() {
        val animationsToTest = listOf<String>(
            //"src/commonTest/resources/animations/marketplace/marketplace-lottie-anim-1.json",
            "src/commonTest/resources/animations/marketplace/marketplace-lottie-anim-2.json",
        )

        animationsToTest(animationsToTest)
    }

}

