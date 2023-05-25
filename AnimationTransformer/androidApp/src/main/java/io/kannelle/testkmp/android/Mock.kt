package io.kannelle.testkmp.android

import androidx.compose.ui.graphics.Color

val colors = listOf(
    Color.White,
    Color.Black,
    Color.Blue,
    Color.Yellow,
    Color.Green,
    Color.Cyan,
    Color.Magenta,
    Color.Red,
    Color.DarkGray,
)

enum class ColorThemeType(val value: String) {
    ACCOR("Accor"),
    BTS("BTS"),
    BLOCKCHAIN("Blockchain");

    companion object {
        fun fromValue(value: String): ColorThemeType? {
            return values().find { it.value == value }
        }
    }
}

enum class AnimationType(val value: String) {
    ALGIERS_FORD("ALGIERS-FORD"),
    ALGIERS_PEUGEOT("ALGIERS-PEUGEOT"),
    ALGIERS_PLANE("ALGIERS-PLANE"),
    ALGIERS_SIMCA("ALGIERS-SIMCA"),
    BALI_FORD("BALI-FORD"),
    BALI_PEUGEOT("BALI-PEUGEOT"),
    BALI_PLANE("BALI-PLANE"),
    BERLIN_FORD("BERLIN-FORD"),
    BERLIN_PEUGEOT("BERLIN-PEUGEOT"),
    BERLIN_PLANE("BERLIN-PLANE"),
    BOGOTA_AUSTIN("BOGOTA-AUSTIN"),
    BOGOTA_AVENTI("BOGOTA-AVENTI"),
    BOGOTA_DELOREAN("BOGOTA-DELOREAN"),
    BOGOTA_DODGE("BOGOTA-DODGE"),
    BOGOTA_LINCOLN("BOGOTA-LINCOLN"),
    BOGOTA_MCLAREN("BOGOTA-MCLAREN"),
    BOGOTA_RAM("BOGOTA-RAM"),
    BOGOTA_ROUSH("BOGOTA-ROUSH"),
    BOGOTA_TOYOTA("BOGOTA-TOYOTA"),
    BOGOTA_VECTOR("BOGOTA-VECTOR"),
    GENEVA_FORD("GENEVA-FORD"),
    GENEVA_PEUGEOT("GENEVA-PEUGEOT"),
    GENEVA_PLANE("GENEVA-PLANE"),
    LOS_ANGELES_FORD("LOS_ANGELES-FORD"),
    LOS_ANGELES_PEUGEOT("LOS_ANGELES-PEUGEOT"),
    LOS_ANGELES_PLANE("LOS_ANGELES-PLANE"),
    LOS_ANGELES_SIMCA("LOS_ANGELES-SIMCA"),
    PARIS_BUTTERFLY("PARIS-BUTTERFLY"),
    PARIS_FOX("PARIS-FOX"),
    PARIS_OWL("PARIS-OWL"),
    SEATTLE_FORD("SEATTLE-FORD"),
    SEATTLE_PEUGEOT("SEATTLE-PEUGEOT"),
    SEATTLE_PLANE("SEATTLE-PLANE")
}

