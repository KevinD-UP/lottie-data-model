package lottieAnimation

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import lottieAnimation.rules.properties.KPAnimationRules
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalSerializationApi
class KPAnimationRulesTest {

    private fun rulesToTest(rulesToTest: List<String>) {
        rulesToTest.forEach { filename ->
            val path = filename.toPath()
            println("path = $path")
            val rulesJson = FileSystem.SYSTEM.read(path) {
                readUtf8()
            }
            val json = Json {
                explicitNulls = false
                encodeDefaults = true
            }
            val animationRules = json.decodeFromString<KPAnimationRules?>(rulesJson)
            requireNotNull(animationRules)
            val outputJson = json.encodeToString(animationRules)
            val outputRuleElement = json.parseToJsonElement(outputJson)
            val redecoded = json.decodeFromJsonElement<KPAnimationRules>(outputRuleElement)
            val reencoded = json.encodeToString(redecoded)
            val res = json.parseToJsonElement(reencoded)
            assertEquals(outputRuleElement, res)
            println("path done = $path")
        }
    }

    @Test
    fun testDecodeAlgiers() {
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
        rulesToTest(algierList)
    }

    @Test
    fun testDecodeBali() {
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
        rulesToTest(baliList)
    }

    @Test
    fun testDecodeBerlin() {
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
        rulesToTest(berlinList)
    }

    @Test
    fun testDecodeBogota() {
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
        rulesToTest(bogotaList)
    }

    @Test
    fun testDecodeGeneva() {
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
        rulesToTest(genevaList)
    }

    @Test
    fun testDecodeLosAngeles() {
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
        rulesToTest(losAngelesList)
    }

    @Test
    fun testDecodeParis() {
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
        rulesToTest(parisList)
    }

    @Test
    fun testDecodeSeattle() {
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
        rulesToTest(seattleList)
    }
}