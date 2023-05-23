package lottieAnimation.transformer.colorTransformer

import kotlinx.serialization.ExperimentalSerializationApi
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

class KPColorTransformerTest {

    private fun colorsFor(theme: String) : Map<String,String> {
        return when (theme) {
            "bogota" -> mapOf(
                "shapeNeutral" to "#342EEA",
                "figure" to "#908080",
                "shapeShadow" to "#716B6B",
                "background" to "#D55656",
                "shapeSecondary" to "#CBE60A",
                "backgroundOpacity" to "0.3",
                "text" to "#ffffff",

                "shapeMain" to "#FB6B00",
                "shapeShadowOpacity" to "0.80"
            )
            "berlin" -> mapOf(
                "gradientTop" to "#FFD280",
                "neutralGradientTop" to "#ffffff",
                "titleGradientTop" to "#80FFE6",
                "textBackgroundOpacity" to "0.7",
                "neutralGradientBottom" to "#000000",
                "shapeSecond" to "#050033",

                "shapeNeutral" to "#ffffff",
                "background" to "#D3A86A",
                "titleGradientBottom" to "#D3A86A",
                "slideBackground" to "#ffffff",
                "titleBackgroundOpacity" to "0.7",
                "textBackground" to "#050033",
                "maskGradientBottom" to "#D3A86A",
                "maskGradientTop" to "#FFD280",
                "text" to "#ffffff",
                "gradientBottom" to "#D3A86A",
                "shapeMain" to "#D3A86A",
                "titleBackground" to "#000000"
            )
            "geneva" -> mapOf(
                "container" to "#002b41",
                "shadow" to "#000000",
                "shape" to "#ffffff",
                "background" to "#050033",
                "line" to "#e6a500",
                "titleText" to "#ffffff",
                "maskGradientBottom" to "#D3A86A",
                "maskGradientTop" to "#FFD280",
                "text" to "#ffffff",
                "shadowOpacity" to "0.7",
                "titleBackground" to "#000000"
            )
            "losAngeles" -> mapOf(
                "shadow" to "#FFFF05",
                "slideTextStrong" to "#D3A86A",
                "textStrong" to "#e6a500",
                "subtitleBottomText" to "#ffffff",
                "shadowOpacity" to "1",
                "subtitleTopText" to "#ffffff",

                "containerStrong" to "#e6a500",
                "background" to "#D3A86A",
                "titleText" to "#ffffff",
                "slideBackground" to "#ffffff",
                "slideText" to "#050033",
                "floatingBackground" to "#EEECF2",
                "maskGradientBottom" to "#D3A86A",
                "maskGradientTop" to "#FFD280",
                "floatingText" to "#ffffff",
                "text" to "#002b41",
                "subtitleBackground" to "#050033",
                "titleBackground" to "#000000"
            )
            "seattle" -> mapOf(
                "subtitleBottomBackground" to "#050033",
                "shape" to "#050033",
                "slideFullBackground" to "#D3A86A",
                "slideSplitBottomText" to "#D3A86A",
                "subtitleBottomText" to "#ffffff",
                "main" to "#e6a500",
                "slideSplitBottomBackground" to "#ffffff",
                "subtitleTopText" to "#050033",

                "second" to "#002b41",
                "background" to "#D3A86A",
                "slideSplitTopText" to "#ffffff",
                "slideFullText" to "#ffffff",
                "titleText" to "#ffffff",
                "floatingBackground" to "#ffffff",
                "maskGradientTop" to "#FFD280",
                "maskGradientBottom" to "#D3A86A",
                "text" to "#ffffff",
                "slideSplitTopBackground" to "#D3A86A",
                "floatingText" to "#050033",
                "titleBackground" to "#000000",
                "subtitleTopBackground" to "#ffffff"
            )
            "bali" -> mapOf(
                "container" to "#002b41",
                "shape" to "#D3A86A",
                "line" to "#e6a500",
                "backgroundOpacity" to "0.5",
                "background" to "#050033",
                "titleText" to "#ffffff",
                "titleBackgroundOpacity" to "0.85",
                "maskGradientBottom" to "#D3A86A",
                "maskGradientTop" to "#FFD280",
                "text" to "#ffffff",
                "subtitleOpacity" to "0.85",
                "titleBackground" to "#000000"
            )
            "algiers" -> mapOf(
                "subtitleBottomBackground" to "#D3A86A",
                "containerBottom" to "#e6a500",
                "slideSplitBottomText" to "#ffffff",
                "subtitleBottomText" to "#ffffff",
                "slideSplitBottomBackground" to "#050033",
                "gradientRight" to "#050033",
                "subtitleTopText" to "#ffffff",
                "gradientText" to "#000000",
                "containerTop" to "#002b41",
                "slideSplitTopText" to "#ffffff",
                "background" to "#D3A86A",
                "titleText" to "#ffffff",
                "titleBackgroundOpacity" to "0.8",
                "maskGradientTop" to "#FFD280",
                "maskGradientBottom" to "#D3A86A",
                "text" to "#ffffff",
                "gradientLeft" to "#050033",
                "slideSplitTopBackground" to "#D3A86A",
                "subtitleOpacity" to "0.8",
                "titleBackground" to "#d3a86a",
                "subtitleTopBackground" to "#4030ca"
            )
            else -> throw (Exception("Unhandled theme?"))
        }
    }