fun colorSetAccor(): Map<String, Map<String, String>> {
    val bogotaColors = mapOf(
        "shapeNeutral" to "#342EEA",
        "figure" to "#908080",
        "shapeShadow" to "#716B6B",
        "background" to "#D55656",
        "shapeSecondary" to "#CBE60A",
        "backgroundOpacity" to "0.3",
        "text" to "#FFFFFF",
        "shapeMain" to "#FB6B00",
        "shapeShadowOpacity" to "0.80"
    )
    val berlinColors = mapOf(
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
    val genevaColors = mapOf(
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
    val losAngelesColors = mapOf(
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
    val seattleColors = mapOf(
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
        "text" to "#FFFFFF",
        "slideSplitTopBackground" to "#D3A86A",
        "floatingText" to "#050033",
        "titleBackground" to "#000000",
        "subtitleTopBackground" to "#ffffff"
    )
    val baliColors = mapOf(
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
    val algiersColors = mapOf(
        "subtitleBottomBackground" to "#D3A86A",
        "containerBottom" to "#e6a500",
        "slideSplitBottomText" to "#ffffff",
        "subtitleBottomText" to "#ffffff",
        "slideSplitBottomBackground" to "#050033",
        "gradientRight" to "#050033",
        "subtitleTopText" to "#ffffff",
        "gradientText" to "#ffffff",
        "containerTop" to "#002b41",
        "slideSplitTopText" to "#ffffff",
        "background" to "#D3A86A",
        "titleText" to "#ffffff",
        "titleBackgroundOpacity" to "0.8",
        "maskGradientTop" to "#FFD280",
        "maskGradientBottom" to "#D3A86A",
        "text" to "#FFFFFF",
        "gradientLeft" to "#050033",
        "slideSplitTopBackground" to "#D3A86A",
        "subtitleOpacity" to "0.8",
        "titleBackground" to "#d3a86a",
        "subtitleTopBackground" to "#4030ca"
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

fun colorSetBTS(): Map<String, Map<String, String>> {
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

fun colorSetBlockchain(): Map<String, Map<String, String>> {
    val bogotaColors = mapOf(
        "coverTextOpacity" to "0.50",
        "shapeShadow" to "#000000",
        "negativeBarBottom" to "#FFFFFF",
        "coverText" to "#FFFFFF",
        "backgroundOpacity" to "1.00",
        "titleBlinking" to "#00AEE6",
        "source" to "#00AEE6",
        "title" to "#FFFFFF",
        "positiveBarTop" to "#00AEE6",
        "positiveBarBottom" to "#00AEE6",
        "shapeNeutral" to "#FFFFFF",
        "cta" to "#FFFFFF",
        "negativeBarTop" to "#FFFFFF",
        "ctaTitle" to "#FFFFFF",
        "transparentBackground" to "#000000",
        "maskGradientBottom" to "#00AEE6",
        "maskGradientTop" to "#00AEE6",
        "text" to "#FFFFFF",
        "backgroundBarOpacity" to "0.20",
        "textShadow" to "#000000",
        "figure" to "#FFFFFF",
        "shapeCosmeticOpacity" to "0.70",
        "titleBottom" to "#FFFFFF",
        "shapeCosmetic" to "#FFFFFF",
        "chartEmpty" to "#FFFFFF",
        "background" to "#123962",
        "shapeSecondary" to "#123962",
        "textShadowOpacity" to "0.70",
        "chartFill" to "#00AEE6",
        "shapeMain" to "#00AEE6",
        "sourceNeutral" to "#FFFFFF",
        "figureBlink" to "#00AEE6",
        "shapeShadowOpacity" to "0.70",
        "ctaTitleBlinking" to "#00AEE6"
    )
    val berlinColors = mapOf(
        "gradientTop" to "#00AEE6",
        "neutralGradientTop" to "#FFFFFF",
        "titleGradientTop" to "#00AEE6",
        "textBackgroundOpacity" to "1.00",
        "shapeSecond" to "#799EB2",
        "neutralGradientBottom" to "#000000",
        "shapeNeutral" to "#FFFFFF",
        "titleGradientBottom" to "#799EB2",
        "titleBackgroundOpacity" to "0.80",
        "slideBackground" to "#FFFFFF",
        "maskGradientBottom" to "#799EB2",
        "maskGradientTop" to "#00AEE6",
        "textBackground" to "#123962",
        "gradientBottom" to "#799EB2",
        "text" to "#FFFFFF",
        "shapeMain" to "#00AEE6",
        "titleBackground" to "#000000"
    )
    val genevaColors = mapOf(
        "shape" to "#FFFFFF",
        "shadow" to "#000000",
        "background" to "#123962",
        "titleText" to "#FFFFFF",
        "titleBackgroundOpacity" to "0.50",
        "maskGradientBottom" to "#00AEE6",
        "maskGradientTop" to "#00AEE6",
        "text" to "#FFFFFF",
        "shadowOpacity" to "0.70",
        "titleBackground" to "#000000"
    )
    val losAngelesColors = mapOf(
        "shadow" to "#000000",
        "slideTextStrong" to "#00AEE6",
        "subtitleBottomText" to "#FFFFFF",
        "subtitleTopText" to "#FFFFFF",
        "shadowOpacity" to "0.50",
        "titleText" to "#FFFFFF",
        "titleBackgroundOpacity" to "0.70",
        "slideBackground" to "#FFFFFF",
        "floatingBackground" to "#123962",
        "slideText" to "#123962",
        "maskGradientTop" to "#00AEE6",
        "maskGradientBottom" to "#799EB2",
        "floatingText" to "#FFFFFF",
        "subtitleBackground" to "#123962",
        "titleBackground" to "#000000"
    )
    val seattleColors = mapOf(
        "subtitleBottomBackground" to "#123962",
        "shape" to "#123962",
        "slideFullBackground" to "#00AEE6",
        "slideSplitBottomText" to "#00AEE6",
        "subtitleBottomText" to "#FFFFFF",
        "slideSplitBottomBackground" to "#FFFFFF",
        "subtitleTopText" to "#123962",
        "slideSplitTopText" to "#FFFFFF",
        "slideFullText" to "#FFFFFF",
        "titleText" to "#FFFFFF",
        "titleBackgroundOpacity" to "0.80",
        "floatingBackground" to "#FFFFFF",
        "maskGradientBottom" to "#799EB2",
        "maskGradientTop" to "#799EB2",
        "slideSplitTopBackground" to "#00AEE6",
        "floatingText" to "#123962",
        "subtitleTopBackground" to "#FFFFFF",
        "titleBackground" to "#000000"
    )
    val baliColors = mapOf(
        "shape" to "#00AEE6",
        "background" to "#123962",
        "titleText" to "#FFFFFF",
        "titleBackgroundOpacity" to "0.70",
        "backgroundOpacity" to "0.50",
        "maskGradientBottom" to "#123962",
        "maskGradientTop" to "#123962",
        "text" to "#FFFFFF",
        "titleBackground" to "#000000"
    )
    val algiersColors = mapOf(
        "subtitleBottomBackground" to "#00AEE6",
        "slideSplitBottomText" to "#FFFFFF",
        "subtitleBottomText" to "#FFFFFF",
        "gradientRight" to "#123962",
        "slideSplitBottomBackground" to "#123962",
        "subtitleTopText" to "#FFFFFF",
        "gradientText" to "#FFFFFF",
        "slideSplitTopText" to "#FFFFFF",
        "titleText" to "#FFFFFF",
        "titleBackgroundOpacity" to "0.70",
        "maskGradientBottom" to "#00AEE6",
        "maskGradientTop" to "#00AEE6",
        "gradientLeft" to "#123962",
        "slideSplitTopBackground" to "#00AEE6",
        "subtitleOpacity" to "0.80",
        "subtitleTopBackground" to "#123962",
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