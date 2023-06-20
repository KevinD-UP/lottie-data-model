package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.*
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import transformer.KPColorTransformer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KPColorTransformerTest {

    val json = Json {
        prettyPrint = true
        explicitNulls = false
        encodeDefaults = true
    }

    // ---------------------- TESTS ----------------------
    @Test
    fun testBaliPlane() {
        val animation: KPLottieAnimation = json.decodeFromString(readJson("src/commonTest/resources/Animations/BALI-PLANE.json"))
        val rule: KPAnimationRules = json.decodeFromString(readJson("src/commonTest/resources/Animations/BALI-PLANE-rules.json"))
        val colors = colorSetBTS()["bali"]!!
        val sut = KPColorTransformer()
        val result = sut.transformColor(animation, rule, colors)
        println("colors: $colors")
        // ------ Opacity -----
        val opacity = (((result.layers.firstOrNull { 9 == it.ind } as KPVisualLayer).ks.o as KPMultiDimensionalSimple).k as KPMultiDimensionalPrimitive).value.double
        val expectedOpacity = colors["backgroundOpacity"]?.toDoubleOrNull()?.times(100f)
        assertEquals(expectedOpacity, opacity)

        val titleBackgroundOpacity = (((result.layers.firstOrNull { 10 == it.ind } as KPVisualLayer).ks.o as KPMultiDimensionalSimple).k as KPMultiDimensionalPrimitive).value.double
        val expectedTitleBackgroundOpacity = colors["titleBackgroundOpacity"]?.toDoubleOrNull()?.times(100f)
        assertEquals(expectedTitleBackgroundOpacity, titleBackgroundOpacity)
        // ------- Title 1 --------
        testTextFor(1, colors["titleText"] ?: "", result)
        // ------- Title 2 --------
        testTextFor(2, colors["titleText"] ?: "", result)
        // -------- Shape 1 -------
        val shape1Color = (((result.layers.firstOrNull { 3 == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeStroke } as KPShapeStroke).c?.k?.map { it.float }
        val expectedShape1Color = argbStringToFloatArray(colors["shape"] ?: "")?.toMutableList()
        expectedShape1Color?.add(1.0f)
        assertEquals(expectedShape1Color, shape1Color)
        // -------- Shape 2 -------
        val shape2Color = (((result.layers.firstOrNull { 4 == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeStroke } as KPShapeStroke).c?.k?.map { it.float }
        val expectedShape2Color = argbStringToFloatArray(colors["shape"] ?: "")?.toMutableList()
        expectedShape2Color?.add(1.0f)
        assertEquals(expectedShape2Color, shape2Color)
        // -------- Shape 3 -------
        val shape3Color = (((result.layers.firstOrNull { 5 == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeStroke } as KPShapeStroke).c?.k?.map { it.float }
        val expectedShape3Color = argbStringToFloatArray(colors["shape"] ?: "")?.toMutableList()
        expectedShape3Color?.add(1.0f)
        assertEquals(expectedShape3Color, shape3Color)
        // -------- Shape 4 -------
        val shape4Color = (((result.layers.firstOrNull { 6 == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeStroke } as KPShapeStroke).c?.k?.map { it.float }
        val expectedShape4Color = argbStringToFloatArray(colors["shape"] ?: "")?.toMutableList()
        expectedShape4Color?.add(1.0f)
        assertEquals(expectedShape4Color, shape4Color)
        // ------- Text 1 --------
        testTextFor(7, colors["text"] ?: "", result)
        // ------- Text 2 --------
        testTextFor(8, colors["text"] ?: "", result)
        // -------- background 1 -------
        val background1Color = ((((result.layers.firstOrNull { 9 == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeFill } as KPShapeFill).c.k as KPMultiDimensionalList).values.map {
            it as KPMultiDimensionalNodePrimitive
            it.value.float
        }
        val expectedBackground1Color = argbStringToFloatArray(colors["background"] ?: "")?.toMutableList() ?: mutableListOf()
        expectedBackground1Color.add(1.0f)
        assertEquals(expectedBackground1Color, background1Color)
        // -------- titleBackground 2 -------
        val titleBackground2Color = (result.layers.firstOrNull { 10 == it.ind } as KPSolidLayer).sc
        val expectedTitleBackground2Color = colors["titleBackground"] ?: ""
        assertEquals(expectedTitleBackground2Color, titleBackground2Color)
    }

    @Test
    fun testAlgiersFord() {
        val animation: KPLottieAnimation = json.decodeFromString(readJson("src/commonTest/resources/Animations/ALGIERS-FORD.json"))
        val rule: KPAnimationRules = json.decodeFromString(readJson("src/commonTest/resources/Animations/ALGIERS-FORD-rules.json"))
        val colors = colorSetBTS()["algiers"]!!
        val sut = KPColorTransformer()
        val result = sut.transformColor(animation, rule, colors)
        println("colors: $colors")
        // ------- Text --------
        testTextFor(1, colors["gradientText"] ?: "", result)
        testTextFor(2, colors["gradientText"] ?: "", result)
        // -------- Gradient --------
        testGradientColor(3, rule.layerRules[2].gradientColorKey!!, colors, result)
    }

    @Test
    fun testBogotaAustin() {
        val animation: KPLottieAnimation = json.decodeFromString(readJson("src/commonTest/resources/Animations/BOGOTA-AUSTIN.json"))
        val rule: KPAnimationRules = json.decodeFromString(readJson("src/commonTest/resources/Animations/BOGOTA-AUSTIN-rules.json"))
        val colors = colorSetBTS()["bogota"]!!
        val sut = KPColorTransformer()
        val result = sut.transformColor(animation, rule, colors)
        println("colors: $colors")
        testTextFor(4, colors["title"] ?: "", result)
        testFillColorKey(4, colors["title"] ?: "", colors["titleBlinking"] ?: "", result)
        testTextFor(6, colors["text"] ?: "", result)
        testTextFor(8, colors["text"] ?: "", result)
        testTextFor(9, colors["figure"] ?: "", result)
        testTextFor(19, colors["figure"] ?: "", result)
        testTextFor(29, colors["sourceNeutral"] ?: "", result)
        testShapeColor(12, colors["negativeBarBottom"] ?: "", result)
        testShapeColor(22, colors["negativeBarTop"] ?: "", result)
        testShapeColor(16, colors["positiveBarBottom"] ?: "", result)
        testShapeColor(26, colors["positiveBarTop"] ?: "", result)
        testOpacity(14, colors["backgroundBarOpacity"] ?: "", result)
        testOpacity(18, colors["backgroundBarOpacity"] ?: "", result)
        testOpacity(24, colors["backgroundBarOpacity"] ?: "", result)
        testOpacity(28, colors["backgroundBarOpacity"] ?: "", result)
        testShapeColor(32, colors["transparentBackground"] ?: "", result)
        testOpacity(32, colors["backgroundOpacity"] ?: "", result)
    }

    @Test
    fun testBogotaAventi() {
        val animation: KPLottieAnimation = json.decodeFromString(readJson("src/commonTest/resources/Animations/BOGOTA-AVENTI.json"))
        val rule: KPAnimationRules = json.decodeFromString(readJson("src/commonTest/resources/Animations/BOGOTA-AVENTI-rules.json"))
        val colors = colorSetBTS()["bogota"]!!
        val sut = KPColorTransformer()
        val result = sut.transformColor(animation, rule, colors)
        println("colors: $colors")
        testTextFor(4, colors["cta"] ?: "", result)
        testTextFor(5, colors["ctaTitle"] ?: "", result)
        testFillColorKey(5, colors["ctaTitle"] ?: "", colors["ctaTitleBlinking"] ?: "", result)
        testBackground(7, colors["background"] ?: "", result)
        testBackground(9, colors["transparentBackground"] ?: "", result)
    }

    private fun testBackground(layerInd: Int, color: String, result: KPLottieAnimation) {
        val background1Color =
            ((((result.layers.firstOrNull { layerInd == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeFill } as KPShapeFill).c.k as KPMultiDimensionalList).values.map {
                it as KPMultiDimensionalNodePrimitive
                it.value.float
            }
        val expectedBackground1Color = argbStringToFloatArray(color)?.toMutableList() ?: mutableListOf()
        expectedBackground1Color.add(1.0f)
        assertEquals(expectedBackground1Color, background1Color)
    }

    private fun testOpacity(layerInd: Int, color: String, result: KPLottieAnimation) {
        val opacity = (((result.layers.firstOrNull { layerInd == it.ind } as KPVisualLayer).ks.o as KPMultiDimensionalSimple).k as KPMultiDimensionalPrimitive).value.double
        val expectedOpacity = color.toDoubleOrNull()?.times(100f)
        assertEquals(expectedOpacity, opacity)
    }

    private fun testShapeColor(layerInd: Int, color: String, result: KPLottieAnimation) {
        val shape4Color = (((result.layers.firstOrNull { layerInd == it.ind } as KPShapeLayer).shapes?.firstOrNull { it is KPShapeGroup } as KPShapeGroup).it.firstOrNull { it is KPShapeStroke } as KPShapeStroke).c?.k?.map { it.float }
        val expectedShape4Color = argbStringToFloatArray(color)?.toMutableList()
        expectedShape4Color?.add(1.0f)
        assertEquals(expectedShape4Color, shape4Color)
    }

    private fun testShadowOpacity(layerInd: Int, color: String, result: KPLottieAnimation) {
        val expectedOpacity = color.toDoubleOrNull()?.times(100f)
        val colorResult = ((result.layers.firstOrNull { layerInd == it.ind } as KPVisualLayer).ef?.firstOrNull { it.ty == 25 }?.ef?.firstOrNull { it.ty == 0 }?.v?.k as KPMultiDimensionalPrimitive).value.double
        assertEquals(expectedOpacity, colorResult)
    }

    private fun testFillColorKey(layerInd: Int, color: String, fillColor: String, result: KPLottieAnimation) {
        val expectedColorArray = argbStringToFloatArray(color)?.toList()
        val expectedFillColorArray = argbStringToFloatArray(fillColor)?.toList()
        val fc = (result.layers.firstOrNull { layerInd == it.ind } as KPTextLayer).t.d.k.first().s.fc
        val currentLayerColor = floatArrayOf(
            fc[0].jsonPrimitive.float,
            fc[1].jsonPrimitive.float,
            fc[2].jsonPrimitive.float
        )
        ((result.layers.firstOrNull { layerInd == it.ind } as KPVisualLayer).ef?.firstOrNull { it.ty == 21 }?.ef?.firstOrNull { it.ty == 2 }?.v?.k as KPMultiDimensionalList).values.forEach {
            it as KPMultiDimensionNodeObject
            if (it.s != null) {
                if (it.s!!.toRGBFloatArray.contentEquals(currentLayerColor)) {
                    assertEquals(expectedColorArray, it.s!!.map { it.jsonPrimitive.float }.toList())
                } else {
                    assertEquals(expectedFillColorArray, it.s!!.map { it.jsonPrimitive.float }.toList())
                }

            }

            if (it.e != null) {
                if (it.e!!.toRGBFloatArray.contentEquals(currentLayerColor)) {
                    assertEquals(expectedColorArray, it.e!!.map { it.jsonPrimitive.float }.toList())
                } else {
                    assertEquals(expectedFillColorArray, it.e!!.map { it.jsonPrimitive.float }.toList())
                }
            }
        }
    }

    private fun testGradientColor(layerInd: Int, gradientColorKey: List<String>, colors: Map<String, String>, result: KPLottieAnimation) {
        gradientColorKey.forEachIndexed { _, _ ->
            val gradientColor = ((((result.layers.firstOrNull { layerInd == it.ind } as KPShapeLayer)
                .shapes?.get(0) as KPShapeGroup)
                .it.firstOrNull { it is KPShapeGFill } as KPShapeGFill?)?.g?.k?.k as KPMultiDimensionalList)
                .values.map {
                    it as KPMultiDimensionalNodePrimitive
                    it.value.float
                }.count()
            assertTrue(gradientColor > 10)
        }
    }

    private fun testTextFor(layerInd: Int, color: String, result: KPLottieAnimation) {
        val text1Color = (result.layers.firstOrNull { layerInd == it.ind } as KPTextLayer).t.d.k.first().s.fc.map { it.jsonPrimitive.float }
        val expectedText1Color = argbStringToFloatArray(color)?.toList()
        assertEquals(expectedText1Color, text1Color)
    }

    private fun argbStringToFloatArray(argbString: String): FloatArray? {
        return try {
            val argb = argbString.trim().removePrefix("#")

            // Extract the individual RGB components
            val red: Int
            val green: Int
            val blue: Int
            val alpha: Int

            when (argb.length) {
                6 -> {
                    // 6-digit ARGB string (add default alpha FF)
                    red = argb.substring(0, 2).toInt(16)
                    green = argb.substring(2, 4).toInt(16)
                    blue = argb.substring(4, 6).toInt(16)
                    alpha = 255 // Default alpha value FF (fully opaque)
                }
                8 -> {
                    // 8-digit ARGB string
                    alpha = argb.substring(0, 2).toInt(16)
                    red = argb.substring(2, 4).toInt(16)
                    green = argb.substring(4, 6).toInt(16)
                    blue = argb.substring(6, 8).toInt(16)
                }
                else -> {
                    throw IllegalArgumentException("Invalid ARGB string: $argbString")
                }
            }

            // Normalize the component values to the range of 0.0 to 1.0
            val floatAlpha = alpha.toFloat() / 255
            val floatRed = red.toFloat() / 255
            val floatGreen = green.toFloat() / 255
            val floatBlue = blue.toFloat() / 255

            // Create and return the float array
            floatArrayOf(floatRed, floatGreen, floatBlue)
        } catch (e: Exception) {
            println("Orpheus: Error Converting ARGB to FloatArray $e")
            null
        }

    }

    private fun readJson(path: String): String {
        return FileSystem.SYSTEM.read(path.toPath()) { readUtf8() }
    }

    private val JsonArray.toRGBFloatArray: FloatArray
        get() {
            val list = mutableListOf<Float>()
            for (i in 0 until count()) {
                if (i < 3) {
                    list.add(i, this[i].jsonPrimitive.float)
                }
            }
            return list.toFloatArray()

        }

    private fun colorSetBTS(): Map<String, Map<String, String>> {
        val bogotaColors = mapOf(
            "coverTextOpacity" to "0.50",
            "shapeShadow" to "#000000",
            "negativeBarBottom" to "#FFFFFF",
            "coverText" to "#FFFFFF",
            "backgroundOpacity" to "1.00",
            "titleBlinking" to "#00e183",
            "source" to "#00e183",
            "title" to "#FFFFFF",
            "positiveBarTop" to "#00e183",
            "positiveBarBottom" to "#00e183",
            "shapeNeutral" to "#FFFFFF",
            "cta" to "#FFFFFF",
            "negativeBarTop" to "#FFFFFF",
            "ctaTitle" to "#FFFFFF",
            "transparentBackground" to "#000000",
            "maskGradientBottom" to "#00e183",
            "maskGradientTop" to "#00e183",
            "text" to "#FFFFFF",
            "backgroundBarOpacity" to "0.20",
            "textShadow" to "#000000",
            "figure" to "#FFFFFF",
            "shapeCosmeticOpacity" to "0.70",
            "titleBottom" to "#FFFFFF",
            "shapeCosmetic" to "#FFFFFF",
            "chartEmpty" to "#FFFFFF",
            "background" to "#F5D809",
            "shapeSecondary" to "#F5D809",
            "textShadowOpacity" to "0.70",
            "chartFill" to "#00e183",
            "shapeMain" to "#00e183",
            "sourceNeutral" to "#FFFFFF",
            "figureBlink" to "#00e183",
            "shapeShadowOpacity" to "0.70",
            "ctaTitleBlinking" to "#00e183"
        )
        val berlinColors = mapOf(
            "gradientTop" to "#00e183",
            "neutralGradientTop" to "#FFFFFF",
            "titleGradientTop" to "#00e183",
            "textBackgroundOpacity" to "1.00",
            "shapeSecond" to "#F5D809",
            "neutralGradientBottom" to "#000000",
            "shapeNeutral" to "#FFFFFF",
            "background" to "#F5D809",
            "titleGradientBottom" to "#F5D809",
            "titleBackgroundOpacity" to "0.80",
            "slideBackground" to "#FFFFFF",
            "maskGradientBottom" to "#F5D809",
            "maskGradientTop" to "#00e183",
            "textBackground" to "#F5D809",
            "gradientBottom" to "#F5D809",
            "text" to "#FFFFFF",
            "shapeMain" to "#00e183",
            "titleBackground" to "#000000"
        )
        val genevaColors = mapOf(
            "shape" to "#FFFFFF",
            "shadow" to "#000000",
            "background" to "#F5D809",
            "titleText" to "#FFFFFF",
            "titleBackgroundOpacity" to "0.50",
            "maskGradientBottom" to "#00e183",
            "maskGradientTop" to "#00e183",
            "text" to "#FFFFFF",
            "shadowOpacity" to "0.70",
            "titleBackground" to "#000000"
        )
        val losAngelesColors = mapOf(
            "shadow" to "#000000",
            "slideTextStrong" to "#00e183",
            "subtitleBottomText" to "#FFFFFF",
            "subtitleTopText" to "#FFFFFF",
            "shadowOpacity" to "0.50",
            "background" to "#F5D809",
            "titleText" to "#FFFFFF",
            "titleBackgroundOpacity" to "0.70",
            "slideBackground" to "#FFFFFF",
            "floatingBackground" to "#F5D809",
            "slideText" to "#F5D809",
            "maskGradientTop" to "#00e183",
            "maskGradientBottom" to "#F5D809",
            "text" to "#FFFFFF",
            "floatingText" to "#FFFFFF",
            "subtitleBackground" to "#F5D809",
            "titleBackground" to "#000000"
        )
        val seattleColors = mapOf(
            "subtitleBottomBackground" to "#F5D809",
            "shape" to "#F5D809",
            "slideFullBackground" to "#00e183",
            "slideSplitBottomText" to "#00e183",
            "subtitleBottomText" to "#FFFFFF",
            "slideSplitBottomBackground" to "#FFFFFF",
            "subtitleTopText" to "#F5D809",
            "background" to "#F5D809",
            "slideSplitTopText" to "#FFFFFF",
            "slideFullText" to "#FFFFFF",
            "titleText" to "#FFFFFF",
            "titleBackgroundOpacity" to "0.80",
            "floatingBackground" to "#FFFFFF",
            "maskGradientBottom" to "#F5D809",
            "maskGradientTop" to "#F5D809",
            "text" to "#FFFFFF",
            "slideSplitTopBackground" to "#00e183",
            "floatingText" to "#F5D809",
            "subtitleTopBackground" to "#FFFFFF",
            "titleBackground" to "#000000"
        )
        val baliColors = mapOf(
            "shape" to "#00e183",
            "background" to "#F5D809",
            "titleText" to "#FFFFFF",
            "titleBackgroundOpacity" to "0.70",
            "backgroundOpacity" to "0.50",
            "maskGradientBottom" to "#F5D809",
            "maskGradientTop" to "#F5D809",
            "text" to "#FFFFFF",
            "titleBackground" to "#000000"
        )
        val algiersColors = mapOf(
            "subtitleBottomBackground" to "#00e183",
            "slideSplitBottomText" to "#FFFFFF",
            "subtitleBottomText" to "#FFFFFF",
            "gradientRight" to "#F5D809",
            "slideSplitBottomBackground" to "#F5D809",
            "subtitleTopText" to "#FFFFFF",
            "gradientText" to "#FFFFFF",
            "background" to "#F5D809",
            "slideSplitTopText" to "#FFFFFF",
            "titleText" to "#FFFFFF",
            "titleBackgroundOpacity" to "0.70",
            "maskGradientBottom" to "#00e183",
            "maskGradientTop" to "#00e183",
            "text" to "#FFFFFF",
            "gradientLeft" to "#F5D809",
            "slideSplitTopBackground" to "#00e183",
            "subtitleOpacity" to "0.80",
            "subtitleTopBackground" to "#F5D809",
            "titleBackground" to "#000000"
        )
        return mapOf(
            "bogota" to bogotaColors,
            "algiers" to algiersColors,
            "bali" to baliColors,
            "geneva" to genevaColors,
            "los_angeles" to losAngelesColors,
            "seattle" to seattleColors,
            "berlin" to berlinColors
        )
    }
}

