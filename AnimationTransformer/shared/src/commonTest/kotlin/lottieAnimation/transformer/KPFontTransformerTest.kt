package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules
import lottieAnimation.transformer.variableTransformer.AnimationTransformerFunctionsDelegateMock
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPFontTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class KPFontTransformerTest {

    val fontFixture = mapOf(
        "textBoldItalic" to "path-to-font/Arial-Black.ttf",
        "titleBlackItalic" to "path-to-font/Arial-Bold.ttf",
        "textBlackItalic" to "path-to-font/Arial-Black.ttf",
        "textBold" to "path-to-font/Arial-Black.ttf",
        "titleBoldItalic" to "path-to-font/Arial-Bold.ttf",
        "titleBold" to "path-to-font/Arial-Bold.ttf",
        "textBlack" to "path-to-font/Arial-Black.ttf",
        "text" to "path-to-font/Arial-Black.ttf",
        "title" to "path-to-font/Arial-Bold.ttf",
    )

    @Test
    fun testFontTransformation() {
        val pathToAnimationAlgier = "src/commonTest/resources/animations/algiers/a.json"
        val pathToAnimationRulesAlgiers =
            "src/commonTest/resources/rules/algiers/ALGIERS-FORD-rules.json"
        val functionsDelegateMock = AnimationTransformerFunctionsDelegateMock()
        val fontTransformer = KPFontTransformer(functionsDelegateMock)
        val animationJson = FileSystem.SYSTEM.read(pathToAnimationAlgier.toPath()) {
            readUtf8()
        }
        val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRulesAlgiers.toPath()) {
            readUtf8()
        }
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val baseLottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
        val res = fontTransformer.transformFonts(lottieAnimation, animationRules, fontFixture)
        assertNotEquals(baseLottieAnimation, res)
    }

    @Test
    fun testFontTransformationNothing() {
        val pathToAnimationAlgier = "src/commonTest/resources/animations/algiers/a.json"
        val pathToAnimationRulesAlgiers =
            "src/commonTest/resources/rules/algiers/ALGIERS-FORD-rules.json"
        val functionsDelegateMock = AnimationTransformerFunctionsDelegateMock()
        val fontTransformer = KPFontTransformer(functionsDelegateMock)
        val animationJson = FileSystem.SYSTEM.read(pathToAnimationAlgier.toPath()) {
            readUtf8()
        }
        val animationRulesJson = FileSystem.SYSTEM.read(pathToAnimationRulesAlgiers.toPath()) {
            readUtf8()
        }
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val baseLottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val lottieAnimation = json.decodeFromString<KPLottieAnimation>(animationJson)
        val animationRules = json.decodeFromString<KPAnimationRules>(animationRulesJson)
        val res = fontTransformer.transformFonts(lottieAnimation, animationRules)
        assertEquals(baseLottieAnimation, res)
    }
}