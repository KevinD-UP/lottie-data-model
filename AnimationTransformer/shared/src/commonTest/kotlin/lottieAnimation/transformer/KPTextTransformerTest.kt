package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPTextTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class KPTextTransformerTest {

    @Test
    fun testTextTransformation() {
        val pathToAnimationAlgier = "src/commonTest/resources/animations/algiers/a.json"
        val pathToAnimationRulesAlgiers =
            "src/commonTest/resources/rules/algiers/ALGIERS-FORD-rules.json"
        val textTransformer = KPTextTransformer()
        val animationJson = FileSystem.SYSTEM.read(pathToAnimationAlgier.toPath()) {
            readUtf8()
        }
        val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRulesAlgiers.toPath()) {
            readUtf8()
        }
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
        }
        val baseLottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
        val texts = listOf("New text1", "New text2")
        val res = textTransformer.transformTexts(lottieAnimation, animationRules, texts)
        assertNotEquals(baseLottieAnimation, res)
    }

    @Test
    fun testFontTransformationNothing() {
        val pathToAnimationAlgier = "src/commonTest/resources/animations/algiers/a.json"
        val pathToAnimationRulesAlgiers =
            "src/commonTest/resources/rules/algiers/ALGIERS-FORD-rules.json"
        val textTransformer = KPTextTransformer()
        val animationJson = FileSystem.SYSTEM.read(pathToAnimationAlgier.toPath()) {
            readUtf8()
        }
        val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRulesAlgiers.toPath()) {
            readUtf8()
        }
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
        }
        val baseLottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
        val res = textTransformer.transformTexts(lottieAnimation, animationRules, emptyList())
        assertEquals(baseLottieAnimation, res)
    }
}