package lottieAnimation

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.FunSpec
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path.Companion.toPath

class LottieAnimationTest: FunSpec({
    val animationsToTest = listOf<String>(
        "src/test/resources/lottiefiles-carnival-archery.json",
        "src/test/resources/lottiefiles-cube-shape-animation.json",
        "src/test/resources/lottiefiles-wine-is-love.json",
        "src/test/resources/lottiefiles-scale-loop-animation.json",
        "src/test/resources/lottiefiles-car-rides.json"
    )

    animationsToTest.forEach { filename ->
        test("it should be able to serialize / deserialize animation \"$filename\"") {
            val path = filename.toPath()
            val animationJson = FileSystem.SYSTEM.read(path) {
                readUtf8()
            }
            val lottieAnimation = Json.decodeFromString<LottieAnimation>(animationJson)
            val outputJson = Json{
                explicitNulls = false
                encodeDefaults = true
            }.encodeToString(lottieAnimation)
            animationJson.shouldEqualJson(outputJson)
        }
    }
})