    // ---------------------- TESTS ----------------------
    @Test
    fun focusTest() {
        val animation: KPLottieAnimation = json.decodeFromString(readJson("src/commonTest/resources/animations/bali/a.json"))
        val rule: KPAnimationRules = json.decodeFromString(readJson("src/commonTest/resources/rules/bali/BALI-PLANE-rules.json"))
        KPColorTransformer()
            .transformColor(animation, rule, colorsFor("bali"))
    }


    @Test
    fun testAlgiers() {
        println("--------------- Algiers --------------------")
        algiersRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("algiers").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("algiers"))
            }
        }
        println("--------------------------------------------")
    }

    @Test
    fun testBali() {
        println("--------------- Bali --------------------")
        algiersRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("bali").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("bali"))
            }
        }
        println("--------------------------------------------")
    }

    @Test
    fun testBerlin() {
        println("--------------- Berlin --------------------")
        algiersRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("berlin").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("berlin"))
            }
        }
        println("--------------------------------------------")
    }

    @Test
    fun testGeneva() {
        println("--------------- Geneva --------------------")
        algiersRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("geneva").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("geneva"))
            }
        }
        println("--------------------------------------------")
    }

    @Test
    fun testLosAngeles() {
        println("--------------- Angeles --------------------")
        algiersRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("losAngeles").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("losAngeles"))
            }
        }
        println("--------------------------------------------")
    }

    @Test
    fun testSeattle() {
        println("--------------- Seattle --------------------")
        algiersRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        baliRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        berlinRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        genevaRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        losAngelesRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        seattleRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        bogotaRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        parisRules().forEach { rule ->
            animationsFor("seattle").forEach { animation ->
                KPColorTransformer().transformColor(animation, rule, colorsFor("seattle"))
            }
        }
        println("--------------------------------------------")
    }


    // -------------- Mock Data ------------------
    val animations = listOf("algiers", "bali", "berlin", "bogota", "geneva", "losAngeles", "paris", "seattle")

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = true
    }

    private fun readJson(path: String): String {
        return FileSystem.SYSTEM.read(path.toPath()) { readUtf8() }
    }

    private fun animationsFor(theme: String): List<KPLottieAnimation> {
        val animations = listOf(
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
        return animations.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimation")) }
    }

    private fun algiersRules(): List<KPAnimationRules> {
        val pathToAlgiersRules = "src/commonTest/resources/rules/algiers"
        val algierList = listOf(
            "$pathToAlgiersRules/ALGIERS-FORD-1_1-rules.json",
            "$pathToAlgiersRules/ALGIERS-FORD-9_16-rules.json",
            "$pathToAlgiersRules/ALGIERS-FORD-rules.json",
            "$pathToAlgiersRules/ALGIERS-MINI-rules.json",
            "$pathToAlgiersRules/ALGIERS-PEUGEOT-1_1-rules.json",
            "$pathToAlgiersRules/ALGIERS-PEUGEOT-9_16-rules.json",
            "$pathToAlgiersRules/ALGIERS-PEUGEOT-rules.json",
            "$pathToAlgiersRules/ALGIERS-PLANE-1_1-rules.json",
            "$pathToAlgiersRules/ALGIERS-PLANE-9_16-rules.json",
            "$pathToAlgiersRules/ALGIERS-PLANE-rules.json",
            "$pathToAlgiersRules/ALGIERS-SIMCA-1_1-rules.json",
            "$pathToAlgiersRules/ALGIERS-SIMCA-9_16-rules.json",
            "$pathToAlgiersRules/ALGIERS-SIMCA-rules.json"
        )
        return algierList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun baliRules(): List<KPAnimationRules> {
        val pathToBaliRules = "src/commonTest/resources/rules/bali"
        val baliList = listOf(
            "$pathToBaliRules/BALI-FORD-1_1-rules.json",
            "$pathToBaliRules/BALI-FORD-9_16-rules.json",
            "$pathToBaliRules/BALI-FORD-rules.json",
            "$pathToBaliRules/BALI-MINI-rules.json",
            "$pathToBaliRules/BALI-PEUGEOT-1_1-rules.json",
            "$pathToBaliRules/BALI-PEUGEOT-9_16-rules.json",
            "$pathToBaliRules/BALI-PEUGEOT-rules.json",
            "$pathToBaliRules/BALI-PLANE-1_1-rules.json",
            "$pathToBaliRules/BALI-PLANE-9_16-rules.json",
            "$pathToBaliRules/BALI-PLANE-rules.json"
        )
        return baliList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun berlinRules(): List<KPAnimationRules> {
        val pathToBerlinRules = "src/commonTest/resources/rules/berlin"
        val berlinList = listOf(
            "$pathToBerlinRules/BERLIN-FORD-1_1-rules.json",
            "$pathToBerlinRules/BERLIN-FORD-9_16-rules.json",
            "$pathToBerlinRules/BERLIN-FORD-rules.json",
            "$pathToBerlinRules/BERLIN-MINI-rules.json",
            "$pathToBerlinRules/BERLIN-PEUGEOT-1_1-rules.json",
            "$pathToBerlinRules/BERLIN-PEUGEOT-9_16-rules.json",
            "$pathToBerlinRules/BERLIN-PEUGEOT-rules.json",
            "$pathToBerlinRules/BERLIN-PLANE-1_1-rules.json",
            "$pathToBerlinRules/BERLIN-PLANE-9_16-rules.json",
            "$pathToBerlinRules/BERLIN-PLANE-rules.json"
        )
        return berlinList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun bogotaRules(): List<KPAnimationRules> {
        val pathToBogotaRules = "src/commonTest/resources/rules/bogota"
        val bogotaList = listOf(
            "$pathToBogotaRules/BOGOTA-AUSTIN-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-AUSTIN-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-AUSTIN-rules.json",
            "$pathToBogotaRules/BOGOTA-AVENTI-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-AVENTI-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-AVENTI-rules.json",
            "$pathToBogotaRules/BOGOTA-DELOREAN-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-DELOREAN-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-DELOREAN-rules.json",
            "$pathToBogotaRules/BOGOTA-DODGE-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-DODGE-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-DODGE-rules.json",
            "$pathToBogotaRules/BOGOTA-LINCOLN-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-LINCOLN-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-LINCOLN-rules.json",
            "$pathToBogotaRules/BOGOTA-MCLAREN-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-MCLAREN-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-MCLAREN-rules.json",
            "$pathToBogotaRules/BOGOTA-MINI-rules.json",
            "$pathToBogotaRules/BOGOTA-RAM-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-RAM-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-RAM-rules.json",
            "$pathToBogotaRules/BOGOTA-ROUSH-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-ROUSH-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-ROUSH-rules.json",
            "$pathToBogotaRules/BOGOTA-TOYOTA-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-TOYOTA-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-TOYOTA-rules.json",
            "$pathToBogotaRules/BOGOTA-VECTOR-1_1-rules.json",
            "$pathToBogotaRules/BOGOTA-VECTOR-9_16-rules.json",
            "$pathToBogotaRules/BOGOTA-VECTOR-rules.json"
        )
        return bogotaList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun genevaRules(): List<KPAnimationRules> {
        val pathToGenevaRules = "src/commonTest/resources/rules/geneva"
        val genevaList = listOf(
            "$pathToGenevaRules/GENEVA-FORD-1_1-rules.json",
            "$pathToGenevaRules/GENEVA-FORD-9_16-rules.json",
            "$pathToGenevaRules/GENEVA-FORD-rules.json",
            "$pathToGenevaRules/GENEVA-MINI-rules.json",
            "$pathToGenevaRules/GENEVA-PEUGEOT-1_1-rules.json",
            "$pathToGenevaRules/GENEVA-PEUGEOT-9_16-rules.json",
            "$pathToGenevaRules/GENEVA-PEUGEOT-rules.json",
            "$pathToGenevaRules/GENEVA-PLANE-1_1-rules.json",
            "$pathToGenevaRules/GENEVA-PLANE-9_16-rules.json",
            "$pathToGenevaRules/GENEVA-PLANE-rules.json"
        )
        return genevaList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun losAngelesRules(): List<KPAnimationRules> {
        val pathToLosAngelesRules = "src/commonTest/resources/rules/losAngeles"
        val losAngelesList = listOf(
            "$pathToLosAngelesRules/LOS_ANGELES-FORD-1_1-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-FORD-9_16-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-FORD-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-MINI-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-1_1-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-9_16-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PEUGEOT-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PLANE-1_1-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PLANE-9_16-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-PLANE-9_16-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-1_1-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-9_16-rules.json",
            "$pathToLosAngelesRules/LOS_ANGELES-SIMCA-rules.json"
        )
        return losAngelesList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun parisRules(): List<KPAnimationRules> {
        val pathToParisRules = "src/commonTest/resources/rules/paris"
        val parisList = listOf(
            "$pathToParisRules/PARIS-BUTTERFLY-1_1-rules.json",
            "$pathToParisRules/PARIS-BUTTERFLY-9_16-rules.json",
            "$pathToParisRules/PARIS-BUTTERFLY-rules.json",
            "$pathToParisRules/PARIS-FOX-1_1-rules.json",
            "$pathToParisRules/PARIS-FOX-9_16-rules.json",
            "$pathToParisRules/PARIS-FOX-rules.json",
            "$pathToParisRules/PARIS-MINI-rules.json",
            "$pathToParisRules/PARIS-OWL-1_1-rules.json",
            "$pathToParisRules/PARIS-OWL-9_16-rules.json",
            "$pathToParisRules/PARIS-OWL-9_16-rules.json"
        )
        return parisList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }

    private fun seattleRules(): List<KPAnimationRules> {
        val pathToSeattleRules = "src/commonTest/resources/rules/seattle"
        val seattleList = listOf(
            "$pathToSeattleRules/SEATTLE-FORD-1_1-rules.json",
            "$pathToSeattleRules/SEATTLE-FORD-9_16-rules.json",
            "$pathToSeattleRules/SEATTLE-FORD-rules.json",
            "$pathToSeattleRules/SEATTLE-MINI-rules.json",
            "$pathToSeattleRules/SEATTLE-PEUGEOT-1_1-rules.json",
            "$pathToSeattleRules/SEATTLE-PEUGEOT-9_16-rules.json",
            "$pathToSeattleRules/SEATTLE-PEUGEOT-rules.json",
            "$pathToSeattleRules/SEATTLE-PLANE-1_1-rules.json",
            "$pathToSeattleRules/SEATTLE-PLANE-9_16-rules.json",
            "$pathToSeattleRules/SEATTLE-PLANE-rules.json"
        )
        return seattleList.map { json.decodeFromString(readJson(it)) ?: throw (Exception("Cant decode LottieAnimationRules")) }
    }
}

