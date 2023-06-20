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
import transformer.KPTextTransformer
import kotlin.test.*

val texts = listOf(
    "Test-text-1",
    "test-text-2",
    "TEST-text-3",
    "TesT-Text4"
)

class KPTextTransformerTest {

    @Test
    fun testAlgiersFord() {
        val sut = setupSUT("ALGIERS-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testAlgiersPeugeot() {
        val sut = setupSUT("ALGIERS-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testAlgiersPlane() {
        val sut = setupSUT("ALGIERS-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(2)))
        val textLayer4 = sut.layers[4]
        assertTextLayerText(textLayer4, expectedText(listOf(3)))
    }

    @Test
    fun testAlgiersSimca() {
        val sut = setupSUT("ALGIERS-SIMCA")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
    }

    @Test
    fun testBaliFord() {
        val sut = setupSUT("BALI-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0,1)))
    }

    @Test
    fun testBaliPeugeot() {
        val sut = setupSUT("BALI-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testBaliPlane() {
        val sut = setupSUT("BALI-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer6 = sut.layers[6]
        assertTextLayerText(textLayer6, expectedText(listOf(2)))
        val textLayer7 = sut.layers[7]
        assertTextLayerText(textLayer7, expectedText(listOf(3)))
    }

    @Test
    fun testBerlinFord() {
        val sut = setupSUT("BERLIN-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0,1)))
    }

    @Test
    fun testBerlinPeugeot() {
        val sut = setupSUT("BERLIN-PEUGEOT")
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer3 = sut.layers[3]
        assertTextLayerText(textLayer3, expectedText(listOf(0)))
    }

    @Test
    fun testBerlinPlane() {
        val sut = setupSUT("BERLIN-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(2)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(3)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(0,1)))
    }

    @Test
    fun testGenevaFord() {
        val sut = setupSUT("GENEVA-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0,1)))
    }

    @Test
    fun testGenevaPeugeot() {
        val sut = setupSUT("GENEVA-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(1)))
    }

    @Test
    fun testGenevaPlane() {
        val sut = setupSUT("GENEVA-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(2)))
        val textLayer4 = sut.layers[4]
        assertTextLayerText(textLayer4, expectedText(listOf(3)))
    }

    @Test
    fun testLosAngelesFord() {
        val sut = setupSUT("LOS_ANGELES-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testLosAngelesPeugeot() {
        val sut = setupSUT("LOS_ANGELES-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testLosAngelesPlane() {
        val sut = setupSUT("LOS_ANGELES-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(2)))
        val textLayer3 = sut.layers[3]
        assertTextLayerText(textLayer3, expectedText(listOf(3)))
    }

    @Test
    fun testLosAngelesSimca() {
        val sut = setupSUT("LOS_ANGELES-SIMCA")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
    }

    @Test
    fun testSeattleFord() {
        val sut = setupSUT("SEATTLE-FORD")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0,1)))
    }

    @Test
    fun testSeattlePeugeot() {
        val sut = setupSUT("SEATTLE-PEUGEOT")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
    }

    @Test
    fun testSeattlePlane() {
        val sut = setupSUT("SEATTLE-PLANE")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer3 = sut.layers[3]
        assertTextLayerText(textLayer3, expectedText(listOf(2)))
        val textLayer6 = sut.layers[6]
        assertTextLayerText(textLayer6, expectedText(listOf(3)))
    }

    @Test
    fun testParisButterfly() {
        val sut = setupSUT("PARIS-BUTTERFLY")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(0)))
    }

    @Test
    fun testParisFox() {
        val sut = setupSUT("PARIS-FOX")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0,1), "\n"))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(0,0,0,0)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(0,0,0,0)))
        val textLayer3 = sut.layers[3]
        assertTextLayerText(textLayer3, expectedText(listOf(0,0,0,0)))
        val textLayer4 = sut.layers[4]
        assertTextLayerText(textLayer4, expectedText(listOf(0,0,0,0)))
        val textLayer5 = sut.layers[5]
        assertTextLayerText(textLayer5, expectedText(listOf(0,0,0,0)))
        val textLayer6 = sut.layers[6]
        assertTextLayerText(textLayer6, expectedText(listOf(0,0,0,0)))
    }

    @Test
    fun testParisOwl() {
        val sut = setupSUT("PARIS-OWL")
        val textLayer0 = sut.layers[0]
        assertTextLayerText(textLayer0, expectedText(listOf(0)))
        val textLayer1 = sut.layers[1]
        assertTextLayerText(textLayer1, expectedText(listOf(1)))
        val textLayer2 = sut.layers[2]
        assertTextLayerText(textLayer2, expectedText(listOf(0)))
    }
}

private fun KPTextTransformerTest.parseTextKey(texts: List<String>, textInds: List<Int>?, separator: String): String? {
    return textInds?.joinToString(separator = separator) { index ->
        texts.getOrNull(index) ?: ""
    }?.trim().takeIf { it?.isNotEmpty() ?: false }
}

private fun KPTextTransformerTest.expectedText(textInds: List<Int>, separator: String = " "): String {
    val text = parseTextKey(texts, textInds, separator)
    assertNotNull(text)
    return text
}

private fun KPTextTransformerTest.assertTextLayerText(layer: KPLayer, expectedValue: String) {
    assertTrue(layer is KPTextLayer)
    val kList = layer.t.d.k
    kList.forEach {
        assertEquals(expectedValue, it.s.t)
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun KPTextTransformerTest.setupSUT(
    animationName: String
): KPLottieAnimation {
    val pathToAnimation = "src/commonTest/resources/animations/$animationName.json"
    val pathToAnimationRules =
        "src/commonTest/resources/animations/$animationName-rules.json"
    val textTransformer = KPTextTransformer()
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
    return textTransformer.transformTexts(lottieAnimation, animationRules, texts)
}