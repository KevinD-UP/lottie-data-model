package lottieAnimation.transformer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPFontTransformer
import kotlin.test.*

val fonts = mapOf(
    "textBoldItalic" to "path/TestFont-TextBoldItalic.ttf",
    "titleBlackItalic" to "path/TestFont-TitleBlackItalic.ttf",
    "textBlackItalic" to "path/TestFont-TextBlackItalic.ttf",
    "textBold" to "path/TestFont-TextBold.ttf",
    "titleBoldItalic" to "path/TestFont-TitleBoldItalic.ttf",
    "titleBold" to "path/TestFont-TitleBold.ttf",
    "textBlack" to "path/TestFont-TextBlack.ttf",
    "text" to "path/TestFont-Text.ttf",
    "title" to "path/TestFont-Title.ttf",
)

class KPFontTransformerTest {
    @Test
    fun testAlgiersFord() {
        val sut = setupSUT("ALGIERS-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBoldItalic"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBoldItalic"))
    }

    @Test
    fun testAlgiersPeugeot() {
        val sut = setupSUT("ALGIERS-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBoldItalic"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBoldItalic"))
    }

    @Test
    fun testAlgiersPlane() {
        val sut = setupSUT("ALGIERS-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("title"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("title"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBold"))
        val textLayer4 = sut.layers[4]
        assertTextLayerFont(textLayer4, fontName("text"))
    }

    @Test
    fun testAlgiersSimca() {
        val sut = setupSUT("ALGIERS-SIMCA")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBoldItalic"))
    }

    @Test
    fun testBaliFord() {
        val sut = setupSUT("BALI-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
    }

    @Test
    fun testBaliPeugeot() {
        val sut = setupSUT("BALI-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBold"))
    }

    @Test
    fun testBaliPlane() {
        val sut = setupSUT("BALI-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("title"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("title"))
        val textLayer6 = sut.layers[6]
        assertTextLayerFont(textLayer6, fontName("textBold"))
        val textLayer7 = sut.layers[7]
        assertTextLayerFont(textLayer7, fontName("text"))
    }

    @Test
    fun testBerlinFord() {
        val sut = setupSUT("BERLIN-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
    }

    @Test
    fun testBerlinPeugeot() {
        val sut = setupSUT("BERLIN-PEUGEOT")
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBold"))
        val textLayer3 = sut.layers[3]
        assertTextLayerFont(textLayer3, fontName("textBold"))
    }

    @Test
    fun testBerlinPlane() {
        val sut = setupSUT("BERLIN-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("text"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBold"))
    }

    @Test
    fun testGenevaFord() {
        val sut = setupSUT("GENEVA-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBlackItalic"))
    }

    @Test
    fun testGenevaPeugeot() {
        val sut = setupSUT("GENEVA-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBlackItalic"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBlackItalic"))
    }

    @Test
    fun testGenevaPlane() {
        val sut = setupSUT("GENEVA-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("title"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("title"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBlack"))
        val textLayer4 = sut.layers[4]
        assertTextLayerFont(textLayer4, fontName("text"))
    }

    @Test
    fun testLosAngelesFord() {
        val sut = setupSUT("LOS_ANGELES-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("text"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("text"))
    }

    @Test
    fun testLosAngelesPeugeot() {
        val sut = setupSUT("LOS_ANGELES-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("text"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("text"))
    }

    @Test
    fun testLosAngelesPlane() {
        val sut = setupSUT("LOS_ANGELES-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("title"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("title"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("text"))
        val textLayer3 = sut.layers[3]
        assertTextLayerFont(textLayer3, fontName("text"))
    }

    @Test
    fun testLosAngelesSimca() {
        val sut = setupSUT("LOS_ANGELES-SIMCA")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("text"))
    }

    @Test
    fun testSeattleFord() {
        val sut = setupSUT("SEATTLE-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("text"))
    }

    @Test
    fun testSeattlePeugeot() {
        val sut = setupSUT("SEATTLE-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBold"))
    }

    @Test
    fun testSeattlePlane() {
        val sut = setupSUT("SEATTLE-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("title"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("title"))
        val textLayer3 = sut.layers[3]
        assertTextLayerFont(textLayer3, fontName("textBold"))
        val textLayer6 = sut.layers[6]
        assertTextLayerFont(textLayer6, fontName("text"))
    }

    @Test
    fun testParisButterfly() {
        val sut = setupSUT("PARIS-BUTTERFLY")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBold"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBold"))
    }

    @Test
    fun testParisFox() {
        val sut = setupSUT("PARIS-FOX")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("textBold"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBold"))
        val textLayer3 = sut.layers[3]
        assertTextLayerFont(textLayer3, fontName("textBold"))
        val textLayer4 = sut.layers[4]
        assertTextLayerFont(textLayer4, fontName("textBold"))
        val textLayer5 = sut.layers[5]
        assertTextLayerFont(textLayer5, fontName("textBold"))
        val textLayer6 = sut.layers[6]
        assertTextLayerFont(textLayer6, fontName("textBold"))
    }

    @Test
    fun testParisOwl() {
        val sut = setupSUT("PARIS-OWL")
        val textLayer0 = sut.layers[0]
        assertTextLayerFont(textLayer0, fontName("textBold"))
        val textLayer1 = sut.layers[1]
        assertTextLayerFont(textLayer1, fontName("text"))
        val textLayer2 = sut.layers[2]
        assertTextLayerFont(textLayer2, fontName("textBold"))
    }
}

fun fontName(key: String): String {
    val fontPath = fonts[key]
    assertNotNull(fontPath)
    return fontPath.substringAfterLast("/").substringBefore(".")
}

fun assertTextLayerFont(layer: KPLayer, expectedValue: String) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.t.d.k
    kList.forEach {
        assertEquals(expectedValue, it.s.f)
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun setupSUT(
    animationName: String
): KPLottieAnimation {
    val pathToAnimation = "src/commonTest/resources/animations/$animationName.json"
    val pathToAnimationRules =
        "src/commonTest/resources/animations/$animationName-rules.json"
    val fontTransformer = KPFontTransformer()
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
    return fontTransformer.transformFonts(lottieAnimation, animationRules, fonts)
}