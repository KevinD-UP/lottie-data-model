package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.LottieAnimation
import lottieAnimation.rules.properties.AnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertNotEquals


class FontTransformerTest {

  @Test
  fun testFontTransformation() {
    val pathToAnimationAlgier = "src/commonTest/resources/animations/algiers/a.json"
    val pathToAnimationRulesAlgiers = "src/commonTest/resources/rules/algiers/ALGIERS-FORD-rules.json"
    val fontTransformer = FontTransformer()
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
    val lottieAnimation = json.decodeFromString<LottieAnimation>(animationJson)
    val animationRules = json.decodeFromString<AnimationRules>(animationRulesJson)
    val res = fontTransformer.transformFonts(lottieAnimation, animationRules)
    println(json.encodeToString(lottieAnimation))
    println(json.encodeToString(res))
    assertNotEquals(lottieAnimation, res)
  }
}